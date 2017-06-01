package com.gst.organisation.outwardstaginginv.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gst.commands.handler.NewCommandSourceHandler;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
<<<<<<< HEAD
import com.gst.organisation.outwardstaginginv.service.ChargeCodeWritePlatformService;
=======
import com.gst.organisation.outwardstaginginv.service.OutWardStagingInvWritePlatformService;
>>>>>>> upstream/master

@Service
public class CreateOutWardStagingInvCommandHandler implements NewCommandSourceHandler {

<<<<<<< HEAD
	private final ChargeCodeWritePlatformService chargeCodeWritePlatformService;

	@Autowired
	public CreateOutWardStagingInvCommandHandler(
			final ChargeCodeWritePlatformService chargeCodeWritePlatformService) {
		this.chargeCodeWritePlatformService = chargeCodeWritePlatformService;
=======
	private final OutWardStagingInvWritePlatformService outWardStagingInvWritePlatformService;

	@Autowired
	public CreateOutWardStagingInvCommandHandler(
			final OutWardStagingInvWritePlatformService outWardStagingInvWritePlatformService) {
		this.outWardStagingInvWritePlatformService = outWardStagingInvWritePlatformService;
>>>>>>> upstream/master
	}

	@Transactional
	@Override
	public CommandProcessingResult processCommand(final JsonCommand command) {
<<<<<<< HEAD
		return chargeCodeWritePlatformService.createChargeCode(command);
=======
		return outWardStagingInvWritePlatformService.createChargeCode(command);
>>>>>>> upstream/master
	}

}
