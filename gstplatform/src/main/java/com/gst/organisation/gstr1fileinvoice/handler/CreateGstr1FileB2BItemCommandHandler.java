package com.gst.organisation.gstr1fileinvoice.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gst.commands.annotation.CommandType;
import com.gst.commands.handler.NewCommandSourceHandler;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.organisation.gstr1fileinvoice.service.Gstr1FileB2BInvoiceWritePlatformService;
import com.gst.organisation.gstr1fileinvoice.service.Gstr1FileB2BItemWritePlatformService;

@Service
@CommandType(entity = "GSTR1FILEB2BITEM", action = "CREATE")
public class CreateGstr1FileB2BItemCommandHandler  implements NewCommandSourceHandler{
	
	private final Gstr1FileB2BItemWritePlatformService gstr1FileB2BItemWritePlatformService;

	@Autowired
	public CreateGstr1FileB2BItemCommandHandler(Gstr1FileB2BItemWritePlatformService gstr1FileB2BItemWritePlatformService) {
		this.gstr1FileB2BItemWritePlatformService = gstr1FileB2BItemWritePlatformService;
	}

	@Transactional
	@Override
	public CommandProcessingResult processCommand(JsonCommand command) {
		return this.gstr1FileB2BItemWritePlatformService.createGstr1Fileb2b2Item(command);
	}
	
	

}
