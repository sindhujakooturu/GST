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
import com.gst.organisation.gstr1fileinvoice.serialization.Gstr1FileB2BInvoiceCommandFromApiJsonDeserializer;
import com.gst.organisation.outwardstaginginv.domain.OutWardStagingInv;
import com.gst.organisation.outwardstaginginv.exception.OutWardStagingInvNotFoundException;

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
			final Gstr1FileB2BInvoice utWardStagingInvData  = Gstr1FileB2BInvoice.fromJson(command);
			this.gstr1FileB2BInvoiceRepository.save(utWardStagingInvData);
			//if(true == command.booleanPrimitiveValueOfParameterNamed("isDetails")){this.addOutwardStagingItemDetails(command);}			
			return new CommandProcessingResultBuilder().withCommandId(command.commandId()).withEntityId(utWardStagingInvData.getId()).build();
		} catch (final DataIntegrityViolationException dve) {
			handleDataIntegrityIssues(command, dve);
			return new CommandProcessingResult(Long.valueOf(-1L));
		}
	}

	/*private void addOutwardStagingItemDetails(JsonCommand command) {
		final Long invoiceId = command.longValueOfParameterNamed("invoiceId");
		List<OutWardStagingItem> outWardStagingItemsList = new ArrayList<OutWardStagingItem>();
		final JsonArray outWardStagingItemArray = command.arrayOfParameterNamed("itemDetails").getAsJsonArray();
		String[] outWardStagingItems = new String[outWardStagingItemArray.size()];
		for(int i = 0; i < outWardStagingItemArray.size(); i++){
			outWardStagingItems[i] = outWardStagingItemArray.get(i).toString();
	}
	
		for (final String outWardStagingItem : outWardStagingItems) {
					
				final JsonElement element = this.fromApiJsonHelper.parse(outWardStagingItem);
				final String itemType = this.fromApiJsonHelper.extractStringNamed("itemType",element);
				final String itemCode = this.fromApiJsonHelper.extractStringNamed("itemCode",element);
				final Double taxValue = this.fromApiJsonHelper.extractDoubleNamed("taxValue",element);
				final Double igstRate = this.fromApiJsonHelper.extractDoubleNamed("igstRate",element);
				final Double igstAmount = this.fromApiJsonHelper.extractDoubleNamed("igstAmount",element);
				final Double cgstRate = this.fromApiJsonHelper.extractDoubleNamed("cgstRate",element);
				final Double cgstAmount = this.fromApiJsonHelper.extractDoubleNamed("cgstAmount",element);
				final Double sgstRate = this.fromApiJsonHelper.extractDoubleNamed("sgstRate",element);
				final Double sgstAmount = this.fromApiJsonHelper.extractDoubleNamed("sgstAmount",element);
				final Double cessRate = this.fromApiJsonHelper.extractDoubleNamed("cessRate",element);
				final Double cessAmount = this.fromApiJsonHelper.extractDoubleNamed("cessAmount",element);
			
				outWardStagingItemsList.add(new OutWardStagingItem(invoiceId, itemType, itemCode, taxValue, igstRate, igstAmount, cgstRate, cgstAmount,
					sgstRate, sgstAmount, cessRate, cessAmount));
			
		}
		this.outWardStagingItemRepository.save(outWardStagingItemsList);
	}*/

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
		
		//OutWardStagingInv chargeCode = null;
		try {
			context.authenticatedUser();
			this.apiJsonDeserializer.validaForCreate(command.json());
			final Gstr1FileB2BInvoice outWardStagingInv = retrieveChargeCodeById(outWardInvId);
			
			final Map<String, Object> changes = outWardStagingInv.update(command);
			if (!changes.isEmpty()) {
				gstr1FileB2BInvoiceRepository.saveAndFlush(outWardStagingInv);
			}

			return new CommandProcessingResultBuilder().withCommandId(command.commandId()).withEntityId(outWardStagingInv.getId()).with(changes).build();
		} catch (DataIntegrityViolationException dve) {
			/*if (dve.getCause() instanceof ConstraintViolationException) {
				handleDataIntegrityIssues(command, dve);
			}*/
			return new CommandProcessingResult(Long.valueOf(-1L));
		}
	}

	private Gstr1FileB2BInvoice retrieveChargeCodeById(final Long outWardInvId) {
		final Gstr1FileB2BInvoice outWardStagingInv = this.gstr1FileB2BInvoiceRepository.findOne(outWardInvId);
		if (outWardStagingInv == null) {
			throw new OutWardStagingInvNotFoundException(outWardInvId);
		}
		return outWardStagingInv;
	}

}
