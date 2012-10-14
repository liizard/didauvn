package core.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;

import core.exception.DdException;

public class UploadUtil {
	public static final String FILE_SIZE_LIMIT_CODE = "err271";
	public static final String FILE_SIZE_LIMIT = "Upload file size is out of limit";
	public static final String FILE_TYPE_NOT_ALLOW_CODE = "err272";
	public static final String FILE_TYPE_NOT_ALLOW = "Upload file type is not allowed";

	private int fileSizeLimit;
	private String fileTypeAllow;
	private String[] mimeTypeAllow;
	private String path;

	public UploadUtil() {
	}

	public UploadUtil(int fileSizeLimit, String fileTypeAllow,
			String[] mimeTypeAllow, String path) {
		super();
		this.fileSizeLimit = fileSizeLimit;
		this.fileTypeAllow = fileTypeAllow;
		this.mimeTypeAllow = mimeTypeAllow;
		this.path = path;
	}

	public void uploadFile(MultipartFile multipartFile, String fileName) throws DdException {
		InputStream inputStream = null;
		OutputStream outputStream = null;

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
			fileExt = fileNameTemp.substring(fileNameTemp.indexOf("."),
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

		try {
			inputStream = multipartFile.getInputStream();
			outputStream = new FileOutputStream(path + fileName);
			int readBytes = 0;
			byte[] buffer = new byte[fileSizeLimit];
			while ((readBytes = inputStream.read(buffer, 0, fileSizeLimit)) != -1) {
				outputStream.write(buffer, 0, readBytes);
			}
		}catch (Exception e) {
			//throw error
		} finally {
			if (inputStream != null)
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			if (outputStream != null)
				try {
					outputStream.close();
				} catch (IOException e) {
				}
		}
	}

	public int getFileSizeLimit() {
		return fileSizeLimit;
	}

	public void setFileSizeLimit(int fileSizeLimit) {
		this.fileSizeLimit = fileSizeLimit;
	}

	public String getFileTypeAllow() {
		return fileTypeAllow;
	}

	public void setFileTypeAllow(String fileTypeAllow) {
		this.fileTypeAllow = fileTypeAllow;
	}

}
