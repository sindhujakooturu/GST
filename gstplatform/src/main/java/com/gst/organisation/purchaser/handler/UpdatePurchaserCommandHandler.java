package com.gst.organisation.purchaser.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gst.commands.annotation.CommandType;
import com.gst.commands.handler.NewCommandSourceHandler;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.organisation.purchaser.service.PurchaserWritePlatformService;

@Service
@CommandType(entity = "PURCHASER", action = "UPDATE")
public class UpdatePurchaserCommandHandler implements NewCommandSourceHandler{
	
	private final PurchaserWritePlatformService writePlatformService;
	
	@Autowired
    public UpdatePurchaserCommandHandler(final PurchaserWritePlatformService writePlatformService) {
        this.writePlatformService = writePlatformService;
    }

	@Override
	public CommandProcessingResult processCommand(JsonCommand command) {
		return this.writePlatformService.updatePurchaser(command.entityId(), command);
	}
}
