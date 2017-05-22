package com.gst.organisation.b2binvoice.handler;

import com.gst.commands.annotation.CommandType;
import com.gst.commands.handler.NewCommandSourceHandler;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.organisation.b2binvoice.service.B2BInvoiceWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@CommandType(entity = "B2BINVOICESTATUS", action = "UPDATE")
public class UpdateB2BInvoiceStatusCommandHandler implements NewCommandSourceHandler {
	
	private final B2BInvoiceWritePlatformService b2BInvoiceWritePlatformService;

	@Autowired
	public UpdateB2BInvoiceStatusCommandHandler(B2BInvoiceWritePlatformService b2bInvoiceWritePlatformService) {
		b2BInvoiceWritePlatformService = b2bInvoiceWritePlatformService;
	}

	@Override
	@Transactional
	public CommandProcessingResult processCommand(JsonCommand command) {
		return this.b2BInvoiceWritePlatformService.updateB2BInvoiceStatus(command.entityId(),command);
	}
	
	

}
