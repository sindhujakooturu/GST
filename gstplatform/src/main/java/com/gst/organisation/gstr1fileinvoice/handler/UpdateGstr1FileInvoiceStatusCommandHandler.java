package com.gst.organisation.gstr1fileinvoice.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gst.commands.annotation.CommandType;
import com.gst.commands.handler.NewCommandSourceHandler;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.organisation.gstr1fileinvoice.service.Gstr1FileInvoiceWritePlatformService;

@Service
@CommandType(entity = "GSTR1FILEINVOICESTATUS", action = "UPDATE")
public class UpdateGstr1FileInvoiceStatusCommandHandler implements NewCommandSourceHandler {

	private final Gstr1FileInvoiceWritePlatformService gstr1FileInvoiceWritePlatformService;

	@Autowired
	public UpdateGstr1FileInvoiceStatusCommandHandler(final Gstr1FileInvoiceWritePlatformService gstr1FileInvoiceWritePlatformService) {
		this.gstr1FileInvoiceWritePlatformService = gstr1FileInvoiceWritePlatformService;
	}

	@Transactional
	@Override
	public CommandProcessingResult processCommand(final JsonCommand command) {
		return gstr1FileInvoiceWritePlatformService.updateGstr1FileInvoiceStatus(command,command.entityId());
	}

}
