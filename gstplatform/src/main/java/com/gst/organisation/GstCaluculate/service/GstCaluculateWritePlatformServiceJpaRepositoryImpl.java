package com.gst.organisation.GstCaluculate.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amazonaws.util.json.JSONException;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.infrastructure.core.exception.PlatformDataIntegrityException;
import com.gst.infrastructure.security.service.PlatformSecurityContext;
import com.gst.organisation.GstCaluculate.data.GstCaluculateData;
import com.gst.organisation.GstCaluculate.domain.GstCaluculate;
import com.gst.organisation.GstCaluculate.domain.GstCaluculateRepository;
import com.gst.organisation.GstCaluculate.serialization.GstCaluculateCommandFromApiJsonDeserializer;
import com.gst.organisation.office.domain.Office;
import com.gst.organisation.purchaser.domain.Purchaser;
import com.gst.organisation.staff.domain.Staff;

@Service
public class GstCaluculateWritePlatformServiceJpaRepositoryImpl implements GstCaluculateWritePlatformService{
			private final PlatformSecurityContext context;
		    private final GstCaluculateCommandFromApiJsonDeserializer fromApiJsonDeserializer;
		    private final GstCaluculateRepository gstcaluculateRepository;
		    private final GstCaluculateReadPlatformServiceImpl gstCaluculateReadPlatformServiceImpl;
    
    @Autowired
    public GstCaluculateWritePlatformServiceJpaRepositoryImpl(final GstCaluculateCommandFromApiJsonDeserializer fromApiJsonDeserializer,final PlatformSecurityContext context,
    		final GstCaluculateReadPlatformServiceImpl gstCaluculateReadPlatformServiceImpl,final GstCaluculateRepository gstcaluculateRepository) {
		    	this.context = context;
		        this.fromApiJsonDeserializer = fromApiJsonDeserializer;
		        this.gstcaluculateRepository=gstcaluculateRepository;
		        this.gstCaluculateReadPlatformServiceImpl=gstCaluculateReadPlatformServiceImpl;
    }
    @Transactional
    @Override
    public CommandProcessingResult createGstCaluculate(final JsonCommand command) {

    	try {
			context.authenticatedUser();
			this.fromApiJsonDeserializer.validateForCreate(command.json());
			GstCaluculate caluculate = GstCaluculate.fromJson(command);
			this.gstcaluculateRepository.save(caluculate);
			
			//this.gstcaluculateRepositoryWrapper.findCaluculation(sgstrate, igstrate);
			
		 	//this.gstCaluculateReadPlatformServiceImpl.retrieveAllCaluculations(sgstrate, igstrate);
		 	
			return new CommandProcessingResult(caluculate.getId());

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
	public CommandProcessingResult createGstCaluculate(JsonCommand command,
			Double sgstrate, Double igstrate) {
		// TODO Auto-generated method stub
		return null;
	}
}
