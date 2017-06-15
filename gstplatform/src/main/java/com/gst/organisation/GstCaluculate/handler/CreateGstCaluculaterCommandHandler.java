/*package com.gst.organisation.GstCaluculate.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gst.commands.annotation.CommandType;
import com.gst.commands.handler.NewCommandSourceHandler;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.organisation.GstCaluculate.service.GstCaluculateWritePlatformService;


@Service
@CommandType(entity = "GSTCALUCULATE", action = "CREATE")
public class CreateGstCaluculaterCommandHandler implements NewCommandSourceHandler{
	
	private final GstCaluculateWritePlatformService writePlatformService;
	
	@Autowired
    public CreateGstCaluculaterCommandHandler(final GstCaluculateWritePlatformService writePlatformService) {
        this.writePlatformService = writePlatformService;
    }
	@Transactional
    @Override
    public String processCommand(final JsonCommand command) {

        return this.writePlatformService.createGstCaluculate(command);
    }

}
*/