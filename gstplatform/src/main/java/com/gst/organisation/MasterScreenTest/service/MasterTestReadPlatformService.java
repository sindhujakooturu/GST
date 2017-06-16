package com.gst.organisation.MasterScreenTest.service;

import java.util.List;

import com.gst.organisation.MasterScreenTest.data.MasterTestData;

/**
 * @author Trigital
 * 
 */
public interface MasterTestReadPlatformService {

	List<MasterTestData> retrieveAllTestData();

	MasterTestData retrieveSingleTestDetails(Long id);

}
