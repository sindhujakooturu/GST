package com.gst.organisation.b2binvoice.service;

import javax.persistence.PersistenceException;

import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.infrastructure.core.data.CommandProcessingResultBuilder;
import com.gst.organisation.b2binvoice.domain.B2BInvoice;
import com.gst.organisation.b2binvoice.domain.B2BInvoiceRepository;
import com.gst.organisation.b2binvoice.exception.B2BInvoiceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class B2BInvoiceWritePlatformServiceImpl implements B2BInvoiceWritePlatformService {

    private final B2BInvoiceRepository b2BInvoiceRepository;
    
	@Autowired
	public B2BInvoiceWritePlatformServiceImpl(final B2BInvoiceRepository b2BInvoiceRepository) {
		
		this.b2BInvoiceRepository = b2BInvoiceRepository;
	}



	@Override
	public CommandProcessingResult updateB2BInvoiceStatus(Long id,JsonCommand command) {

        try {

            B2BInvoice b2bInvoice = this.retriveB2BInvoice(id);
            if(!b2bInvoice.isStatus()){
            	b2bInvoice.setStatus(true);
            	this.b2BInvoiceRepository.saveAndFlush(b2bInvoice);
            }

            return new CommandProcessingResultBuilder().withCommandId(command.commandId()).withEntityId(id).build();
        } catch (final DataIntegrityViolationException dve) {
            return CommandProcessingResult.empty();
        }catch (final PersistenceException dve) {
        	return CommandProcessingResult.empty();
        }
	}



	private B2BInvoice retriveB2BInvoice(final Long id) {
		B2BInvoice b2bInvoice = this.b2BInvoiceRepository.findOne(id);
		if(b2bInvoice == null){
			throw new B2BInvoiceNotFoundException(id);
		}else{
			return b2bInvoice;
		}
	}

}
