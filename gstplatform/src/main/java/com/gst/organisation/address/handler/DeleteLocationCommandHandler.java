package com.gst.organisation.address.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gst.commands.handler.NewCommandSourceHandler;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.organisation.address.service.AddressWritePlatformService;

@Service
public class DeleteLocationCommandHandler implements NewCommandSourceHandler{
	private final AddressWritePlatformService writePlatformService;
	
	@Autowired
	public DeleteLocationCommandHandler(final AddressWritePlatformService writePlatformService){
        this.writePlatformService = writePlatformService;
    }
 @Transactional
    @Override
    public CommandProcessingResult processCommand(final JsonCommand command) {
		 return this.writePlatformService.deleteLocation(command,command.getSupportedEntityType(),command.entityId());
 	}

}
