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
package domain.attribute.dao;

import java.util.List;

import domain.attribute.model.Image;

public interface ImageDao {

	public Image getImage(long id);

	public List<Image> getImages();
	
	public List<Image> getImages(long placeId);
	
	public List<Image> getMoreImages(long placeId, long lastId, int count);
	
	public List<Image> getImagesByPage(long placeId, int page, int count);
	
	public long countImages(long placeId);

	public long insertImage(final Image image);

	public void updateImage(Image image);

	public void deleteImage(long id);

}