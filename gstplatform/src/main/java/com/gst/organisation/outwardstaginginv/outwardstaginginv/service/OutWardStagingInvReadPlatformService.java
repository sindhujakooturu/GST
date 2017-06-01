package com.gst.organisation.outwardstaginginv.service;

import java.util.List;

import com.gst.organisation.outwardstaginginv.data.OutWardStagingInvData;

/**
 * @author hugo
 * 
 */
public interface OutWardStagingInvReadPlatformService {

	List<OutWardStagingInvData> retrieveAllOutWardInvData();

	/*List<ChargeTypeData> getChargeType();

	List<DurationTypeData> getDurationType();

	List<BillFrequencyCodeData> getBillFrequency();*/

	OutWardStagingInvData retrieveSingleChargeCodeDetails(Long outWardInvId);

	//OutWardStagingInvData retrieveChargeCodeForRecurring(Long planId);
}
