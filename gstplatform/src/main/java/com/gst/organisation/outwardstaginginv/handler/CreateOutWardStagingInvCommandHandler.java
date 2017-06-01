package com.gst.organisation.outwardstaginginv.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gst.commands.handler.NewCommandSourceHandler;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.organisation.outwardstaginginv.service.OutWardStagingInvWritePlatformService;

@Service
public class CreateOutWardStagingInvCommandHandler implements NewCommandSourceHandler {

	private final OutWardStagingInvWritePlatformService outWardStagingInvWritePlatformService;

	@Autowired
	public CreateOutWardStagingInvCommandHandler(
			final OutWardStagingInvWritePlatformService outWardStagingInvWritePlatformService) {
		this.outWardStagingInvWritePlatformService = outWardStagingInvWritePlatformService;
	}

	@Transactional
	@Override
	public CommandProcessingResult processCommand(final JsonCommand command) {
		return outWardStagingInvWritePlatformService.createChargeCode(command);
	}

}
