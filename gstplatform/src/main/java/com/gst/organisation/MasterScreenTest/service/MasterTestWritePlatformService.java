package com.gst.organisation.MasterScreenTest.service;

import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;

/**
 * @author Trigital
 *
 */
public interface MasterTestWritePlatformService {

	 CommandProcessingResult createMasterTest(JsonCommand command);
	
	
}
