package com.gst.organisation.gstr1fileinvoice.service;

import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;

/**
 * @author Trigital
 *
 */
public interface Gstr1FileB2BItemWritePlatformService {

	 CommandProcessingResult createGstr1Fileb2b2Item(JsonCommand command);
	
	 CommandProcessingResult updateGstr1Fileb2bItem(JsonCommand command,Long gstr1Fileb2bItmId);
	
}
