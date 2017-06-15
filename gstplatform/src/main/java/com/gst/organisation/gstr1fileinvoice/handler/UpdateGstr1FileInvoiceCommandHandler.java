package com.gst.organisation.gstr1fileinvoice.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gst.commands.annotation.CommandType;
import com.gst.commands.handler.NewCommandSourceHandler;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.organisation.gstr1fileinvoice.service.Gstr1FileInvoiceWritePlatformService;


@Service
@CommandType(entity = "GSTR1FILEINVOICE", action = "UPDATE")
public class UpdateGstr1FileInvoiceCommandHandler implements NewCommandSourceHandler {

	private final Gstr1FileInvoiceWritePlatformService gstr1FileInvoiceWritePlatformService;

	@Autowired
	public UpdateGstr1FileInvoiceCommandHandler(final Gstr1FileInvoiceWritePlatformService gstr1FileInvoiceWritePlatformService) {
		this.gstr1FileInvoiceWritePlatformService = gstr1FileInvoiceWritePlatformService;
	}

	@Override
	public CommandProcessingResult processCommand(final JsonCommand command) {
		return gstr1FileInvoiceWritePlatformService.updateGstr1FileInvoice(command,command.entityId());
	}

}
