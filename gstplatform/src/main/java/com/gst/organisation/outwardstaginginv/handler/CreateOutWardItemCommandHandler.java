package com.gst.organisation.outwardstaginginv.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gst.commands.annotation.CommandType;
import com.gst.commands.handler.NewCommandSourceHandler;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.organisation.outwardstaginginv.service.OutWardStagingItemWritePlatformService;

@Service
@CommandType(entity = "OUTWARDITEM", action = "CREATE")
public class CreateOutWardItemCommandHandler  implements NewCommandSourceHandler{
	
	private final OutWardStagingItemWritePlatformService outWardStagingItemWritePlatformService;

	@Autowired
	public CreateOutWardItemCommandHandler(OutWardStagingItemWritePlatformService outWardStagingItemWritePlatformService) {
		this.outWardStagingItemWritePlatformService = outWardStagingItemWritePlatformService;
	}

	@Transactional
	@Override
	public CommandProcessingResult processCommand(JsonCommand command) {
		return this.outWardStagingItemWritePlatformService.createOutWardItem(command, command.entityId());
	}
	
	

}
