package com.gst.organisation.GstCaluculate.service;

import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;

public interface GstCaluculateWritePlatformService {
	
	CommandProcessingResult createGstCaluculate(final JsonCommand command);

	//CommandProcessingResult createGstCaluculate(JsonCommand command);

	}
