package com.gst.organisation.company.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gst.commands.annotation.CommandType;
import com.gst.commands.handler.NewCommandSourceHandler;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.organisation.company.service.CompanyWritePlatformService;


@Service
@CommandType(entity = "COMPANY", action = "UPDATE")
public class UpdateCompanyCommandHandler implements NewCommandSourceHandler {

	private final CompanyWritePlatformService companyWritePlatformService;

	@Autowired
	public UpdateCompanyCommandHandler(final CompanyWritePlatformService companyWritePlatformService) {
		this.companyWritePlatformService = companyWritePlatformService;
	}

	@Override
	public CommandProcessingResult processCommand(final JsonCommand command) {
		return companyWritePlatformService.updateCompany(command,command.entityId());
	}

}
