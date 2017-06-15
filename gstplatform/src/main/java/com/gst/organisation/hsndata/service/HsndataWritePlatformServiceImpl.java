package com.gst.organisation.hsndata.service;

import java.util.Map;
import javax.persistence.PersistenceException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.infrastructure.core.data.CommandProcessingResultBuilder;
import com.gst.infrastructure.core.exception.PlatformDataIntegrityException;
import com.gst.organisation.hsndata.domain.Hsndata;
import com.gst.organisation.hsndata.domain.HsndataRepository;
import com.gst.organisation.hsndata.exception.HsndataNotFoundException;
import com.gst.organisation.hsndata.serialization.HsndataCommandFromApiJsonDeserializer;


@Service
public class HsndataWritePlatformServiceImpl implements HsndataWritePlatformService {

	
	private final static Logger logger = LoggerFactory.getLogger(HsndataWritePlatformServiceImpl.class);
	private final HsndataCommandFromApiJsonDeserializer fromApiJsonDeserializer;
    private final HsndataRepository hsndataRepository;
    
    @Autowired
    public HsndataWritePlatformServiceImpl(final HsndataCommandFromApiJsonDeserializer fromApiJsonDeserializer,
            final HsndataRepository hsndataRepository) {
    	
        this.fromApiJsonDeserializer = fromApiJsonDeserializer;
        this.hsndataRepository = hsndataRepository;
    }
       
    @Override
     public CommandProcessingResult createHsndata(final JsonCommand command) {
  
    	try {
              this.fromApiJsonDeserializer.validateForCreate(command.json());
              final Hsndata hsn = Hsndata.fromJson( command);
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

	@Override
	public CommandProcessingResult updateHsndata(final Long hsnId,final JsonCommand command) {

		try {

			this.fromApiJsonDeserializer.validateForUpdate(command.json());
			Hsndata hsndata = this.retriveHsndata(hsnId);
			final Map<String, Object> changesOnly = hsndata.update(command);

			if (!changesOnly.isEmpty()) {
				this.hsndataRepository.saveAndFlush(hsndata);
			}

			return new CommandProcessingResultBuilder().withCommandId(command.commandId()).with(changesOnly).build();
		} catch (final DataIntegrityViolationException dve) {
			handleHsndataDataIntegrityIssues(command,dve.getMostSpecificCause(), dve);
			return CommandProcessingResult.empty();
		} catch (final PersistenceException dve) {
			Throwable throwable = ExceptionUtils.getRootCause(dve.getCause());
			handleHsndataDataIntegrityIssues(command, throwable, dve);
			return CommandProcessingResult.empty();
		}
	}

        
	private Hsndata retriveHsndata(final Long hsnId) {
		
		final Hsndata hsnData = this.hsndataRepository.findOne(hsnId);
		if (hsnData == null) {
			throw new HsndataNotFoundException(hsnId);
		} else {
			return hsnData;
		}

	}

	private void handleHsndataDataIntegrityIssues(final JsonCommand command, final Throwable realCause, final Exception dve) {

		if (realCause.getMessage().contains("hsnCode_UNIQUE")) {
			final String hsnCode = command.stringValueOfParameterNamed("hsnCode");
			throw new PlatformDataIntegrityException("error.msg.hsndata.duplicate.hscCode", "Hsndata with hsnCode `" + hsnCode + "` already exists",
					"hsnCode", hsnCode);
		}

		logger.error(dve.getMessage(), dve);
		throw new PlatformDataIntegrityException("error.msg.hsndata.unknown.data.integrity.issue",
				"Unknown data integrity issue with resource: "+ realCause.getMessage());

	}

	@Override
	public CommandProcessingResult deleteHsndata(final Long hsnId, final JsonCommand command) {
		
			final Hsndata hsndata = retriveHsndata(hsnId);
			hsndata.delete();
			this.hsndataRepository.save(hsndata);
			return new CommandProcessingResultBuilder().withEntityId(hsnId).build();
	}

}
	
	
	

