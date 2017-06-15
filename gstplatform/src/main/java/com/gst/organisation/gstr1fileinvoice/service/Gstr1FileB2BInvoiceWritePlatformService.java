package com.gst.organisation.gstr1fileinvoice.service;

import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;

/**
 * @author Trigital
 *
 */
public interface Gstr1FileB2BInvoiceWritePlatformService {

	 CommandProcessingResult createGstr1Fileb2b2Invoice(JsonCommand command);
	
	 CommandProcessingResult updateGstr1Fileb2bInvoice(JsonCommand command,Long gstr1Fileb2bInvId);
	
}
