package com.gst.organisation.purchaser.service;


import java.util.Collection;

import com.gst.organisation.purchaser.data.PurchaserData;

public interface PurchaserReadPlatformService{
	
	//SupplierData retrieveSupplier(Long id);

    //Collection<SupplierData> retrieveAllSupplierForDropdown(Long id);

    /*Collection<SupplierData> retrieveAllSuppliersById(final Long id);*/
    
    //Collection<SupplierData> retrieveAllSupplierAndItsParentHierarchy(Long id);

    Collection<PurchaserData> retrieveAllPurchaser();

	
}
