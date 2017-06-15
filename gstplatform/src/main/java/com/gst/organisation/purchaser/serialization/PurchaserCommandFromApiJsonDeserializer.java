package com.gst.organisation.purchaser.serialization;

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
public class PurchaserCommandFromApiJsonDeserializer {
	
	private final Set<String> supportedParameters = new HashSet<>(Arrays.asList("id","gstin", "gstinComp", "purchaserName", "contactName",
            "officePhone", "homePhone", "rmn", "fax", "rmail", "panNo", "etin","addrLine1","addrLine2","city","state","country","pin"));

    private final FromJsonHelper fromApiJsonHelper;
    
   @Autowired
    public PurchaserCommandFromApiJsonDeserializer(final FromJsonHelper fromApiJsonHelper) {
        this.fromApiJsonHelper = fromApiJsonHelper;
    }
   
    public void validateForCreate(final String json) {
        if (StringUtils.isBlank(json)) { throw new InvalidJsonException(); }

        final Type typeOfMap = new TypeToken<Map<String, Object>>() {}.getType();
        this.fromApiJsonHelper.checkForUnsupportedParameters(typeOfMap, json, this.supportedParameters);

        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();
        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("purchaser");

        final JsonElement element = this.fromApiJsonHelper.parse(json);

        final String gstin = this.fromApiJsonHelper.extractStringNamed("gstin", element);
        baseDataValidator.reset().parameter("gstin").value(gstin).notBlank().notExceedingLengthOf(15);

        final String gstinComp = this.fromApiJsonHelper.extractStringNamed("gstinComp", element);
        baseDataValidator.reset().parameter("gstinComp").value(gstinComp).notBlank().notExceedingLengthOf(15);

        final String purchaserName = this.fromApiJsonHelper.extractStringNamed("purchaserName", element);
        baseDataValidator.reset().parameter("purchaserName").value(purchaserName).notBlank().notExceedingLengthOf(256);
        
        final String contactName = this.fromApiJsonHelper.extractStringNamed("contactName", element);
        baseDataValidator.reset().parameter("contactName").value(contactName).notBlank().notExceedingLengthOf(60);
        
        final String officePhone = this.fromApiJsonHelper.extractStringNamed("officePhone", element);
        baseDataValidator.reset().parameter("officePhone").value(officePhone).notBlank().notExceedingLengthOf(60);
        
        final String homePhone = this.fromApiJsonHelper.extractStringNamed("homePhone", element);
        baseDataValidator.reset().parameter("homePhone").value(homePhone).notBlank().notExceedingLengthOf(60);
        
        final String rmn = this.fromApiJsonHelper.extractStringNamed("rmn", element);
        baseDataValidator.reset().parameter("rmn").value(rmn).notBlank().notExceedingLengthOf(60);
        
        final String fax = this.fromApiJsonHelper.extractStringNamed("fax", element);
        baseDataValidator.reset().parameter("fax").value(fax).notBlank().notExceedingLengthOf(60);
        
        final String rmail = this.fromApiJsonHelper.extractStringNamed("rmail", element);
        baseDataValidator.reset().parameter("rmail").value(rmail).notBlank().notExceedingLengthOf(60);
        
        final String panNo = this.fromApiJsonHelper.extractStringNamed("panNo", element);
        baseDataValidator.reset().parameter("panNo").value(panNo).notBlank().notExceedingLengthOf(10);
        
        final String etin = this.fromApiJsonHelper.extractStringNamed("etin", element);
        baseDataValidator.reset().parameter("etin").value(etin).notBlank().notExceedingLengthOf(10);
        
        final String addrLine1 = this.fromApiJsonHelper.extractStringNamed("addrLine1", element);
        baseDataValidator.reset().parameter("addrLine1").value(addrLine1).notBlank().notExceedingLengthOf(256);
        
        final String addrLine2 = this.fromApiJsonHelper.extractStringNamed("addrLine2", element);
        baseDataValidator.reset().parameter("addrLine2").value(addrLine2).notBlank().notExceedingLengthOf(256);
        
        final String city = this.fromApiJsonHelper.extractStringNamed("city", element);
        baseDataValidator.reset().parameter("city").value(city).notBlank().notExceedingLengthOf(60);
        
        final String state = this.fromApiJsonHelper.extractStringNamed("state", element);
        baseDataValidator.reset().parameter("state").value(state).notBlank().notExceedingLengthOf(60);
        
        final String country = this.fromApiJsonHelper.extractStringNamed("country", element);
        baseDataValidator.reset().parameter("country").value(country).notBlank().notExceedingLengthOf(60);
        
        final String pin = this.fromApiJsonHelper.extractStringNamed("pin", element);
        baseDataValidator.reset().parameter("pin").value(pin).notBlank().notExceedingLengthOf(10);

        throwExceptionIfValidationWarningsExist(dataValidationErrors);
    }  
    public void validateForUpdate(final String json) {
    	if (StringUtils.isBlank(json)) { throw new InvalidJsonException(); }

        final Type typeOfMap = new TypeToken<Map<String, Object>>() {}.getType();
        fromApiJsonHelper.checkForUnsupportedParameters(typeOfMap, json, supportedParameters);

        final List<ApiParameterError> dataValidationErrors = new ArrayList<ApiParameterError>();
        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("purchaser");

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
