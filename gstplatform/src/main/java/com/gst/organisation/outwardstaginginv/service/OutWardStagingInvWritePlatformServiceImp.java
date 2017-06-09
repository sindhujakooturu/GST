package com.gst.organisation.outwardstaginginv.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.infrastructure.core.data.CommandProcessingResultBuilder;
import com.gst.infrastructure.core.serialization.FromJsonHelper;
import com.gst.infrastructure.security.service.PlatformSecurityContext;
import com.gst.organisation.outwardstaginginv.domain.OutWardStagingInv;
import com.gst.organisation.outwardstaginginv.domain.OutWardStagingInvRepository;
import com.gst.organisation.outwardstaginginv.domain.OutWardStagingItem;
import com.gst.organisation.outwardstaginginv.domain.OutWardStagingItemRepository;
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
	private final FromJsonHelper fromApiJsonHelper;
	private final OutWardStagingItemRepository outWardStagingItemRepository; 
    
	@Autowired
	public OutWardStagingInvWritePlatformServiceImp(final PlatformSecurityContext context,final OutWardStagingInvRepository outWardStagingInvRepository,
			final OutWardStagingInvCommandFromApiJsonDeserializer apiJsonDeserializer,
			final FromJsonHelper fromApiJsonHelper, final OutWardStagingItemRepository outWardStagingItemRepository) {
		
		this.context = context;
		this.outWardStagingInvRepository = outWardStagingInvRepository;
		this.apiJsonDeserializer = apiJsonDeserializer;
		this.fromApiJsonHelper = fromApiJsonHelper;
		this.outWardStagingItemRepository = outWardStagingItemRepository;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 */
	@Transactional
	@Override
	public CommandProcessingResult createOutWardInv(final JsonCommand command) {

		try {
			this.apiJsonDeserializer.validaForCreate(command.json());
			final OutWardStagingInv utWardStagingInvData  = OutWardStagingInv.fromJson(command);
			this.outWardStagingInvRepository.save(utWardStagingInvData);
			if(true == command.booleanPrimitiveValueOfParameterNamed("isDetails")){this.addOutwardStagingItemDetails(command);}			
			return new CommandProcessingResultBuilder().withCommandId(command.commandId()).withEntityId(utWardStagingInvData.getId()).build();
		} catch (final DataIntegrityViolationException dve) {
			handleDataIntegrityIssues(command, dve);
			return new CommandProcessingResult(Long.valueOf(-1L));
		}
	}

	private void addOutwardStagingItemDetails(JsonCommand command) {
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
				final BigDecimal taxValue = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("taxValue",element);
				final BigDecimal igstRate = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("igstRate",element);
				final BigDecimal igstAmount = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("igstAmount",element);
				final BigDecimal cgstRate = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("cgstRate",element);
				final BigDecimal cgstAmount = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("cgstAmount",element);
				final BigDecimal sgstRate = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("sgstRate",element);
				final BigDecimal sgstAmount = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("sgstAmount",element);
				final BigDecimal cessRate = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("cessRate",element);
				final BigDecimal cessAmount = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("cessAmount",element);
			
				outWardStagingItemsList.add(new OutWardStagingItem(invoiceId, itemType, itemCode, taxValue, igstRate, igstAmount, cgstRate, cgstAmount,
					sgstRate, sgstAmount, cessRate, cessAmount));
			
		}
		this.outWardStagingItemRepository.save(outWardStagingItemsList);
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
			final OutWardStagingInv outWardStagingInv = retrieveOutWardInvById(outWardInvId);
			
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

	private OutWardStagingInv retrieveOutWardInvById(final Long outWardInvId) {
		final OutWardStagingInv outWardStagingInv = this.outWardStagingInvRepository.findOne(outWardInvId);
		if (outWardStagingInv == null) {
			throw new OutWardStagingInvNotFoundException(outWardInvId);
		}
		return outWardStagingInv;
	}

}
