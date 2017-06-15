package com.gst.organisation.gstr1fileinvoice.service;

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
import com.gst.infrastructure.core.serialization.FromJsonHelper;
import com.gst.infrastructure.security.service.PlatformSecurityContext;
import com.gst.organisation.gstr1fileinvoice.domain.Gstr1FileB2BItem;
import com.gst.organisation.gstr1fileinvoice.domain.Gstr1FileB2BItemRepository;
import com.gst.organisation.gstr1fileinvoice.exception.Gstr1FileB2BItemNotFoundException;
import com.gst.organisation.gstr1fileinvoice.serialization.Gstr1FileB2BItemCommandFromApiJsonDeserializer;

/**
 * @author Trigital
 * 
 */
@Service
public class Gstr1FileB2BItemWritePlatformServiceImp implements Gstr1FileB2BItemWritePlatformService {

	private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(Gstr1FileB2BItemWritePlatformServiceImp.class);

	private final PlatformSecurityContext context;
	private final Gstr1FileB2BItemRepository gstr1FileB2BItemRepository;
	private final Gstr1FileB2BItemCommandFromApiJsonDeserializer apiJsonDeserializer;
	private final FromJsonHelper fromApiJsonHelper;
    
	@Autowired
	public Gstr1FileB2BItemWritePlatformServiceImp(final PlatformSecurityContext context,final Gstr1FileB2BItemRepository gstr1FileB2BItemRepository,
			final Gstr1FileB2BItemCommandFromApiJsonDeserializer apiJsonDeserializer,
			final FromJsonHelper fromApiJsonHelper) {
		
		this.context = context;
		this.gstr1FileB2BItemRepository = gstr1FileB2BItemRepository;
		this.apiJsonDeserializer = apiJsonDeserializer;
		this.fromApiJsonHelper = fromApiJsonHelper;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 */
	@Transactional
	@Override
	public CommandProcessingResult createGstr1Fileb2b2Item(final JsonCommand command) {

		try {
			this.apiJsonDeserializer.validaForCreate(command.json());
			final Gstr1FileB2BItem gstr1FileB2BItemData  = Gstr1FileB2BItem.fromJson(command);
			this.gstr1FileB2BItemRepository.save(gstr1FileB2BItemData);
			return new CommandProcessingResultBuilder().withCommandId(command.commandId()).withEntityId(gstr1FileB2BItemData.getId()).build();
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
	public CommandProcessingResult updateGstr1Fileb2bItem(final JsonCommand command,final Long gstr1Fileb2bItmId) {
		
		try {
			context.authenticatedUser();
			this.apiJsonDeserializer.validaForCreate(command.json());
			final Gstr1FileB2BItem gstr1FileB2BItem = retrieveGstr1Fileb2bItemById(gstr1Fileb2bItmId);
			
			final Map<String, Object> changes = gstr1FileB2BItem.update(command);
			if (!changes.isEmpty()) {
				gstr1FileB2BItemRepository.saveAndFlush(gstr1FileB2BItem);
			}

			return new CommandProcessingResultBuilder().withCommandId(command.commandId()).withEntityId(gstr1FileB2BItem.getId()).with(changes).build();
		} catch (DataIntegrityViolationException dve) {
			return new CommandProcessingResult(Long.valueOf(-1L));
		}
	}

	private Gstr1FileB2BItem retrieveGstr1Fileb2bItemById(final Long gstr1Fileb2bItmId) {
		final Gstr1FileB2BItem gstr1FileB2BItem = this.gstr1FileB2BItemRepository.findOne(gstr1Fileb2bItmId);
		if (gstr1FileB2BItem == null) {
			throw new Gstr1FileB2BItemNotFoundException(gstr1Fileb2bItmId);
		}
		return gstr1FileB2BItem;
	}

}
