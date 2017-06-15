package com.gst.organisation.hsndata.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gst.commands.annotation.CommandType;
import com.gst.commands.handler.NewCommandSourceHandler;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.organisation.hsndata.service.HsndataWritePlatformService;

@Service
@CommandType(entity = "HSNDATA", action = "CREATE")
public class CreateHsndataCommandHandler implements NewCommandSourceHandler {
	
	private final HsndataWritePlatformService writePlatformService;

	 @Autowired
	    public CreateHsndataCommandHandler(final HsndataWritePlatformService writePlatformService) {
	        this.writePlatformService = writePlatformService;
	        
	    }

	 @Transactional
	 @Override
	 public CommandProcessingResult processCommand(final JsonCommand command) {

    return this.writePlatformService.createHsndata(command);
    
  }
}
