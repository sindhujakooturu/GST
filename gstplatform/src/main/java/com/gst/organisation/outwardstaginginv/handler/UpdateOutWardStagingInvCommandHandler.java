package com.gst.organisation.outwardstaginginv.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gst.commands.annotation.CommandType;
import com.gst.commands.handler.NewCommandSourceHandler;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.organisation.outwardstaginginv.service.OutWardStagingInvWritePlatformService;


@Service
@CommandType(entity = "OUTWARDINV", action = "UPDATE")
public class UpdateOutWardStagingInvCommandHandler implements NewCommandSourceHandler {

	private final OutWardStagingInvWritePlatformService outWardStagingInvWritePlatformService;

	@Autowired
	public UpdateOutWardStagingInvCommandHandler(final OutWardStagingInvWritePlatformService outWardStagingInvWritePlatformService) {
		this.outWardStagingInvWritePlatformService = outWardStagingInvWritePlatformService;
	}

	@Override
	public CommandProcessingResult processCommand(final JsonCommand command) {
		return outWardStagingInvWritePlatformService.updateOutWardInv(command,command.entityId());
	}

}
