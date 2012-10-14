package core.security.dao;

public interface PermissionDao {
	public int checkPlaceManager(long placeId, long userId);

	public int checkImgPlaceManager(long imageId, long userId);

	public int checkVideoPlaceManager(long videoId, long userId);

	long checkImgPlaceOwner(long imageId);

	long checkVideoPlaceOwner(long videoId);

	long checkNewsPlaceOwner(long newsId);

	int checkNewsPlaceManager(long newsId, long uid);
}