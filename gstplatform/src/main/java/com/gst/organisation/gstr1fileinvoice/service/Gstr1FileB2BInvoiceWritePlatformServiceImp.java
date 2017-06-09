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
import com.gst.organisation.gstr1fileinvoice.domain.Gstr1FileB2BInvoice;
import com.gst.organisation.gstr1fileinvoice.domain.Gstr1FileB2BInvoiceRepository;
import com.gst.organisation.gstr1fileinvoice.exception.Gstr1FileB2BInvoiceNotFoundException;
import com.gst.organisation.gstr1fileinvoice.serialization.Gstr1FileB2BInvoiceCommandFromApiJsonDeserializer;

/**
 * @author Trigital
 * 
 */
@Service
public class Gstr1FileB2BInvoiceWritePlatformServiceImp implements Gstr1FileB2BInvoiceWritePlatformService {

	private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(Gstr1FileB2BInvoiceWritePlatformServiceImp.class);

	private final PlatformSecurityContext context;
	private final Gstr1FileB2BInvoiceRepository gstr1FileB2BInvoiceRepository;
	private final Gstr1FileB2BInvoiceCommandFromApiJsonDeserializer apiJsonDeserializer;
	private final FromJsonHelper fromApiJsonHelper;
    
	@Autowired
	public Gstr1FileB2BInvoiceWritePlatformServiceImp(final PlatformSecurityContext context,final Gstr1FileB2BInvoiceRepository gstr1FileB2BInvoiceRepository,
			final Gstr1FileB2BInvoiceCommandFromApiJsonDeserializer apiJsonDeserializer,
			final FromJsonHelper fromApiJsonHelper) {
		
		this.context = context;
		this.gstr1FileB2BInvoiceRepository = gstr1FileB2BInvoiceRepository;
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
	public CommandProcessingResult createGstr1Fileb2b2Invoice(final JsonCommand command) {

		try {
			this.apiJsonDeserializer.validaForCreate(command.json());
			final Gstr1FileB2BInvoice gstr1FileB2BInvoiceData  = Gstr1FileB2BInvoice.fromJson(command);
			this.gstr1FileB2BInvoiceRepository.save(gstr1FileB2BInvoiceData);
			return new CommandProcessingResultBuilder().withCommandId(command.commandId()).withEntityId(gstr1FileB2BInvoiceData.getId()).build();
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
	public CommandProcessingResult updateGstr1Fileb2bInvoice(final JsonCommand command,final Long gstr1Fileb2bInvId) {
		
		try {
			context.authenticatedUser();
			this.apiJsonDeserializer.validaForCreate(command.json());
			final Gstr1FileB2BInvoice gstr1FileB2BInvoice = retrieveGstr1Fileb2bInvoiceById(gstr1Fileb2bInvId);
			
			final Map<String, Object> changes = gstr1FileB2BInvoice.update(command);
			if (!changes.isEmpty()) {
				gstr1FileB2BInvoiceRepository.saveAndFlush(gstr1FileB2BInvoice);
			}

			return new CommandProcessingResultBuilder().withCommandId(command.commandId()).withEntityId(gstr1FileB2BInvoice.getId()).with(changes).build();
		} catch (DataIntegrityViolationException dve) {
			return new CommandProcessingResult(Long.valueOf(-1L));
		}
	}

	private Gstr1FileB2BInvoice retrieveGstr1Fileb2bInvoiceById(final Long gstr1Fileb2bInvId) {
		final Gstr1FileB2BInvoice gstr1FileB2BInvoice = this.gstr1FileB2BInvoiceRepository.findOne(gstr1Fileb2bInvId);
		if (gstr1FileB2BInvoice == null) {
			throw new Gstr1FileB2BInvoiceNotFoundException(gstr1Fileb2bInvId);
		}
		return gstr1FileB2BInvoice;
	}

}
