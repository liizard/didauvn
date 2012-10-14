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

import domain.attribute.model.News;
import domain.attribute.model.NewsView;

public interface NewsDao {
	public long insert(News news, long placeId);

	public void update(News news, long placeId);

	public void delete(long id);

	public News getByPlace(long placeId);

	public List<NewsView> getByCategory(int categoryId, int from, int count);

	public List<NewsView> getNews(int from, int count);

	public List<NewsView> getDiscounts(int from, int count);

	public List<NewsView> getNotifies(int from, int count);

	public List<NewsView> getEvents(int from, int count);

	public void updateImage(long newsId,long imageId);

	public long getImage(long newsId);
}
