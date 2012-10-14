package domain.attribute.dao;

import java.util.List;

public interface WowDao {
	void insert(long placeId, long userId);

	List<Long> getUsersWow(long placeId);

	void delete(long placeid, long userId);

}