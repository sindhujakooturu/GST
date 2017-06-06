package com.gst.organisation.outwardstaginginv.service;

import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;

public interface OutWardStagingItemWritePlatformService {

	CommandProcessingResult createOutWardItem(JsonCommand command, Long entityId);

}
