package com.gst.organisation.outwardstaginginv.service;

import java.util.List;

import com.gst.organisation.outwardstaginginv.data.OutWardStagingItemData;

/**
 * @author Trigital
 * 
 */
public interface OutWardStagingItemReadPlatformService {

	List<OutWardStagingItemData> retrieveAllOutWardItemData();

	List<OutWardStagingItemData> retriveOutwardStagingInvItems(Long invoiceId);

}
