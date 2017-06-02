package com.gst.organisation.supplier.service;


import java.util.Collection;

import com.gst.organisation.supplier.data.SupplierData;

public interface SupplierReadPlatformService {
	
	//SupplierData retrieveSupplier(Long id);

    //Collection<SupplierData> retrieveAllSupplierForDropdown(Long id);

    /*Collection<SupplierData> retrieveAllSuppliersById(final Long id);*/
    
    //Collection<SupplierData> retrieveAllSupplierAndItsParentHierarchy(Long id);

    Collection<SupplierData> retrieveAllSupplier();

	
}
