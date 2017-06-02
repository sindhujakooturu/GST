package com.gst.organisation.company.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.infrastructure.core.data.CommandProcessingResultBuilder;
import com.gst.infrastructure.security.service.PlatformSecurityContext;
import com.gst.organisation.company.domain.Company;
import com.gst.organisation.company.domain.CompanyRepository;
import com.gst.organisation.company.exception.CompanyNotFoundException;
import com.gst.organisation.company.serialization.CompanyCommandFromApiJsonDeserializer;

/**
 * @author Trigital
 * 
 */
@Service
public class CompanyWritePlatformServiceImp implements CompanyWritePlatformService {

	private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(CompanyWritePlatformServiceImp.class);

	private final PlatformSecurityContext context;
	private final CompanyRepository companyRepository;
	private final CompanyCommandFromApiJsonDeserializer apiJsonDeserializer;
    
	@Autowired
	public CompanyWritePlatformServiceImp(final PlatformSecurityContext context,final CompanyRepository companyRepository,
			final CompanyCommandFromApiJsonDeserializer apiJsonDeserializer) {
		
		this.context = context;
		this.companyRepository = companyRepository;
		this.apiJsonDeserializer = apiJsonDeserializer;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 */
	@Transactional
	@Override
	public CommandProcessingResult createCompany(final JsonCommand command) {

		try {
			context.authenticatedUser();
			this.apiJsonDeserializer.validaForCreate(command.json());
			
			Company companyData  = Company.fromJson(command);
			this.companyRepository.save(companyData);
			
			return new CommandProcessingResultBuilder().withCommandId(command.commandId()).withEntityId(companyData.getId()).build();
		} catch (final DataIntegrityViolationException dve) {
			handleDataIntegrityIssues(command, dve);
			return new CommandProcessingResult(Long.valueOf(-1L));
		}
	}

	private void handleDataIntegrityIssues(final JsonCommand command,
			final DataIntegrityViolationException dve) {
		   LOGGER.error(dve.getMessage(), dve);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.lang.Long)
	 */
	@Transactional
	@Override
	public CommandProcessingResult updateCompany(final JsonCommand command,final Long companyId) {
		
		//OutWardStagingInv chargeCode = null;
		try {
			context.authenticatedUser();
			this.apiJsonDeserializer.validaForCreate(command.json());
			Company companyData = retrieveCompanyById(companyId);
			
			final Map<String, Object> changes = companyData.update(command);
			if (!changes.isEmpty()) {
				companyRepository.saveAndFlush(companyData);
			}

			return new CommandProcessingResultBuilder().withCommandId(command.commandId()).withEntityId(companyData.getId()).with(changes).build();
		} catch (DataIntegrityViolationException dve) {
			/*if (dve.getCause() instanceof ConstraintViolationException) {
				handleDataIntegrityIssues(command, dve);
			}*/
			return new CommandProcessingResult(Long.valueOf(-1L));
		}
	}

	private Company retrieveCompanyById(final Long outWardInvId) {
		final Company company = this.companyRepository.findOne(outWardInvId);
		if (company == null) {
			throw new CompanyNotFoundException(outWardInvId);
		}
		return company;
	}

}
