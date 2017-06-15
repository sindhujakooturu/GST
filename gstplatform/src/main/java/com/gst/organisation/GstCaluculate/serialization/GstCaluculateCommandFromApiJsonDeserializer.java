package com.gst.organisation.GstCaluculate.serialization;

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

@Component
public class GstCaluculateCommandFromApiJsonDeserializer {
	
	private final Set<String> supportedParameters = new HashSet<>(Arrays.asList("id","itemCode", "itemDesc", "sgstRate", "cgstRate",
            "igstRate", "cessRate","itemAmount","itemName"));

    private final FromJsonHelper fromApiJsonHelper;
    
   @Autowired
    public GstCaluculateCommandFromApiJsonDeserializer(final FromJsonHelper fromApiJsonHelper) {
        this.fromApiJsonHelper = fromApiJsonHelper;
    }
   
    public void validateForCreate(final String json) {
        if (StringUtils.isBlank(json)) { throw new InvalidJsonException(); }

        final Type typeOfMap = new TypeToken<Map<String, Object>>() {}.getType();
        this.fromApiJsonHelper.checkForUnsupportedParameters(typeOfMap, json, this.supportedParameters);

        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();
        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("gstcaluculate");

        final JsonElement element = this.fromApiJsonHelper.parse(json);

        final String itemCode = this.fromApiJsonHelper.extractStringNamed("itemCode", element);
        baseDataValidator.reset().parameter("itemCode").value(itemCode).notBlank().notExceedingLengthOf(15);

        final String sgstRate = this.fromApiJsonHelper.extractStringNamed("sgstRate", element);
        baseDataValidator.reset().parameter("sgstRate").value(sgstRate).notBlank().notExceedingLengthOf(15);

        final String cgstRate = this.fromApiJsonHelper.extractStringNamed("cgstRate", element);
        baseDataValidator.reset().parameter("cgstRate").value(cgstRate).notBlank().notExceedingLengthOf(15);
        
        final String igstRate = this.fromApiJsonHelper.extractStringNamed("igstRate", element);
        baseDataValidator.reset().parameter("igstRate").value(igstRate).notBlank().notExceedingLengthOf(60);
        
        final String cessRate = this.fromApiJsonHelper.extractStringNamed("cessRate", element);
        baseDataValidator.reset().parameter("cessRate").value(cessRate).notBlank().notExceedingLengthOf(60);
        
        final String itemAmount = this.fromApiJsonHelper.extractStringNamed("itemAmount", element);
        baseDataValidator.reset().parameter("itemAmount").value(itemAmount).notBlank().notExceedingLengthOf(60);
        
        final String itemName = this.fromApiJsonHelper.extractStringNamed("itemName", element);
        baseDataValidator.reset().parameter("itemName").value(itemName).notBlank().notExceedingLengthOf(60);
        
        

        throwExceptionIfValidationWarningsExist(dataValidationErrors);
    }  
    public void validateForUpdate(final String json) {
    	if (StringUtils.isBlank(json)) { throw new InvalidJsonException(); }

        final Type typeOfMap = new TypeToken<Map<String, Object>>() {}.getType();
        fromApiJsonHelper.checkForUnsupportedParameters(typeOfMap, json, supportedParameters);

        final List<ApiParameterError> dataValidationErrors = new ArrayList<ApiParameterError>();
        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("gstcaluculate");

        final JsonElement element = fromApiJsonHelper.parse(json);
        if (fromApiJsonHelper.parameterExists("name", element)) {
            final String name = fromApiJsonHelper.extractStringNamed("name", element);
            baseDataValidator.reset().parameter("name").value(name).notBlank().notExceedingLengthOf(100);
        }

        throwExceptionIfValidationWarningsExist(dataValidationErrors);
    }

    private void throwExceptionIfValidationWarningsExist(final List<ApiParameterError> dataValidationErrors) {
        if (!dataValidationErrors.isEmpty()) { throw new PlatformApiDataValidationException("validation.msg.validation.errors.exist",
                "Validation errors exist.", dataValidationErrors); }
    }
}
