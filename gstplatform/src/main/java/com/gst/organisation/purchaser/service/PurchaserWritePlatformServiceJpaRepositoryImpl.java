package com.gst.organisation.purchaser.service;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gst.infrastructure.codes.exception.CodeNotFoundException;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.infrastructure.core.data.CommandProcessingResultBuilder;
import com.gst.infrastructure.core.exception.PlatformDataIntegrityException;
import com.gst.infrastructure.security.service.PlatformSecurityContext;
import com.gst.organisation.purchaser.domain.Purchaser;
import com.gst.organisation.purchaser.domain.PurchaserRepository;
import com.gst.organisation.supplier.serialization.SupplierCommandFromApiJsonDeserializer;

@Service
public class PurchaserWritePlatformServiceJpaRepositoryImpl implements PurchaserWritePlatformService{
	private final PlatformSecurityContext context;
    private final SupplierCommandFromApiJsonDeserializer fromApiJsonDeserializer;
    private final PurchaserRepository purchaserRepository;
    
    @Autowired
    public PurchaserWritePlatformServiceJpaRepositoryImpl(final SupplierCommandFromApiJsonDeserializer fromApiJsonDeserializer,
    		final PurchaserRepository purchaserRepository,final PlatformSecurityContext context) {
    	this.context = context;
        this.fromApiJsonDeserializer = fromApiJsonDeserializer;
        this.purchaserRepository=purchaserRepository;
    }
    @Transactional
    @Override
    public CommandProcessingResult createPurchaser(final JsonCommand command) {

    	try {
			context.authenticatedUser();
			this.fromApiJsonDeserializer.validateForCreate(command.json());
			Purchaser purchaser = Purchaser.fromJson(command);
			this.purchaserRepository.save(purchaser);
			return new CommandProcessingResult(purchaser.getId());

		} catch (DataIntegrityViolationException dve) {
			handlePurchaserDataIntegrityIssues(command, dve);
			return CommandProcessingResult.empty();
		}
    }
    private void handlePurchaserDataIntegrityIssues(final JsonCommand command,final DataIntegrityViolationException dve) {

    	final Throwable realCause = dve.getMostSpecificCause();
		if (realCause.getMessage().contains("gstin")) {
			final String name = command.stringValueOfParameterNamed("gstin");
			throw new PlatformDataIntegrityException("error.msg.supplier.code.duplicate.name","A code with name '" + name + "' already exists", name);
		} else if (realCause.getMessage().contains("gstinComp")) {

			throw new PlatformDataIntegrityException("error.msg.supplier.type.with.given.units.already exists","A supplier with given id already exists", "gstinComp");
		}

		throw new PlatformDataIntegrityException("error.msg.cund.unknown.data.integrity.issue","Unknown data integrity issue with resource: "+ realCause.getMessage());
    }
    
    @Override
	public CommandProcessingResult updatePurchaser(final Long purchaserId,final JsonCommand command) {
		
		try {
			context.authenticatedUser();
			this.fromApiJsonDeserializer.validateForCreate(command.json());
			final Purchaser purchaser = retrieveCodeBy(purchaserId);
			final Map<String, Object> changes = purchaser.update(command);
			if (!changes.isEmpty()) {
				this.purchaserRepository.saveAndFlush(purchaser);
			}
			return new CommandProcessingResultBuilder().withCommandId(command.commandId()).withEntityId(purchaserId).with(changes).build();

		} catch (DataIntegrityViolationException dve) {
			handlePurchaserDataIntegrityIssues(command, dve);
			return new CommandProcessingResult(Long.valueOf(-1));
		}

	}

	private Purchaser retrieveCodeBy(final Long purchaserId) {
		final Purchaser purchaser = this.purchaserRepository.findOne(purchaserId);
		if (purchaser == null) {
			throw new CodeNotFoundException(purchaserId.toString());
		}
		return purchaser;
	}

}
