package com.gst.organisation.outwardstaginginv.service;

import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;

/**
 * @author Trigital
 *
 */
public interface OutWardStagingInvWritePlatformService {

	 CommandProcessingResult createOutWardInv(JsonCommand command);
	
	 CommandProcessingResult updateOutWardInv(JsonCommand command,Long outWardInvId);
	
}
