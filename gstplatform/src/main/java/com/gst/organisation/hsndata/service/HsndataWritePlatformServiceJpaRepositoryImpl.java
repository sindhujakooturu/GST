package com.gst.organisation.hsndata.service;

import java.util.Map;

import javax.persistence.PersistenceException;

import org.apache.commons.lang.exception.ExceptionUtils;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.infrastructure.core.data.CommandProcessingResultBuilder;
import com.gst.infrastructure.core.exception.PlatformDataIntegrityException;
import com.gst.infrastructure.security.service.PlatformSecurityContext;
import com.gst.organisation.hsndata.domain.Hsndata;
import com.gst.organisation.hsndata.domain.HsndataRepository;
import com.gst.organisation.hsndata.exception.HsndataNotFoundException;
import com.gst.organisation.hsndata.serialization.HsndataCommandFromApiJsonDeserializer;



@Service
public class HsndataWritePlatformServiceJpaRepositoryImpl implements HsndataWritePlatformService {

	//private final static Logger logger = LoggerFactory.getLogger(HsndataWritePlatformServiceJpaRepositoryImpl.class);
	
	private final PlatformSecurityContext context;
	private final HsndataCommandFromApiJsonDeserializer fromApiJsonDeserializer;
    private final HsndataRepository hsndataRepository;
    
    @Autowired
    public HsndataWritePlatformServiceJpaRepositoryImpl(final PlatformSecurityContext context,
    		final HsndataCommandFromApiJsonDeserializer fromApiJsonDeserializer,
            final HsndataRepository hsndataRepository) {
    	
    	this.context = context;
        this.fromApiJsonDeserializer = fromApiJsonDeserializer;
        this.hsndataRepository = hsndataRepository;
    }
        @Transactional
        @Override
        public CommandProcessingResult createHsndata(final JsonCommand command) {

            try {
            	
            	context.authenticatedUser();
            	
                this.fromApiJsonDeserializer.validateForCreate(command.json());

                 Hsndata hsn = Hsndata.fromJson( command);
                
                this.hsndataRepository.save(hsn);

                return new CommandProcessingResultBuilder().withCommandId(command.commandId()).withEntityId(hsn.getId()).build();
            } catch (final DataIntegrityViolationException dve) {
                handleHsndataDataIntegrityIssues(command, dve.getMostSpecificCause(), dve);
                return CommandProcessingResult.empty();
            }catch (final PersistenceException dve) {
            	Throwable throwable = ExceptionUtils.getRootCause(dve.getCause()) ;
            	handleHsndataDataIntegrityIssues(command, throwable, dve);
            	return CommandProcessingResult.empty();
            }
        }

        @Transactional
        public CommandProcessingResult updateHsndata(final Long id, final JsonCommand command) {

            try {
            	
                this.fromApiJsonDeserializer.validateForCreate(command.json());

                 Hsndata hsndataForUpdate = this.hsndataRepository.findOne(id);
                
                if (hsndataForUpdate == null) { 
                	
                	throw new HsndataNotFoundException(id); 
                }

                final Map<String, Object> changesOnly = hsndataForUpdate.update(command);

                if (changesOnly.containsKey("hsnCode")) {
                	
                    final String hsnCode = (String) changesOnly.get("hsnCode");
                  }

                if (!changesOnly.isEmpty()) {
                    this.hsndataRepository.saveAndFlush(hsndataForUpdate);
                }
                
                return new CommandProcessingResultBuilder().withCommandId(command.commandId()).with(changesOnly).build();
            } catch (final DataIntegrityViolationException dve) {
                handleHsndataDataIntegrityIssues(command, dve.getMostSpecificCause(), dve);
                return CommandProcessingResult.empty();
            }catch (final PersistenceException dve) {
            	Throwable throwable = ExceptionUtils.getRootCause(dve.getCause()) ;
            	handleHsndataDataIntegrityIssues(command, throwable, dve);
            	return CommandProcessingResult.empty();
            }
        }

        
        private void handleHsndataDataIntegrityIssues(final JsonCommand command, final Throwable realCause, final Exception dve) {

            if (realCause.getMessage().contains("hsnCode")) {

                final String hsnCode = command.stringValueOfParameterNamed("hsnCode");
                throw new PlatformDataIntegrityException("error.msg.hsndata.duplicate.hscCode", "Hsndata with hsnCode `" + hsnCode
                        + "` already exists", "hsnCode", hsnCode);
            } 

        /*    logger.error(dve.getMessage(), dve);
            throw new PlatformDataIntegrityException("error.msg.hsndata.unknown.data.integrity.issue",
                    "Unknown data integrity issue with resource: " + realCause.getMessage());
        }*/
    
    }
}
	
	
	

