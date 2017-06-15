package com.gst.organisation.purchaser.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gst.commands.annotation.CommandType;
import com.gst.commands.handler.NewCommandSourceHandler;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.organisation.purchaser.service.PurchaserWritePlatformService;


@Service
@CommandType(entity = "PURCHASER", action = "CREATE")
public class CreatePurchaserCommandHandler implements NewCommandSourceHandler{
	
	private final PurchaserWritePlatformService writePlatformService;
	
	@Autowired
    public CreatePurchaserCommandHandler(final PurchaserWritePlatformService writePlatformService) {
        this.writePlatformService = writePlatformService;
    }
	@Transactional
    @Override
    public CommandProcessingResult processCommand(final JsonCommand command) {

        return this.writePlatformService.createPurchaser(command);
    }

}
