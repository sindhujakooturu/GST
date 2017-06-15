package com.gst.organisation.purchaser.service;


import java.util.Collection;

import com.gst.organisation.purchaser.data.PurchaserData;

public interface PurchaserReadPlatformService{
	
    	Collection<PurchaserData> retrieveAllPurchaser();
    	PurchaserData retrievePurchaserdata(final Long purchaserId);
	
}
