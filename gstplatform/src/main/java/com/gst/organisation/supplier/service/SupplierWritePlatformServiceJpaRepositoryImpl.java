package com.gst.organisation.supplier.service;



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
import com.gst.organisation.supplier.domain.Supplier;
import com.gst.organisation.supplier.domain.SupplierRepository;
import com.gst.organisation.supplier.serialization.SupplierCommandFromApiJsonDeserializer;

@Service
public class SupplierWritePlatformServiceJpaRepositoryImpl implements SupplierWritePlatformService{
	private final PlatformSecurityContext context;
    private final SupplierCommandFromApiJsonDeserializer fromApiJsonDeserializer;
    private final SupplierRepository supplierRepository;
    
    @Autowired
    public SupplierWritePlatformServiceJpaRepositoryImpl(final SupplierCommandFromApiJsonDeserializer fromApiJsonDeserializer,
    		final SupplierRepository supplierRepository,final PlatformSecurityContext context) {
			    	this.context = context;
			        this.fromApiJsonDeserializer = fromApiJsonDeserializer;
			        this.supplierRepository=supplierRepository;
    }
    @Transactional
    @Override
    public CommandProcessingResult createSupplier(final JsonCommand command) {

    	try {
			context.authenticatedUser();
			this.fromApiJsonDeserializer.validateForCreate(command.json());
			Supplier supplier = Supplier.fromJson(command);
			this.supplierRepository.save(supplier);
			return new CommandProcessingResult(supplier.getId());
    		} 
    		catch (DataIntegrityViolationException dve) {
			handleSupplierDataIntegrityIssues(command, dve);
			return CommandProcessingResult.empty();
		}
    }
    private void handleSupplierDataIntegrityIssues(final JsonCommand command,final DataIntegrityViolationException dve) {

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
	public CommandProcessingResult updateSupplier(final Long supplierId,final JsonCommand command) {
		
		try {
			context.authenticatedUser();
			this.fromApiJsonDeserializer.validateForCreate(command.json());
			final Supplier supplier = retrieveCodeBy(supplierId);
			final Map<String, Object> changes = supplier.update(command);
			if (!changes.isEmpty()) {
				this.supplierRepository.saveAndFlush(supplier);
			}
			return new CommandProcessingResultBuilder().withCommandId(command.commandId()).withEntityId(supplierId).with(changes).build();

		} catch (DataIntegrityViolationException dve) {
			handleSupplierDataIntegrityIssues(command, dve);
			return new CommandProcessingResult(Long.valueOf(-1));
		}

	}

	private Supplier retrieveCodeBy(final Long supplierId) {
		final Supplier supplier = this.supplierRepository.findOne(supplierId);
		if (supplier == null) {
			throw new CodeNotFoundException(supplierId.toString());
		}
		return supplier;
	}

}
