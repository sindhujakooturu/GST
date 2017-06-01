package com.gst.organisation.outwardstaginginv.service;

<<<<<<< HEAD
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
=======
>>>>>>> upstream/master
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
<<<<<<< HEAD

import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.infrastructure.core.data.CommandProcessingResultBuilder;
import com.gst.infrastructure.core.exception.PlatformDataIntegrityException;
import com.gst.infrastructure.security.service.PlatformSecurityContext;
import com.gst.organisation.outwardstaginginv.data.OutWardStagingInvData;
=======
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.infrastructure.core.data.CommandProcessingResultBuilder;
import com.gst.infrastructure.security.service.PlatformSecurityContext;
>>>>>>> upstream/master
import com.gst.organisation.outwardstaginginv.domain.OutWardStagingInv;
import com.gst.organisation.outwardstaginginv.domain.OutWardStagingInvRepository;
import com.gst.organisation.outwardstaginginv.exception.OutWardStagingInvNotFoundException;
import com.gst.organisation.outwardstaginginv.serialization.OutWardStagingInvCommandFromApiJsonDeserializer;

/**
<<<<<<< HEAD
 * @author hugo
=======
 * @author Trigital
>>>>>>> upstream/master
 * 
 */
@Service
public class OutWardStagingInvWritePlatformServiceImp implements OutWardStagingInvWritePlatformService {

	private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(OutWardStagingInvWritePlatformServiceImp.class);

	private final PlatformSecurityContext context;
<<<<<<< HEAD
	private final OutWardStagingInvRepository chargeCodeRepository;
	private final OutWardStagingInvCommandFromApiJsonDeserializer apiJsonDeserializer;
	private final DiscountMasterRepository discountMasterRepository;
	private final PriceRepository priceRepository;
    private final GenerateBill generateBill;
    private final ClientRepositoryWrapper clientRepository;
    
