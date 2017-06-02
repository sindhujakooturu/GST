package com.gst.organisation.supplier.service;


import javax.persistence.PersistenceException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.infrastructure.core.data.CommandProcessingResultBuilder;
import com.gst.infrastructure.core.exception.PlatformDataIntegrityException;
import com.gst.organisation.supplier.domain.Supplier;
import com.gst.organisation.supplier.domain.SupplierRepository;
import com.gst.organisation.supplier.serialization.SupplierCommandFromApiJsonDeserializer;

@Service
public class SupplierWritePlatformServiceJpaRepositoryImpl implements SupplierWritePlatformService{
	private final static Logger logger = LoggerFactory.getLogger(SupplierWritePlatformServiceJpaRepositoryImpl.class);

    private final SupplierCommandFromApiJsonDeserializer fromApiJsonDeserializer;
    private final SupplierRepository supplierRepository;
    
    @Autowired
    public SupplierWritePlatformServiceJpaRepositoryImpl(final SupplierCommandFromApiJsonDeserializer fromApiJsonDeserializer,
            final SupplierRepository supplierRepository) {
        this.fromApiJsonDeserializer = fromApiJsonDeserializer;
        this.supplierRepository = supplierRepository;
    }
    @Transactional
    @Override
    public CommandProcessingResult createSupplier(final JsonCommand command) {

        try {
            this.fromApiJsonDeserializer.validateForCreate(command.json());

            final Supplier supplier = Supplier.fromJson(command);

            this.supplierRepository.save(supplier);

            return new CommandProcessingResultBuilder() //
                    .withCommandId(command.commandId()) //
                    .build();
        } catch (final DataIntegrityViolationException dve) {
            handleSupplierDataIntegrityIssues(command, dve.getMostSpecificCause(), dve);
            return CommandProcessingResult.empty();
        }catch (final PersistenceException dve) {
        	Throwable throwable = ExceptionUtils.getRootCause(dve.getCause()) ;
        	handleSupplierDataIntegrityIssues(command, throwable, dve);
        	return CommandProcessingResult.empty();
        }
    }
    /*@Transactional
    @Override
    public CommandProcessingResult updateSupplier(final Long id, final JsonCommand command) {

       
    }*/
    private void handleSupplierDataIntegrityIssues(final JsonCommand command, final Throwable realCause, final Exception dve) {

        if (realCause.getMessage().contains("gstin")) {

            final String gstin = command.stringValueOfParameterNamed("gstin");
            throw new PlatformDataIntegrityException("error.msg.supplier.duplicate.gstin", "Supplier with gstin `" + gstin
                    + "` already exists", "gstin", gstin);
        } else if (realCause.getMessage().contains("gstin_comp")) {
            final String gstincomp = command.stringValueOfParameterNamed("gstin_comp");
            String Name = gstincomp;
            if (!StringUtils.isBlank(Name)) {
                final String firstname = command.stringValueOfParameterNamed("gstin_comp");
                Name = gstincomp + ", " + firstname;
            }
            throw new PlatformDataIntegrityException("error.msg.supplier.duplicate.Name", "A supplier with the given Ref gstin name '"
                    + Name + "' already exists", "Name", Name);
        }

        logger.error(dve.getMessage(), dve);
        throw new PlatformDataIntegrityException("error.msg.supplier.unknown.data.integrity.issue",
                "Unknown data integrity issue with resource: " + realCause.getMessage());
    }
	@Override
	public CommandProcessingResult updateSupplier(Long id, JsonCommand command) {
		// TODO Auto-generated method stub
		return null;
	}
}
