package com.gst.organisation.outwardstaginginv.service;

import java.util.List;

import com.gst.organisation.outwardstaginginv.data.OutWardStagingInvData;

/**
 * @author Trigital
 * 
 */
public interface OutWardStagingInvReadPlatformService {

	List<OutWardStagingInvData> retrieveAllOutWardInvData();

	OutWardStagingInvData retrieveSingleOutWardStagingInvDetails(Long outWardInvId);

}
