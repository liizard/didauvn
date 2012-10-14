package core.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

import core.exception.DdException;

public class ImageUtil {

	private static final Logger logger = Logger.getLogger(ImageUtil.class);

	public static final String FILE_SIZE_LIMIT_CODE = "err261";
	public static final String FILE_SIZE_LIMIT = "Upload file size is out of limit";
	public static final String FILE_TYPE_NOT_ALLOW_CODE = "err262";
	public static final String FILE_TYPE_NOT_ALLOW = "Upload file type is not allowed";

	private int fileSizeLimit;
	private String fileTypeAllow;
	private String[] mimeTypeAllow;
	private String path;

	public ImageUtil(int fileSizeLimit, String fileTypeAllow,
			String[] mimeTypeAllow, String path) {
		super();
		this.fileSizeLimit = fileSizeLimit;
		this.fileTypeAllow = fileTypeAllow;
		this.mimeTypeAllow = mimeTypeAllow;
		this.path = path;
	}

	public void resizeByWidth(MultipartFile multipartFile, String fileName,
			int maxWidth, int maxHeight) throws DdException, IOException {
		boolean f = false;
		for (String mime : mimeTypeAllow) {
			if (mime.equalsIgnoreCase(multipartFile.getContentType())) {
				f = true;
				break;
			}
		}

		if (!f) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					UploadUtil.FILE_TYPE_NOT_ALLOW_CODE,
					UploadUtil.FILE_TYPE_NOT_ALLOW);
		}

		String fileNameTemp = "", fileExt = "";
		fileNameTemp = multipartFile.getOriginalFilename();
		if (fileNameTemp.indexOf(".") > -1) {
			fileExt = fileNameTemp.substring(fileNameTemp.lastIndexOf("."),
					fileNameTemp.length());
		}
		fileExt = fileExt.toLowerCase();

		// Check for valid file type upload file
		if (fileTypeAllow.indexOf(fileExt) == -1) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					UploadUtil.FILE_TYPE_NOT_ALLOW_CODE,
					UploadUtil.FILE_TYPE_NOT_ALLOW);
		}

		// Check for limit upload file
		if (multipartFile.getSize() <= 0
				|| multipartFile.getSize() > fileSizeLimit) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					UploadUtil.FILE_SIZE_LIMIT_CODE, UploadUtil.FILE_SIZE_LIMIT);
		}

		BufferedImage img = ImageIO.read(multipartFile.getInputStream());
		if (img.getWidth() > maxWidth) {
			img = Scalr.resize(img, Scalr.Method.BALANCED,
					Scalr.Mode.FIT_TO_WIDTH, maxWidth, maxHeight,
					Scalr.OP_ANTIALIAS);
		}
		ImageIO.write(img, fileExt.substring(1), new File(path + fileName));

	}

	public void createThumbnail(MultipartFile multipartFile, String fileName,
			int thumbnailSize) throws DdException, IOException {
		boolean f = false;
		for (String mime : mimeTypeAllow) {
			if (mime.equalsIgnoreCase(multipartFile.getContentType())) {
				f = true;
				break;
			}
		}

		if (!f) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					UploadUtil.FILE_TYPE_NOT_ALLOW_CODE,
					UploadUtil.FILE_TYPE_NOT_ALLOW);
		}

		String fileNameTemp = "", fileExt = "";
		fileNameTemp = multipartFile.getOriginalFilename();
		if (fileNameTemp.indexOf(".") > -1) {
			fileExt = fileNameTemp.substring(fileNameTemp.lastIndexOf("."),
					fileNameTemp.length());
		}
		fileExt = fileExt.toLowerCase();

		// Check for valid file type upload file
		if (fileTypeAllow.indexOf(fileExt) == -1) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					UploadUtil.FILE_TYPE_NOT_ALLOW_CODE,
					UploadUtil.FILE_TYPE_NOT_ALLOW);
		}

		// Check for limit upload file
		if (multipartFile.getSize() <= 0
				|| multipartFile.getSize() > fileSizeLimit) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					UploadUtil.FILE_SIZE_LIMIT_CODE, UploadUtil.FILE_SIZE_LIMIT);
		}

		BufferedImage img = ImageIO.read(multipartFile.getInputStream());

		int cropMargin = (int) Math.abs(Math.round(((img.getWidth() - img
				.getHeight()) / 2.0)));

		// determine the crop coordinates
		int x1 = 0;
		int y1 = 0;
		int width = img.getWidth();
		int height = img.getHeight();
		if (img.getWidth() > img.getHeight()) {
			x1 = cropMargin;
			width = height;
		} else {
			y1 = cropMargin;
			height = width;
		}

		BufferedImage thumbnail = Scalr.crop(img, x1, y1, width, height);

		thumbnail = Scalr.resize(thumbnail, Scalr.Method.ULTRA_QUALITY,
				Scalr.Mode.AUTOMATIC, thumbnailSize, thumbnailSize,
				Scalr.OP_ANTIALIAS);

		ImageIO.write(thumbnail, fileExt.substring(1),
				new File(path + fileName));

	}

	public void resizeAndCreateThumbnail(MultipartFile multipartFile,
			String fileName, int maxWidth, int maxHeight, int thumbnailSize)
			throws IOException, DdException {
		boolean f = false;
		for (String mime : mimeTypeAllow) {
			if (mime.equalsIgnoreCase(multipartFile.getContentType())) {
				f = true;
				break;
			}
		}

		if (!f) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					UploadUtil.FILE_TYPE_NOT_ALLOW_CODE,
					UploadUtil.FILE_TYPE_NOT_ALLOW);
		}

		String fileNameTemp = "", fileExt = "";
		fileNameTemp = multipartFile.getOriginalFilename();
		if (fileNameTemp.indexOf(".") > -1) {
			fileExt = fileNameTemp.substring(fileNameTemp.lastIndexOf("."),
					fileNameTemp.length());
		}
		fileExt = fileExt.toLowerCase();

		// Check for valid file type upload file
		if (fileTypeAllow.indexOf(fileExt) == -1) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					UploadUtil.FILE_TYPE_NOT_ALLOW_CODE,
					UploadUtil.FILE_TYPE_NOT_ALLOW);
		}

		// Check for limit upload file
		if (multipartFile.getSize() <= 0
				|| multipartFile.getSize() > fileSizeLimit) {
			throw new DdException(DdException.VALIDATION_EXCEPTION,
					UploadUtil.FILE_SIZE_LIMIT_CODE, UploadUtil.FILE_SIZE_LIMIT);
		}

		BufferedImage img = ImageIO.read(multipartFile.getInputStream());
		logger.error("Util: width: " + img.getWidth());
		logger.error("Util: height: " + img.getHeight());
		if (img.getWidth() > maxWidth && img.getHeight() > maxHeight)
			img = Scalr.resize(img, Scalr.Method.BALANCED,
					Scalr.Mode.AUTOMATIC, maxWidth, maxHeight,
					Scalr.OP_ANTIALIAS);

		int cropMargin = (int) Math.abs(Math.round(((img.getWidth() - img
				.getHeight()) / 2.0)));

		logger.error("Util: afterwidth: " + img.getWidth());
		logger.error("Util: afterheight: " + img.getHeight());

		// determine the crop coordinates
		int x1 = 0;
		int y1 = 0;
		int width = img.getWidth();
		int height = img.getHeight();
		if (img.getWidth() > img.getHeight()) {
			x1 = cropMargin;
			width = height;
		} else {
			y1 = cropMargin;
			height = width;
		}

		BufferedImage thumbnail = Scalr.crop(img, x1, y1, width, height);

		logger.error("Util: thumbnail width: " + thumbnail.getWidth());
		logger.error("Util: thumbnail height: " + thumbnail.getHeight());

		thumbnail = Scalr.resize(thumbnail, Scalr.Method.ULTRA_QUALITY,
				Scalr.Mode.AUTOMATIC, thumbnailSize, thumbnailSize,
				Scalr.OP_ANTIALIAS);

		logger.error("Util: thumbnail afterwidth: " + thumbnail.getWidth());
		logger.error("Util: thumbnail afterheight: " + thumbnail.getHeight());
		logger.error("Util: filename: " + path);
		logger.error("Util: filename: " + fileName);

		ImageIO.write(img, fileExt.substring(1), new File(path + fileName));

		ImageIO.write(thumbnail, fileExt.substring(1), new File(path
				+ "thumb\\" + fileName));

	}

	public boolean deleteImg(String fileName) {
		return FileUtils.deleteQuietly(new File(path + fileName));
	}

	public boolean deleteImgAndThumb(String fileName) {
		return (FileUtils.deleteQuietly(new File(path + fileName)) && FileUtils
				.deleteQuietly(new File(path + "thumb/" + fileName)));
	}

}
