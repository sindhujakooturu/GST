package com.gst.organisation.outwardstaginginv.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gst.commands.handler.NewCommandSourceHandler;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
<<<<<<< HEAD
import com.gst.organisation.outwardstaginginv.service.ChargeCodeWritePlatformService;
=======
import com.gst.organisation.outwardstaginginv.service.OutWardStagingInvWritePlatformService;
>>>>>>> upstream/master


@Service
public class UpdateOutWardStagingInvCommandHandler implements NewCommandSourceHandler {

<<<<<<< HEAD
	private final ChargeCodeWritePlatformService chargeCodeWritePlatformService;

	@Autowired
	public UpdateOutWardStagingInvCommandHandler(
			final ChargeCodeWritePlatformService chargeCodeWritePlatformService) {
		this.chargeCodeWritePlatformService = chargeCodeWritePlatformService;
=======
	private final OutWardStagingInvWritePlatformService outWardStagingInvWritePlatformService;

	@Autowired
	public UpdateOutWardStagingInvCommandHandler(final OutWardStagingInvWritePlatformService outWardStagingInvWritePlatformService) {
		this.outWardStagingInvWritePlatformService = outWardStagingInvWritePlatformService;
>>>>>>> upstream/master
	}

	@Override
	public CommandProcessingResult processCommand(final JsonCommand command) {
<<<<<<< HEAD
		return chargeCodeWritePlatformService.updateChargeCode(command,
				command.entityId());
=======
		return outWardStagingInvWritePlatformService.updateChargeCode(command,command.entityId());
>>>>>>> upstream/master
	}

}
