package com.gst.organisation.gstr1fileinvoice.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gst.commands.annotation.CommandType;
import com.gst.commands.handler.NewCommandSourceHandler;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.organisation.gstr1fileinvoice.service.Gstr1FileB2BInvoiceWritePlatformService;
import com.gst.organisation.outwardstaginginv.service.OutWardStagingItemWritePlatformService;

@Service
@CommandType(entity = "GSTR1FILEB2BINVOICE", action = "UPDATE")
public class UpdateGstr1FileB2BInvoiceCommandHandler  implements NewCommandSourceHandler{
	
	private final Gstr1FileB2BInvoiceWritePlatformService gstr1FileB2BInvoiceWritePlatformService;

	@Autowired
	public UpdateGstr1FileB2BInvoiceCommandHandler(Gstr1FileB2BInvoiceWritePlatformService gstr1FileB2BInvoiceWritePlatformService) {
		this.gstr1FileB2BInvoiceWritePlatformService = gstr1FileB2BInvoiceWritePlatformService;
	}

	@Transactional
	@Override
	public CommandProcessingResult processCommand(JsonCommand command) {
		return this.gstr1FileB2BInvoiceWritePlatformService.updateGstr1FileB2BInvoice(command, command.entityId());
	}
	
	

}
