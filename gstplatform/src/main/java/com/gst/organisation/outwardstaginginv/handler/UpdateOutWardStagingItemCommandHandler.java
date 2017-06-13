package com.gst.organisation.outwardstaginginv.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gst.commands.annotation.CommandType;
import com.gst.commands.handler.NewCommandSourceHandler;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.organisation.outwardstaginginv.service.OutWardStagingItemWritePlatformService;


@Service
@CommandType(entity = "OUTWARDITEM", action = "UPDATE")
public class UpdateOutWardStagingItemCommandHandler implements NewCommandSourceHandler {

	private final OutWardStagingItemWritePlatformService outWardStagingItemWritePlatformService;

	@Autowired
	public UpdateOutWardStagingItemCommandHandler(final OutWardStagingItemWritePlatformService outWardStagingItemWritePlatformService) {
		this.outWardStagingItemWritePlatformService = outWardStagingItemWritePlatformService;
	}

	@Override
	public CommandProcessingResult processCommand(final JsonCommand command) {
		return outWardStagingItemWritePlatformService.updateOutWardItem(command,command.entityId());
	}

}
