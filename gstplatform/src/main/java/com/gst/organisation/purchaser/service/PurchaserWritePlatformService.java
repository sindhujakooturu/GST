package com.gst.organisation.purchaser.service;

import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;

public interface PurchaserWritePlatformService {
	
	CommandProcessingResult createPurchaser(final JsonCommand command);

    CommandProcessingResult updatePurchaser(final Long purchaserId, final JsonCommand command);
	}
