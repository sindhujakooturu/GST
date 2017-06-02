package com.gst.organisation.supplier.service;

import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;

public interface SupplierWritePlatformService {
	
	/*CommandProcessingResult createSupplier(final JsonCommand command);*/

    CommandProcessingResult updateSupplier(final Long id, final JsonCommand command);

	CommandProcessingResult createSupplier(JsonCommand command);
}
