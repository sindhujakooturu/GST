package com.gst.organisation.outwardstaginginv.service;

import java.util.List;

import com.gst.organisation.outwardstaginginv.data.OutWardStagingInvData;
import com.gst.organisation.outwardstaginginv.domain.OutWardStagingInv;

/**
 * @author hugo
 * 
 */
public interface OutWardStagingInvReadPlatformService {

	List<OutWardStagingInvData> retrieveAllOutWardInvData();

	/*List<ChargeTypeData> getChargeType();

	List<DurationTypeData> getDurationType();

	List<BillFrequencyCodeData> getBillFrequency();*/

	OutWardStagingInv retrieveSingleChargeCodeDetails(Long chargeCodeId);

	OutWardStagingInv retrieveChargeCodeForRecurring(Long planId);
}
