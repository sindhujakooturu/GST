package com.gst.organisation.company.service;

import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;

/**
 * @author Trigital
 *
 */
public interface CompanyWritePlatformService {

	 CommandProcessingResult createCompany(JsonCommand command);
	
	 CommandProcessingResult updateCompany(JsonCommand command,Long companyId);
	
}
