package com.gst.organisation.company.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gst.commands.annotation.CommandType;
import com.gst.commands.handler.NewCommandSourceHandler;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.organisation.company.service.CompanyWritePlatformService;
import com.gst.organisation.outwardstaginginv.service.OutWardStagingInvWritePlatformService;

@Service
@CommandType(entity = "COMPANY", action = "CREATE")
public class CreateCompanyCommandHandler implements NewCommandSourceHandler {

	private final CompanyWritePlatformService companyWritePlatformService;

	@Autowired
	public CreateCompanyCommandHandler(final CompanyWritePlatformService companyWritePlatformService) {
		this.companyWritePlatformService = companyWritePlatformService;
	}

	@Transactional
	@Override
	public CommandProcessingResult processCommand(final JsonCommand command) {
		return companyWritePlatformService.createCompany(command);
	}

}
