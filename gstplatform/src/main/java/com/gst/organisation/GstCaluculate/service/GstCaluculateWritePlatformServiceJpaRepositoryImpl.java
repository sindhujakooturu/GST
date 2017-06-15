/*package com.gst.organisation.GstCaluculate.service;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.gst.infrastructure.codes.exception.CodeNotFoundException;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.infrastructure.core.data.CommandProcessingResultBuilder;
import com.gst.infrastructure.core.exception.PlatformDataIntegrityException;
import com.gst.infrastructure.core.serialization.FromJsonHelper;
import com.gst.infrastructure.security.service.PlatformSecurityContext;
import com.gst.organisation.GstCaluculate.data.GstCaluculateData;
import com.gst.organisation.GstCaluculate.domain.GstCaluculate;
import com.gst.organisation.GstCaluculate.domain.GstCaluculateRepository;
import com.gst.organisation.GstCaluculate.serialization.GstCaluculateCommandFromApiJsonDeserializer;
import com.gst.organisation.purchaser.domain.Purchaser;

@Service
public class GstCaluculateWritePlatformServiceJpaRepositoryImpl implements GstCaluculateWritePlatformService{
	
   private final PlatformSecurityContext context;
   private final GstCaluculateCommandFromApiJsonDeserializer fromApiJsonDeserializer;
   private final GstCaluculateRepository gstCaliculaterRepository;
   private final FromJsonHelper fromApiJsonHelper;
   private final GstCaluculateReadPlatformService gstCaluculateReadPlatformService;
    
    @Autowired
    public GstCaluculateWritePlatformServiceJpaRepositoryImpl(final GstCaluculateCommandFromApiJsonDeserializer fromApiJsonDeserializer,
    		    final PlatformSecurityContext context,final GstCaluculateRepository gstCaliculaterRepository,final FromJsonHelper fromApiJsonHelper
    		    ,final GstCaluculateReadPlatformService gstCaluculateReadPlatformService) {
    	
    			this.fromApiJsonDeserializer = fromApiJsonDeserializer;
		    	this.context = context;
		        this.gstCaliculaterRepository = gstCaliculaterRepository;
		        this.fromApiJsonHelper = fromApiJsonHelper;
		        this.gstCaluculateReadPlatformService = gstCaluculateReadPlatformService;
    }
    @Transactional
    @Override
    public String createGstCaluculate(final JsonCommand command) {

    	try {
			context.authenticatedUser();
			this.fromApiJsonDeserializer.validateForCreate(command.json());

			List<GstCaluculate> itemListData = new ArrayList<GstCaluculate>();
			
			final String ssc = command.stringValueOfParameterNamed("ssc");
			final String csc = command.stringValueOfParameterNamed("csc");
			final JsonArray itemsArray = command.arrayOfParameterNamed("items").getAsJsonArray();
			if(ssc.equals(csc)){
				
				String[] itemsArrayDatas = new String[itemsArray.size()];
				for(int i = 0; i < itemsArray.size(); i++){
					itemsArrayDatas[i] = itemsArray.get(i).toString();
			     }
			
				for (final String itemsArrayData : itemsArrayDatas) {
							
						final JsonElement element = this.fromApiJsonHelper.parse(itemsArrayData);
						final String itemName = this.fromApiJsonHelper.extractStringNamed("itemName",element);
						final String itemCode = this.fromApiJsonHelper.extractStringNamed("itemCode",element);
						final BigDecimal itemAmount = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("itemAmount",element);
						final GstCaluculate gstCaluculate = retiveByItemCode(itemCode);
						
						final BigDecimal sgstRate = gstCaluculate.getSgstRate().multiply(itemAmount).divide(itemAmount,100);
						final BigDecimal cgstRate = gstCaluculate.getCgstRate().multiply(itemAmount).divide(itemAmount,100);
						
					
				}
				
				
			}else{
				String[] itemsArrayDatas = new String[itemsArray.size()];
				for(int i = 0; i < itemsArray.size(); i++){
					itemsArrayDatas[i] = itemsArray.get(i).toString();
			     }
			
				for (final String itemsArrayData : itemsArrayDatas) {
							
						final JsonElement element = this.fromApiJsonHelper.parse(itemsArrayData);
						final String itemName = this.fromApiJsonHelper.extractStringNamed("itemName",element);
						final String itemCode = this.fromApiJsonHelper.extractStringNamed("itemCode",element);
						final BigDecimal itemAmount = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("itemAmount",element);
						final GstCaluculate gstCaluculate = retiveByItemCode(itemCode);
						
						final BigDecimal igstRate = gstCaluculate.getIgstRate().multiply(itemAmount).divide(itemAmount, 100);
						final BigDecimal cessRate = gstCaluculate.getCessRate().multiply(itemAmount).divide(itemAmount,100);
						
				
				}
			}
		 	
			

		} catch (DataIntegrityViolationException dve) {
		}
    	return createGstCaluculate(command);
    }
    
    //retive prices for items using itemCode
    private  GstCaluculate retiveByItemCode(String itemCode) {
		
    	final GstCaluculate gstCaluculate = this.gstCaliculaterRepository.findOneByItemCode(itemCode);
		if (gstCaluculate == null) {
			throw new CodeNotFoundException(itemCode.toString());
		}
		return gstCaluculate;
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
}
*/