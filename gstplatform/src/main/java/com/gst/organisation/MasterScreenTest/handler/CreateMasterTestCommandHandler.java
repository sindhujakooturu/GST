package com.gst.organisation.MasterScreenTest.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gst.commands.handler.NewCommandSourceHandler;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.organisation.MasterScreenTest.service.MasterTestWritePlatformService;

@Service
//@CommandType(entity = "OUTWARDINV", action = "CREATE")
public class CreateMasterTestCommandHandler implements NewCommandSourceHandler {

	private final MasterTestWritePlatformService masterTestWritePlatformService;

	@Autowired
	public CreateMasterTestCommandHandler(final MasterTestWritePlatformService masterTestWritePlatformService) {
		this.masterTestWritePlatformService = masterTestWritePlatformService;
	}

	@Transactional
	@Override
	public CommandProcessingResult processCommand(final JsonCommand command) {
		return this.masterTestWritePlatformService.createOutWardInv(command);
	}

}
