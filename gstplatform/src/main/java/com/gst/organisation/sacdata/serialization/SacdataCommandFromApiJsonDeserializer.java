package com.gst.organisation.sacdata.serialization;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.gst.infrastructure.core.data.ApiParameterError;
import com.gst.infrastructure.core.data.DataValidatorBuilder;
import com.gst.infrastructure.core.exception.InvalidJsonException;
import com.gst.infrastructure.core.exception.PlatformApiDataValidationException;
import com.gst.infrastructure.core.serialization.FromJsonHelper;
import com.gst.organisation.sacdata.service.SacdataReadPlatformService;

@Component
public class SacdataCommandFromApiJsonDeserializer {
	
	private final Set<String> supportedParameters = new HashSet<>(Arrays.asList("id","sacSeqId", "serviceName", "description", "sacTaxCollection",
        "sacOtherReciept", "sacDeductRefund", "sacPenalty"));
	
	
	private final FromJsonHelper fromApiJsonHelper;

    @Autowired
    public SacdataCommandFromApiJsonDeserializer(final FromJsonHelper fromApiJsonHelper,
            final SacdataReadPlatformService sacdataReadPlatformService) {
        this.fromApiJsonHelper = fromApiJsonHelper;
    }
    
    public void validateForCreate(final String json) {
        if (StringUtils.isBlank(json)) { throw new InvalidJsonException(); }
        
        final Type typeOfMap = new TypeToken<Map<String, Object>>() {}.getType();
        this.fromApiJsonHelper.checkForUnsupportedParameters(typeOfMap, json, this.supportedParameters);

        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();
        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("sacdata");

        final JsonElement element = this.fromApiJsonHelper.parse(json);
        
        final String sacSeqId = this.fromApiJsonHelper.extractStringNamed("sacSeqId", element);
        baseDataValidator.reset().parameter("sacSeqId").value(sacSeqId).notBlank().notExceedingLengthOf(4);

        final String serviceName = this.fromApiJsonHelper.extractStringNamed("serviceName", element);
        baseDataValidator.reset().parameter("serviceName").value(serviceName).notBlank().notExceedingLengthOf(60);

        final String description = this.fromApiJsonHelper.extractStringNamed("description", element);
        baseDataValidator.reset().parameter("description").value(description).notBlank().notExceedingLengthOf(256);
        
        final String sacTaxCollection = this.fromApiJsonHelper.extractStringNamed("sacTaxCollection", element);
        baseDataValidator.reset().parameter("sacTaxCollection").value(sacTaxCollection).notBlank().notExceedingLengthOf(10);
        
        final String sacOtherReciept = this.fromApiJsonHelper.extractStringNamed("sacOtherReciept", element);
        baseDataValidator.reset().parameter("sacOtherReciept").value(sacOtherReciept).notBlank().notExceedingLengthOf(10);
        
        final String sacDeductRefund = this.fromApiJsonHelper.extractStringNamed("sacDeductRefund", element);
        baseDataValidator.reset().parameter("sacDeductRefund").value(sacDeductRefund).notBlank().notExceedingLengthOf(10);
        
        final String sacPenalty = this.fromApiJsonHelper.extractStringNamed("sacPenalty", element);
        baseDataValidator.reset().parameter("sacPenalty").value(sacPenalty).notBlank().notExceedingLengthOf(10);
    
    throwExceptionIfValidationWarningsExist(dataValidationErrors);
}
    
   
    	
        public void validateForUpdate(final String json, Long id) {
        	if (StringUtils.isBlank(json)) { throw new InvalidJsonException(); 
        	}
        	
        	final Type typeOfMap = new TypeToken<Map<String, Object>>() {}.getType();
            this.fromApiJsonHelper.checkForUnsupportedParameters(typeOfMap, json, this.supportedParameters);
            
            final List<ApiParameterError> dataValidationErrors = new ArrayList<>();
            final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("sacdata");
            
            final JsonElement element = this.fromApiJsonHelper.parse(json);
            
            if (this.fromApiJsonHelper.parameterExists("sacSeqId", element)) {
                final String sacseqid1 = this.fromApiJsonHelper.extractStringNamed("sacSeqId", element);
                baseDataValidator.reset().parameter("sacseqid").value(sacseqid1).notBlank().notExceedingLengthOf(4);
            }
            
            if (this.fromApiJsonHelper.parameterExists("serviceName", element)) {
                final String serviceName = this.fromApiJsonHelper.extractStringNamed("serviceName", element);
                baseDataValidator.reset().parameter("serviceName").value(serviceName).notBlank().notExceedingLengthOf(60);
            }
            
            if (this.fromApiJsonHelper.parameterExists("description", element)) {
                final String description = this.fromApiJsonHelper.extractStringNamed("description", element);
                baseDataValidator.reset().parameter("description").value(description).notBlank().notExceedingLengthOf(256);
            }
            
            
            if (this.fromApiJsonHelper.parameterExists("sacTaxCollection", element)) {
                final String sacTaxCollection = this.fromApiJsonHelper.extractStringNamed("sacTaxCollection", element);
                baseDataValidator.reset().parameter("sacTaxCollection").value(sacTaxCollection).notBlank().notExceedingLengthOf(10);
            }
            
            if (this.fromApiJsonHelper.parameterExists("sacOtherReciept", element)) {
                final String sacOtherReciept = this.fromApiJsonHelper.extractStringNamed("sacOtherReciept", element);
                baseDataValidator.reset().parameter("sacOtherReciept").value(sacOtherReciept).notExceedingLengthOf(10);
            }
            if (this.fromApiJsonHelper.parameterExists("sacDeductRefund", element)) {
                final String sacDeductRefund = this.fromApiJsonHelper.extractStringNamed("sacDeductRefund", element);
                baseDataValidator.reset().parameter("sacDeductRefund").value(sacDeductRefund).notBlank().notExceedingLengthOf(10);
            }
            
            if (this.fromApiJsonHelper.parameterExists("sacPenalty", element)) {
                final Boolean sacPenalty = this.fromApiJsonHelper.extractBooleanNamed("sacPenalty", element);
                baseDataValidator.reset().parameter("sacPenalty").value(sacPenalty).notBlank().notExceedingLengthOf(10);
            
    }        throwExceptionIfValidationWarningsExist(dataValidationErrors);
    
    
        }
    
    private void throwExceptionIfValidationWarningsExist(final List<ApiParameterError> dataValidationErrors) {
        if (!dataValidationErrors.isEmpty()) { throw new PlatformApiDataValidationException("validation.msg.validation.errors.exist",
                "Validation errors exist.", dataValidationErrors); }
    }
}