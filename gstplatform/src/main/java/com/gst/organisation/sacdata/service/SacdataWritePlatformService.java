package com.gst.organisation.sacdata.service;

import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.organisation.sacdata.exception.SacdataException;

public interface SacdataWritePlatformService {
	
	
	CommandProcessingResult createSacdata(final JsonCommand command) throws SacdataException;

    CommandProcessingResult updateSacdata(final Long id, final JsonCommand command);
    //CommandProcessingResult deleteSacdata(JsonCommand command);
}
