package com.gst.organisation.address.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gst.commands.handler.NewCommandSourceHandler;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.organisation.address.service.AddressWritePlatformService;

@Service
public class DeleteAddressCommandHandler implements NewCommandSourceHandler{
	
	private final AddressWritePlatformService addressWritePlatformService;
	
@Autowired
public DeleteAddressCommandHandler(final AddressWritePlatformService addressWritePlatformService){
 this.addressWritePlatformService = addressWritePlatformService;	
	
}

@Transactional
@Override
public CommandProcessingResult processCommand(JsonCommand command) {
     return this.addressWritePlatformService.deleteAddress(command.entityId(),command);

}

}
