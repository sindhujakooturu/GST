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
	
	private final Set<String> supportedParameters = new HashSet<>(Arrays.asList("id","itemcode", "itemdesc", "sgstrate", "cgstrate",
            "igstrate", "cessrate","itemamount","itemname"));

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

        final String itemcode = this.fromApiJsonHelper.extractStringNamed("itemcode", element);
        baseDataValidator.reset().parameter("itemcode").value(itemcode).notBlank().notExceedingLengthOf(15);

        final String sgstrate = this.fromApiJsonHelper.extractStringNamed("sgstrate", element);
        baseDataValidator.reset().parameter("sgstrate").value(sgstrate).notBlank().notExceedingLengthOf(15);

        final String cgstrate = this.fromApiJsonHelper.extractStringNamed("cgstrate", element);
        baseDataValidator.reset().parameter("cgstrate").value(cgstrate).notBlank().notExceedingLengthOf(15);
        
        final String igstrate = this.fromApiJsonHelper.extractStringNamed("igstrate", element);
        baseDataValidator.reset().parameter("igstrate").value(igstrate).notBlank().notExceedingLengthOf(60);
        
        final String cessrate = this.fromApiJsonHelper.extractStringNamed("cessrate", element);
        baseDataValidator.reset().parameter("cessrate").value(cessrate).notBlank().notExceedingLengthOf(60);
        
        final String homePhone = this.fromApiJsonHelper.extractStringNamed("homePhone", element);
        baseDataValidator.reset().parameter("homePhone").value(homePhone).notBlank().notExceedingLengthOf(60);
        
        final String itemamount = this.fromApiJsonHelper.extractStringNamed("itemamount", element);
        baseDataValidator.reset().parameter("itemamount").value(itemamount).notBlank().notExceedingLengthOf(60);
        
        final String itemname = this.fromApiJsonHelper.extractStringNamed("itemname", element);
        baseDataValidator.reset().parameter("itemname").value(itemname).notBlank().notExceedingLengthOf(60);
        
        

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
