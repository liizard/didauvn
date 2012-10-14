package domain.attribute.service;

import java.util.List;

public interface WowService { 
	List<Long> gets(long placeid);
	void insert(long placeid);
	void delete(long placeid);
}
