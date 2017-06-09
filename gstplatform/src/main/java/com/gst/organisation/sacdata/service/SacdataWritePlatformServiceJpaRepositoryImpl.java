package com.gst.organisation.sacdata.service;

import java.util.Map;

import javax.persistence.PersistenceException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.infrastructure.core.data.CommandProcessingResultBuilder;
import com.gst.infrastructure.core.exception.PlatformDataIntegrityException;
import com.gst.infrastructure.security.service.PlatformSecurityContext;
import com.gst.organisation.provisioning.domain.ProvisioningCategory;
import com.gst.organisation.provisioning.exception.ProvisioningCategoryCannotBeDeletedException;
import com.gst.organisation.sacdata.domain.Sacdata;
import com.gst.organisation.sacdata.domain.SacdataRepository;
import com.gst.organisation.sacdata.exception.SacdataNotFoundException;
import com.gst.organisation.sacdata.serialization.SacdataCommandFromApiJsonDeserializer;



@Service
public class SacdataWritePlatformServiceJpaRepositoryImpl implements SacdataWritePlatformService {

	private final static Logger logger = LoggerFactory.getLogger(SacdataWritePlatformServiceJpaRepositoryImpl.class);
	
	private final PlatformSecurityContext context;
	private final SacdataCommandFromApiJsonDeserializer fromApiJsonDeserializer;
    private final SacdataRepository sacdataRepository;
    
    @Autowired
    public SacdataWritePlatformServiceJpaRepositoryImpl(final PlatformSecurityContext context,
    		final SacdataCommandFromApiJsonDeserializer fromApiJsonDeserializer,
            final SacdataRepository sacdataRepository) {
    	
    	this.context = context;
        this.fromApiJsonDeserializer = fromApiJsonDeserializer;
        this.sacdataRepository = sacdataRepository;
    }
        @Transactional
        @Override
        public CommandProcessingResult createSacdata(final JsonCommand command)  {
            try {
            	
            	context.authenticatedUser();
            	
                this.fromApiJsonDeserializer.validateForCreate(command.json());

                 Sacdata sac = Sacdata.fromJson( command);
                 
                
                this.sacdataRepository.save(sac);

                return new CommandProcessingResultBuilder().withCommandId(command.commandId()).withEntityId(sac.getId()).build();
            } catch (final DataIntegrityViolationException dve) {
                handleSacdataDataIntegrityIssues(command, dve.getMostSpecificCause(), dve);
                return CommandProcessingResult.empty();
            }catch (final PersistenceException dve) {
            	Throwable throwable = ExceptionUtils.getRootCause(dve.getCause()) ;
            	handleSacdataDataIntegrityIssues(command, throwable, dve);
            	return CommandProcessingResult.empty();
            }
        }

        @Transactional
        public CommandProcessingResult updateSacdata(final Long id, final JsonCommand command) {

            try {
            	
                this.fromApiJsonDeserializer.validateForCreate(command.json());

                 Sacdata sacdataForUpdate = this.sacdataRepository.findOne(id);
                
                if (sacdataForUpdate == null) { 
                	
                	throw new SacdataNotFoundException(id); 
                }

                final Map<String, Object> changesOnly = sacdataForUpdate.update(command);

                if (changesOnly.containsKey("sacSeqId")) {
                	
                    final String sacSeqId = (String) changesOnly.get("sacSeqId");
                  }

                if (!changesOnly.isEmpty()) {
                    this.sacdataRepository.saveAndFlush(sacdataForUpdate);
                }
                
                
                return new CommandProcessingResultBuilder().withCommandId(command.commandId()).with(changesOnly).build();
            } catch (final DataIntegrityViolationException dve) {
                handleSacdataDataIntegrityIssues(command, dve.getMostSpecificCause(), dve);
                return CommandProcessingResult.empty();
            }catch (final PersistenceException dve) {
            	Throwable throwable = ExceptionUtils.getRootCause(dve.getCause()) ;
            	handleSacdataDataIntegrityIssues(command, throwable, dve);
            	return CommandProcessingResult.empty();
            }
        }
        
        private void handleSacdataDataIntegrityIssues(final JsonCommand command, final Throwable realCause, final Exception dve) {

            if (realCause.getMessage().contains("sacSeqId")) {

                final String sacSeqId = command.stringValueOfParameterNamed("sacSeqId");
                throw new PlatformDataIntegrityException("Sacdata with sacSeqId `" + sacSeqId
                        + "` already exists");
                
            }

            logger.error(dve.getMessage(), dve);
            throw new PlatformDataIntegrityException("duplicate Service Name  is not allowed");
        }
    
    }
	
	
	

