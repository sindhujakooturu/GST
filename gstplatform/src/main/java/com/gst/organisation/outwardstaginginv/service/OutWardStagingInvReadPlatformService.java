package com.gst.organisation.outwardstaginginv.service;

import java.util.List;

import com.gst.organisation.outwardstaginginv.data.OutWardStagingInvData;
<<<<<<< HEAD
import com.gst.organisation.outwardstaginginv.domain.OutWardStagingInv;

/**
 * @author hugo
=======

/**
 * @author Trigital
>>>>>>> upstream/master
 * 
 */
public interface OutWardStagingInvReadPlatformService {

	List<OutWardStagingInvData> retrieveAllOutWardInvData();

<<<<<<< HEAD
	/*List<ChargeTypeData> getChargeType();

	List<DurationTypeData> getDurationType();

	List<BillFrequencyCodeData> getBillFrequency();*/

	OutWardStagingInv retrieveSingleChargeCodeDetails(Long chargeCodeId);

	OutWardStagingInv retrieveChargeCodeForRecurring(Long planId);
=======
	OutWardStagingInvData retrieveSingleOutWardStagingInvDetails(Long outWardInvId);

>>>>>>> upstream/master
}
