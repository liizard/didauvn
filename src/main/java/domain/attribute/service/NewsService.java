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

import org.springframework.web.multipart.MultipartFile;

import core.exception.DdException;
import domain.attribute.model.News;

public interface NewsService {
	public long insert(News news, long placeId) throws DdException;

	public void update(News news, long placeId) throws DdException;

	public void delete(long id);

	public News getByPlace(long placeId);

	public long insertImage(MultipartFile multipartFile, long newsId)
			throws Exception;

	public long getImage(long newsId);;

}
