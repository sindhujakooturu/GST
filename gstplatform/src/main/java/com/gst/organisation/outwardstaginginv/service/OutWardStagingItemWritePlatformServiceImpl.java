package com.gst.organisation.outwardstaginginv.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.infrastructure.core.data.CommandProcessingResultBuilder;
import com.gst.organisation.outwardstaginginv.domain.OutWardStagingItem;
import com.gst.organisation.outwardstaginginv.domain.OutWardStagingItemRepository;
import com.gst.organisation.outwardstaginginv.serialization.OutWardStagingItemCommandFromApiJsonDeserializer;

@Service
public class OutWardStagingItemWritePlatformServiceImpl implements OutWardStagingItemWritePlatformService {

	private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(OutWardStagingItemWritePlatformServiceImpl.class);
	
	private final OutWardStagingItemRepository outWardStagingItemRepository;
	private final OutWardStagingItemCommandFromApiJsonDeserializer apiJsonDeserializer;
	
	
	@Autowired
	public OutWardStagingItemWritePlatformServiceImpl(final OutWardStagingItemRepository outWardStagingItemRepository,
			final OutWardStagingItemCommandFromApiJsonDeserializer apiJsonDeserializer) {

		this.outWardStagingItemRepository = outWardStagingItemRepository;
		this.apiJsonDeserializer = apiJsonDeserializer;
	}


	@Transactional
	@Override
	public CommandProcessingResult createOutWardItem(JsonCommand command, Long  entityId) {
		try{
			this.apiJsonDeserializer.validaForCreate(command.json(), entityId);
			final OutWardStagingItem outWardStagingItem  =  OutWardStagingItem.fromJson(command,entityId);
			this.outWardStagingItemRepository.saveAndFlush(outWardStagingItem);
			
			return new CommandProcessingResultBuilder().withCommandId(command.commandId()).withEntityId(outWardStagingItem.getId()).build();
		} catch (final DataIntegrityViolationException dve) {
			handleDataIntegrityIssues(command, dve);
			return new CommandProcessingResult(Long.valueOf(-1L));
		}
	}

	private void handleDataIntegrityIssues(final JsonCommand command,
			final DataIntegrityViolationException dve) {
		   LOGGER.error(dve.getMessage(), dve);
	}
}
