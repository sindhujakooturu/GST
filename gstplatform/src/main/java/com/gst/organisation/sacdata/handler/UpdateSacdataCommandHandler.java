package com.gst.organisation.sacdata.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gst.commands.annotation.CommandType;
import com.gst.commands.handler.NewCommandSourceHandler;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.organisation.sacdata.service.SacdataWritePlatformService;

@Service
@CommandType(entity = "SACDATA", action = "UPDATE")
public class UpdateSacdataCommandHandler implements NewCommandSourceHandler {

    private final SacdataWritePlatformService writePlatformService;

    @Autowired
    public UpdateSacdataCommandHandler(final SacdataWritePlatformService writePlatformService) {
        this.writePlatformService = writePlatformService;
    }

    @Transactional
    @Override
    public CommandProcessingResult processCommand(final JsonCommand command) {

        return this.writePlatformService.updateSacdata(command.entityId(), command);
    }
}