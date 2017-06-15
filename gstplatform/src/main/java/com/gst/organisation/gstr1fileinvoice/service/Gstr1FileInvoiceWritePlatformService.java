package com.gst.organisation.gstr1fileinvoice.service;

import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;

/**
 * @author Trigital
 *
 */
public interface Gstr1FileInvoiceWritePlatformService {

	 CommandProcessingResult createGstr1FileInvoice(JsonCommand command);
	
	 CommandProcessingResult updateGstr1FileInvoice(JsonCommand command,Long gstr1FileInvId);
	
}
