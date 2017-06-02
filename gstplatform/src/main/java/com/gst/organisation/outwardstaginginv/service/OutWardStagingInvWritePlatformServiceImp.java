package com.gst.organisation.outwardstaginginv.service;

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
import com.gst.organisation.outwardstaginginv.domain.OutWardStagingInv;
import com.gst.organisation.outwardstaginginv.domain.OutWardStagingInvRepository;
import com.gst.organisation.outwardstaginginv.exception.OutWardStagingInvNotFoundException;
import com.gst.organisation.outwardstaginginv.serialization.OutWardStagingInvCommandFromApiJsonDeserializer;

/**
 * @author Trigital
 * 
 */
@Service
public class OutWardStagingInvWritePlatformServiceImp implements OutWardStagingInvWritePlatformService {

	private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(OutWardStagingInvWritePlatformServiceImp.class);

	private final PlatformSecurityContext context;
	private final OutWardStagingInvRepository outWardStagingInvRepository;
	private final OutWardStagingInvCommandFromApiJsonDeserializer apiJsonDeserializer;
    
	@Autowired
	public OutWardStagingInvWritePlatformServiceImp(final PlatformSecurityContext context,final OutWardStagingInvRepository outWardStagingInvRepository,
			final OutWardStagingInvCommandFromApiJsonDeserializer apiJsonDeserializer) {
		
		this.context = context;
		this.outWardStagingInvRepository = outWardStagingInvRepository;
		this.apiJsonDeserializer = apiJsonDeserializer;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 */
	@Transactional
	@Override
	public CommandProcessingResult createOutWardInv(final JsonCommand command) {

		//OutWardStagingInv chargeCode = null;
		try {
			context.authenticatedUser();
			this.apiJsonDeserializer.validaForCreate(command.json());
			
			OutWardStagingInv utWardStagingInvData  = OutWardStagingInv.fromJson(command);
			this.outWardStagingInvRepository.save(utWardStagingInvData);
			
			return new CommandProcessingResultBuilder().withCommandId(command.commandId()).withEntityId(utWardStagingInvData.getId()).build();
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
	public CommandProcessingResult updateOutWardInv(final JsonCommand command,final Long outWardInvId) {
		
		//OutWardStagingInv chargeCode = null;
		try {
			context.authenticatedUser();
			this.apiJsonDeserializer.validaForCreate(command.json());
			OutWardStagingInv outWardStagingInv = retrieveChargeCodeById(outWardInvId);
			
			final Map<String, Object> changes = outWardStagingInv.update(command);
			if (!changes.isEmpty()) {
				outWardStagingInvRepository.saveAndFlush(outWardStagingInv);
			}

			return new CommandProcessingResultBuilder().withCommandId(command.commandId()).withEntityId(outWardStagingInv.getId()).with(changes).build();
		} catch (DataIntegrityViolationException dve) {
			/*if (dve.getCause() instanceof ConstraintViolationException) {
				handleDataIntegrityIssues(command, dve);
			}*/
			return new CommandProcessingResult(Long.valueOf(-1L));
		}
	}

	private OutWardStagingInv retrieveChargeCodeById(final Long outWardInvId) {
		final OutWardStagingInv outWardStagingInv = this.outWardStagingInvRepository.findOne(outWardInvId);
		if (outWardStagingInv == null) {
			throw new OutWardStagingInvNotFoundException(outWardInvId);
		}
		return outWardStagingInv;
	}

}
