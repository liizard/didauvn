package domain.attribute.service;

import domain.attribute.model.rq.ShareEmailRq;

public interface ShareService {
	
	public void sharePlaceViaEmail (long placeId, ShareEmailRq shareEmailRq);
}
