package com.gst.organisation.MasterScreenTest.service;

import java.util.List;

import com.gst.organisation.outwardstaginginv.data.OutWardStagingInvData;

/**
 * @author Trigital
 * 
 */
public interface MasterTestReadPlatformService {

	List<OutWardStagingInvData> retrieveAllOutWardInvData();

	OutWardStagingInvData retrieveSingleOutWardStagingInvDetails(Long outWardInvId);

}
