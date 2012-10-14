/******************************************************************************
 * Copyright (c) 2012 didauvn.
 ******************************************************************************
 *
 ******************************************************************************
 *              M A I N T E N A N C E     L O G
 ******************************************************************************
 * ISSUE # DATE       PROGRAMMER DESCRIPTION
 * ------- ---------- ---------- ----------------------------------------------
 * 1	   08/08/2012 minhle	 Example
 ******************************************************************************
 */
package domain.attribute.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import core.constant.SystemConstant;
import core.constant.WebConstant;
import core.exception.DdException;
import core.job.executor.AsyncJobExecutor;
import core.util.ImageUtil;
import domain.attribute.dao.ImageDao;
import domain.attribute.dao.MessageDao;
import domain.attribute.model.Image;
import domain.attribute.model.rq.GetMoreRq;
import domain.attribute.model.rq.PageRq;
import domain.attribute.service.ImageService;
import domain.place.dao.PlaceDao;

@Service("imageService")
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageDao imageDao;

	@Autowired
	private MessageDao messageDao;

	@Autowired
	private PlaceDao placeDao;

	@Autowired
	private AsyncJobExecutor asyncJobExecutor;

	private String[] allowType = { "image/gif", "image/jpeg", "image/pjpeg",
			"image/png" };

	private ImageUtil imageUtil = new ImageUtil(2 * 1024 * 1024,
			".jpg.png.gif", allowType, SystemConstant.PATH_PLACE_GALLERY);

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_USER') and (hasPermission(#placeId,'hasPlaceOwnerRight') or hasPermission(#placeId,'hasPlaceRight')))")
	public Image insertImage(MultipartFile multipartFile, String caption,
			long placeId) throws Exception {
		long id = 0;
		try {
			Image image = new Image();
			image.setPlaceId(placeId);
			image.setCaption(caption);
			validateCaption(image); // validate caption
			id = imageDao.insertImage(image);
			imageUtil.resizeAndCreateThumbnail(multipartFile, id + ".jpg",
					1024, 768, 150);
			image.setId(id);
			
			// asynchronous job update
//			UpdateNotifyJob job = new UpdateNotifyJob(placeId, ContextHolder
//					.getInstance().getCurrentUserId(),
//					MessageEnum.IMAGE.ordinal());
//			job.setMessageDao(messageDao);
//			asyncJobExecutor.execute(job);
			
			return image;
		} catch (DdException e) {
			// Delete record when error
			imageDao.deleteImage(id);

			if (e.getCode().equalsIgnoreCase(ImageUtil.FILE_SIZE_LIMIT_CODE)) {
				throw new DdException(DdException.VALIDATION_EXCEPTION,
						Image.IMAGE_SIZE_LIMIT_CODE, Image.IMAGE_SIZE_LIMIT, e);

			} else if (e.getCode().equalsIgnoreCase(
					ImageUtil.FILE_TYPE_NOT_ALLOW_CODE)) {
				throw new DdException(DdException.VALIDATION_EXCEPTION,
						Image.IMAGE_TYPE_NOT_ALLOW_CODE,
						Image.IMAGE_TYPE_NOT_ALLOW, e);
			} else {
				throw e;
			}
		} catch (Exception e) {
			// Delete record when error
			imageDao.deleteImage(id);
			throw e;
		}
	}

	@Override
	@PreAuthorize("hasRole('ROLE_USER') and (hasPermission(#image.getId(),'hasImageOwnerRight') or hasPermission(#image.getId(),'hasImageRight'))")
	public void updateImage(Image image) throws DdException {
		validateCaption(image); // validate caption
		imageDao.updateImage(image);
	}

	@Override
	public List<Image> getImages(long placeId) {
		List<Image> images = imageDao.getImages(placeId);
		return images;
	}

	// Get More Image
	@Override
	public List<Image> getMoreImages(GetMoreRq getMoreRq) {
		List<Image> images = imageDao.getMoreImages(getMoreRq.getPlaceId(),
				getMoreRq.getLastId(), WebConstant.IMAGE_PER_PAGE + 1);
		return images;
	}

	// Get Image By Page
	@Override
	public List<Image> getImagesByPage(PageRq pageRq) {
		List<Image> images = imageDao.getImagesByPage(pageRq.getPlaceId(),
				pageRq.getPage() * WebConstant.IMAGE_PER_PAGE,
				WebConstant.IMAGE_PER_PAGE + 1);
		return images;
	}

	@Override
	public List<Image> getImages() {
		return imageDao.getImages();
	}

	@Override
	@PreAuthorize("hasRole('ROLE_USER') and (hasPermission(#id,'hasImageOwnerRight') or hasPermission(#id,'hasImageRight'))")
	public void deleteImage(long id) {
		Image img = imageDao.getImage(id);
		imageUtil.deleteImgAndThumb(img.getId() + ".jpg");
		imageDao.deleteImage(id);
	}

	@Override
	public long getPageNumber(long placeId) {
		long pageNum = 0;
		pageNum = (long) Math.ceil((double) imageDao.countImages(placeId)
				/ WebConstant.IMAGE_PER_PAGE);
		return pageNum;
	}

	@Override
	@PreAuthorize("hasRole('ROLE_USER') and (hasPermission(#imageId,'hasImageOwnerRight') or hasPermission(#imageId,'hasImageRight')) and (hasPermission(#placeId,'hasPlaceOwnerRight') or hasPermission(#placeId,'hasPlaceRight') )")
	public void setPlaceAvatar(long imageId, long placeId) {
		placeDao.updateAvatar(placeId, imageId);
	}

	private void validateCaption(Image image) throws DdException {
		if (image.getCaption().length() > 200) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					Image.CAPTION_TOO_LONG_CODE, Image.CAPTION_TOO_LONG);
		}
	}
}
