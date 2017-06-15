package com.gst.organisation.hsndata.serialization;

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
import com.gst.organisation.hsndata.service.HsndataReadPlatformService;

@Component
public class HsndataCommandFromApiJsonDeserializer {
	
	private final Set<String> supportedParameters = new HashSet<>(Arrays.asList("id","hsnCode", "description"));
	private final FromJsonHelper fromApiJsonHelper;

    @Autowired
    public HsndataCommandFromApiJsonDeserializer(final FromJsonHelper fromApiJsonHelper,
            final HsndataReadPlatformService hsndataReadPlatformService) {
        this.fromApiJsonHelper = fromApiJsonHelper;
    }
    
    public void validateForCreate(final String json) {
        if (StringUtils.isBlank(json)) { throw new InvalidJsonException(); }
        
        final Type typeOfMap = new TypeToken<Map<String, Object>>() {}.getType();
        this.fromApiJsonHelper.checkForUnsupportedParameters(typeOfMap, json, this.supportedParameters);

        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();
        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("hsndata");

        final JsonElement element = this.fromApiJsonHelper.parse(json);
        
        final String hsnCode = this.fromApiJsonHelper.extractStringNamed("hsnCode", element);
        baseDataValidator.reset().parameter("hsnCode").value(hsnCode).notBlank().notExceedingLengthOf(8);


        final String description = this.fromApiJsonHelper.extractStringNamed("description", element);
        baseDataValidator.reset().parameter("description").value(description).notBlank().notExceedingLengthOf(256);
        
    
    	throwExceptionIfValidationWarningsExist(dataValidationErrors);
}
   
    	
        public void validateForUpdate(final String json) {
        	if (StringUtils.isBlank(json)) { throw new InvalidJsonException(); 
        	}
        	
        	final Type typeOfMap = new TypeToken<Map<String, Object>>() {}.getType();
            this.fromApiJsonHelper.checkForUnsupportedParameters(typeOfMap, json, this.supportedParameters);
            final List<ApiParameterError> dataValidationErrors = new ArrayList<>();
            final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("hsndata");
            final JsonElement element = this.fromApiJsonHelper.parse(json);
            
            
            if (this.fromApiJsonHelper.parameterExists("hsnCode", element)) {
                final String hsnCode = this.fromApiJsonHelper.extractStringNamed("hsnCode", element);
                baseDataValidator.reset().parameter("hsnCode").value(hsnCode).notBlank().notExceedingLengthOf(8);
            }
            
            if (this.fromApiJsonHelper.parameterExists("description", element)) {
                final String description = this.fromApiJsonHelper.extractStringNamed("description", element);
                baseDataValidator.reset().parameter("description").value(description).notBlank().notExceedingLengthOf(256);
            
    }    
            
            throwExceptionIfValidationWarningsExist(dataValidationErrors);
    
    
        }
    
    private void throwExceptionIfValidationWarningsExist(final List<ApiParameterError> dataValidationErrors) {
        if (!dataValidationErrors.isEmpty()) { throw new PlatformApiDataValidationException("validation.msg.validation.errors.exist",
                "Validation errors exist.", dataValidationErrors); }
    }
}