	@Autowired
	public OutWardStagingInvWritePlatformServiceImp(final PlatformSecurityContext context,final OutWardStagingInvRepository chargeCodeRepository,
			final OutWardStagingInvCommandFromApiJsonDeserializer apiJsonDeserializer,final DiscountMasterRepository discountMasterRepository,
			final PriceRepository priceRepository,final GenerateBill generateBill,final ClientRepositoryWrapper clientRepository) {
		
		this.context = context;
		this.chargeCodeRepository = chargeCodeRepository;
		this.apiJsonDeserializer = apiJsonDeserializer;
		this.discountMasterRepository = discountMasterRepository;
		this.priceRepository = priceRepository;
		this.generateBill = generateBill;
		this.clientRepository = clientRepository;
=======
	private final OutWardStagingInvRepository outWardStagingInvRepository;
	private final OutWardStagingInvCommandFromApiJsonDeserializer apiJsonDeserializer;
    
	@Autowired
	public OutWardStagingInvWritePlatformServiceImp(final PlatformSecurityContext context,final OutWardStagingInvRepository outWardStagingInvRepository,
			final OutWardStagingInvCommandFromApiJsonDeserializer apiJsonDeserializer) {
		
		this.context = context;
		this.outWardStagingInvRepository = outWardStagingInvRepository;
		this.apiJsonDeserializer = apiJsonDeserializer;
>>>>>>> upstream/master

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
<<<<<<< HEAD
	 * #createChargeCode(org.mifosplatform.infrastructure.core.api.JsonCommand)
	 */
	@Transactional
	@Override
	public CommandProcessingResult createChargeCode(final JsonCommand command) {

		OutWardStagingInv chargeCode = null;
		try {
			context.authenticatedUser();
			this.apiJsonDeserializer.validaForCreate(command.json());
			chargeCode = OutWardStagingInv.fromJson(command);
			this.chargeCodeRepository.save(chargeCode);
			return new CommandProcessingResultBuilder().withCommandId(command.commandId())
					  .withEntityId(chargeCode.getId()).build();
=======
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
>>>>>>> upstream/master
		} catch (final DataIntegrityViolationException dve) {
			handleDataIntegrityIssues(command, dve);
			return new CommandProcessingResult(Long.valueOf(-1L));
		}
	}

	private void handleDataIntegrityIssues(final JsonCommand command,
			final DataIntegrityViolationException dve) {
<<<<<<< HEAD
		final Throwable realCause = dve.getMostSpecificCause();
		if (realCause.getMessage().contains("chargecode")) {
			throw new PlatformDataIntegrityException(
					"error.msg.chargecode.duplicate.name", "A code with name'"
							+ command.stringValueOfParameterNamed("chargeCode")
							+ "'already exists", "chargeCode",
					command.stringValueOfParameterNamed("chargeCode"));
=======
		/*final Throwable realCause = dve.getMostSpecificCause();
		if (realCause.getMessage().contains("chargecode")) {
			throw new PlatformDataIntegrityException("error.msg.chargecode.duplicate.name", "A code with name'"
			    + command.stringValueOfParameterNamed("chargeCode")+ "'already exists", "chargeCode",command.stringValueOfParameterNamed("chargeCode"));
>>>>>>> upstream/master
		}

		if (realCause.getMessage().contains("chargedescription")) {
			throw new PlatformDataIntegrityException(
					"error.msg.chargecode.duplicate.name",
<<<<<<< HEAD
					"A description with name'"
							+ command
									.stringValueOfParameterNamed("charge_description")
							+ "'already exists", "chargeDescription",
					command.stringValueOfParameterNamed("charge_description"));
=======
					"A description with name'" + command.stringValueOfParameterNamed("charge_description")
					 + "'already exists", "chargeDescription", command.stringValueOfParameterNamed("charge_description"));
>>>>>>> upstream/master
		}
		
		if (realCause.getMessage().contains("foreign key constraint")) {
			throw new PlatformDataIntegrityException(
					"error.msg.chargecode.can not.delete or update already used",
<<<<<<< HEAD
					"A code with name'"
							+ command
									.stringValueOfParameterNamed("chargeCode")
							+ "'already used", "chargeCode",
					command.stringValueOfParameterNamed("chargeCode"));
		}
=======
					"A code with name'" + command.stringValueOfParameterNamed("chargeCode")
					+ "'already used", "chargeCode",command.stringValueOfParameterNamed("chargeCode"));
		}*/
>>>>>>> upstream/master
		LOGGER.error(dve.getMessage(), dve);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
<<<<<<< HEAD
	 * #updateChargeCode(org.mifosplatform.infrastructure.core.api.JsonCommand,
=======
>>>>>>> upstream/master
	 * java.lang.Long)
	 */
	@Transactional
	@Override
<<<<<<< HEAD
	public CommandProcessingResult updateChargeCode(final JsonCommand command,final Long chargeCodeId) {
		OutWardStagingInv chargeCode = null;
		try {
			context.authenticatedUser();
			this.apiJsonDeserializer.validaForCreate(command.json());
			chargeCode = retrieveChargeCodeById(chargeCodeId);
			final Map<String, Object> changes = chargeCode.update(command);
			if (!changes.isEmpty()) {
				chargeCodeRepository.saveAndFlush(chargeCode);
			}

			return new CommandProcessingResultBuilder()
					.withCommandId(command.commandId())
					.withEntityId(chargeCode.getId()).with(changes).build();
		} catch (DataIntegrityViolationException dve) {
			if (dve.getCause() instanceof ConstraintViolationException) {
				handleDataIntegrityIssues(command, dve);
			}
=======
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
>>>>>>> upstream/master
			return new CommandProcessingResult(Long.valueOf(-1L));
		}
	}

<<<<<<< HEAD
	private OutWardStagingInv retrieveChargeCodeById(final Long chargeCodeId) {
		final OutWardStagingInv chargeCode = this.chargeCodeRepository
				.findOne(chargeCodeId);
		if (chargeCode == null) {
			throw new OutWardStagingInvNotFoundException(chargeCodeId.toString());
		}
		return chargeCode;
	}
	/*@Override
	public BigDecimal calculateFinalAmount(OutWardStagingInvData chargeCodeData,Long clientId,Long priceId) {
		
		Long defaultValue=Long.valueOf(0);
		Date defaultDate= DateUtils.getDateOfTenant();
		Price price = this.priceRepository.findOne(priceId);
		BigDecimal finalAmount=BigDecimal.ZERO;
		DiscountMaster discountMaster = this.discountMasterRepository.findOne(price.getDiscountId());
		LocalDate endDate=new LocalDate(discountMaster.getStartDate()).plusMonths(1);
		ChargeCodeMaster chargeCode = this.chargeCodeRepository.findOne(chargeCodeData.getId());
		Client client = this.clientRepository.findOneWithNotFoundDetection(clientId);
		BillingOrderData billingOrderData = new BillingOrderData(defaultValue,defaultValue,defaultValue,clientId,discountMaster.getStartDate(),
				defaultDate,defaultDate,chargeCodeData.getBillFrequencyCode(),chargeCode.getChargeCode(),chargeCode.getChargeType(),chargeCode.getChargeDuration(),
				chargeCode.getChargeType(),defaultDate,price.getPrice(),"",discountMaster.getStartDate(),defaultDate,defaultValue,chargeCode.getTaxInclusive(),String.valueOf(client.getTaxExemption())); 
		
		DiscountMasterData discountMasterData = new DiscountMasterData(discountMaster.getId(),defaultValue,defaultValue,new LocalDate(discountMaster.getStartDate()),
							DateUtils.getLocalDateOfTenant(),discountMaster.getDiscountType(),discountMaster.getDiscountRate(),"N",discountMaster.getDiscountCode(),discountMaster.getDiscountDescription());
		
		List<InvoiceTaxCommand> invoiceTaxCommands=this.generateBill.calculateDiscountAndTax(billingOrderData, discountMasterData, new LocalDate(discountMaster.getStartDate()),endDate, price.getPrice());
		if(!invoiceTaxCommands.isEmpty()){
		finalAmount = invoiceTaxCommands.get(0).getDiscountedAmount();
		}else {
			finalAmount= price.getPrice();
		}
		if(chargeCode.getTaxInclusive() !=1){
		for(InvoiceTaxCommand invoiceTaxCommand:invoiceTaxCommands){
			finalAmount = finalAmount.add(invoiceTaxCommand.getTaxAmount()); 
		}
		}
		return finalAmount;
	}*/
=======
	private OutWardStagingInv retrieveChargeCodeById(final Long outWardInvId) {
		final OutWardStagingInv outWardStagingInv = this.outWardStagingInvRepository.findOne(outWardInvId);
		if (outWardStagingInv == null) {
			throw new OutWardStagingInvNotFoundException(outWardInvId);
		}
		return outWardStagingInv;
	}
>>>>>>> upstream/master

}
