package com.gst.organisation.outwardstaginginv.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gst.commands.handler.NewCommandSourceHandler;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.organisation.outwardstaginginv.service.ChargeCodeWritePlatformService;


@Service
public class UpdateOutWardStagingInvCommandHandler implements NewCommandSourceHandler {

	private final ChargeCodeWritePlatformService chargeCodeWritePlatformService;

	@Autowired
	public UpdateOutWardStagingInvCommandHandler(
			final ChargeCodeWritePlatformService chargeCodeWritePlatformService) {
		this.chargeCodeWritePlatformService = chargeCodeWritePlatformService;
	}

	@Override
	public CommandProcessingResult processCommand(final JsonCommand command) {
		return chargeCodeWritePlatformService.updateChargeCode(command,
				command.entityId());
	}

}
