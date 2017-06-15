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
import com.gst.organisation.gstr1fileinvoice.domain.Gstr1FileInvoice;
import com.gst.organisation.gstr1fileinvoice.domain.Gstr1FileInvoiceRepository;
import com.gst.organisation.gstr1fileinvoice.exception.Gstr1FileInvoiceNotFoundException;
import com.gst.organisation.gstr1fileinvoice.serialization.Gstr1FileInvoiceCommandFromApiJsonDeserializer;

/**
 * @author Trigital
 * 
 */
@Service
public class Gstr1FileInvoiceWritePlatformServiceImp implements Gstr1FileInvoiceWritePlatformService {

	private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(Gstr1FileInvoiceWritePlatformServiceImp.class);

	private final PlatformSecurityContext context;
	private final Gstr1FileInvoiceRepository gstr1FileInvoiceRepository;
	private final Gstr1FileInvoiceCommandFromApiJsonDeserializer apiJsonDeserializer;
	private final FromJsonHelper fromApiJsonHelper;
    
	@Autowired
	public Gstr1FileInvoiceWritePlatformServiceImp(final PlatformSecurityContext context,final Gstr1FileInvoiceRepository gstr1FileInvoiceRepository,
			final Gstr1FileInvoiceCommandFromApiJsonDeserializer apiJsonDeserializer,
			final FromJsonHelper fromApiJsonHelper) {
		
		this.context = context;
		this.gstr1FileInvoiceRepository = gstr1FileInvoiceRepository;
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
	public CommandProcessingResult createGstr1FileInvoice(final JsonCommand command) {

		try {
			this.apiJsonDeserializer.validaForCreate(command.json());
			final Gstr1FileInvoice gstr1FileInvoice  = Gstr1FileInvoice.fromJson(command);
			this.gstr1FileInvoiceRepository.save(gstr1FileInvoice);
			return new CommandProcessingResultBuilder().withCommandId(command.commandId()).withEntityId(gstr1FileInvoice.getId()).build();
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
	public CommandProcessingResult updateGstr1FileInvoice(final JsonCommand command,final Long gstr1FileInvId) {
		
		try {
			context.authenticatedUser();
			this.apiJsonDeserializer.validaForCreate(command.json());
			final Gstr1FileInvoice gstr1FileInvoice = retrieveChargeCodeById(gstr1FileInvId);
			
			final Map<String, Object> changes = gstr1FileInvoice.update(command);
			if (!changes.isEmpty()) {
				gstr1FileInvoiceRepository.saveAndFlush(gstr1FileInvoice);
			}

			return new CommandProcessingResultBuilder().withCommandId(command.commandId()).withEntityId(gstr1FileInvoice.getId()).with(changes).build();
		} catch (DataIntegrityViolationException dve) {
			return new CommandProcessingResult(Long.valueOf(-1L));
		}
	}

	private Gstr1FileInvoice retrieveChargeCodeById(final Long gstr1FileInvId) {
		final Gstr1FileInvoice gstr1FileInvoice = this.gstr1FileInvoiceRepository.findOne(gstr1FileInvId);
		if (gstr1FileInvoice == null) {
			throw new Gstr1FileInvoiceNotFoundException(gstr1FileInvId);
		}
		return gstr1FileInvoice;
	}

}
