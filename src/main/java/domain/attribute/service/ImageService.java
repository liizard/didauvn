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
package domain.attribute.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import core.exception.DdException;
import domain.attribute.model.Image;
import domain.attribute.model.rq.GetMoreRq;
import domain.attribute.model.rq.PageRq;

public interface ImageService {

	public Image insertImage(MultipartFile multipartFile, String caption,
			long placeId) throws Exception;

	public void updateImage(Image imageView) throws DdException;

	public List<Image> getImages();

	public void deleteImage(long id);

	public List<Image> getImages(long placeId);

	public List<Image> getMoreImages(GetMoreRq getMoreRq);
	
	public List<Image> getImagesByPage(PageRq pageRq);

	public long getPageNumber(long placeId);

	public void setPlaceAvatar(long imageId, long placeId);

}