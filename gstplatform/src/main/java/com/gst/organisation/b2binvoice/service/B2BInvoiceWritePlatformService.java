package com.gst.organisation.b2binvoice.service;

import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;

public interface B2BInvoiceWritePlatformService {
	
	CommandProcessingResult updateB2BInvoiceStatus(Long id,JsonCommand command);

}
