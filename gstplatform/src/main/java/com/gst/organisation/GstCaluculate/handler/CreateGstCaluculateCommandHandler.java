package com.gst.organisation.GstCaluculate.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gst.commands.annotation.CommandType;
import com.gst.commands.handler.NewCommandSourceHandler;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.organisation.GstCaluculate.service.GstCaluculateWritePlatformService;


@Service
@CommandType(entity = "GstCaluculate", action = "CREATE")
public class CreateGstCaluculateCommandHandler implements NewCommandSourceHandler{
	
	private final GstCaluculateWritePlatformService writePlatformService;
	
	@Autowired
    public CreateGstCaluculateCommandHandler(final GstCaluculateWritePlatformService writePlatformService) {
        this.writePlatformService = writePlatformService;
    }
	@Transactional
    @Override
    public CommandProcessingResult processCommand(final JsonCommand command) {

        return this.writePlatformService.createGstCaluculate(command);
    }

}
