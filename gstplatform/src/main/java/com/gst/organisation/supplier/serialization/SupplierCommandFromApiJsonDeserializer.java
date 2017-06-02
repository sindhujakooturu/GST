package com.gst.organisation.supplier.serialization;

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
import com.gst.portfolio.client.api.ClientApiConstants;

@Component
public class SupplierCommandFromApiJsonDeserializer {
	
	private final Set<String> supportedParameters = new HashSet<>(Arrays.asList("id","gstin", "gstinComp", "supplierName", "contactName",
            "officePhone", "homePhone", "rmn", "fax", "rmail", "panNo", "etin","addrLine1","addrLine2","city"
            ,"state","country","pin"));

    private final FromJsonHelper fromApiJsonHelper;
    
   @Autowired
    public SupplierCommandFromApiJsonDeserializer(final FromJsonHelper fromApiJsonHelper) {
        this.fromApiJsonHelper = fromApiJsonHelper;
    }
   
    public void validateForCreate(final String json) {
        if (StringUtils.isBlank(json)) { throw new InvalidJsonException(); }

        final Type typeOfMap = new TypeToken<Map<String, Object>>() {}.getType();
        this.fromApiJsonHelper.checkForUnsupportedParameters(typeOfMap, json, this.supportedParameters);

        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();
        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("supplier");

        final JsonElement element = this.fromApiJsonHelper.parse(json);

        final String gstin = this.fromApiJsonHelper.extractStringNamed("gstin", element);
        baseDataValidator.reset().parameter("gstin").value(gstin).notBlank().notExceedingLengthOf(15);

        final String gstin_Comp = this.fromApiJsonHelper.extractStringNamed("gstinComp", element);
        baseDataValidator.reset().parameter("gstinComp").value(gstin_Comp).notBlank().notExceedingLengthOf(15);

        final String supplier_Name = this.fromApiJsonHelper.extractStringNamed("supplierName", element);
        baseDataValidator.reset().parameter("supplierName").value(supplier_Name).notBlank().notExceedingLengthOf(256);
        
        final String contact_Name = this.fromApiJsonHelper.extractStringNamed("contactName", element);
        baseDataValidator.reset().parameter("contactName").value(contact_Name).notBlank().notExceedingLengthOf(60);
        
        final String office_Phone = this.fromApiJsonHelper.extractStringNamed("officePhone", element);
        baseDataValidator.reset().parameter("officePhone").value(office_Phone).notBlank().notExceedingLengthOf(60);
        
        final String home_Phone = this.fromApiJsonHelper.extractStringNamed("homePhone", element);
        baseDataValidator.reset().parameter("homePhone").value(home_Phone).notBlank().notExceedingLengthOf(60);
        
        final String rmn = this.fromApiJsonHelper.extractStringNamed("rmn", element);
        baseDataValidator.reset().parameter("rmn").value(rmn).notBlank().notExceedingLengthOf(60);
        
        final String fax = this.fromApiJsonHelper.extractStringNamed("fax", element);
        baseDataValidator.reset().parameter("fax").value(fax).notBlank().notExceedingLengthOf(60);
        
        final String rmail = this.fromApiJsonHelper.extractStringNamed("rmail", element);
        baseDataValidator.reset().parameter("rmail").value(rmail).notBlank().notExceedingLengthOf(60);
        
        final String pan_No = this.fromApiJsonHelper.extractStringNamed("panNo", element);
        baseDataValidator.reset().parameter("panNo").value(pan_No).notBlank().notExceedingLengthOf(10);
        
        final String etin = this.fromApiJsonHelper.extractStringNamed("etin", element);
        baseDataValidator.reset().parameter("etin").value(etin).notBlank().notExceedingLengthOf(10);
        
        final String addr_Line1 = this.fromApiJsonHelper.extractStringNamed("addrLine1", element);
        baseDataValidator.reset().parameter("addrLine1").value(addr_Line1).notBlank().notExceedingLengthOf(256);
        
        final String addr_Line2 = this.fromApiJsonHelper.extractStringNamed("addrLine2", element);
        baseDataValidator.reset().parameter("addrLine2").value(addr_Line2).notBlank().notExceedingLengthOf(256);
        
        final String city = this.fromApiJsonHelper.extractStringNamed("city", element);
        baseDataValidator.reset().parameter("city").value(city).notBlank().notExceedingLengthOf(60);
        
        final String state = this.fromApiJsonHelper.extractStringNamed("state", element);
        baseDataValidator.reset().parameter("state").value(state).notBlank().notExceedingLengthOf(60);
        
        final String country = this.fromApiJsonHelper.extractStringNamed("country", element);
        baseDataValidator.reset().parameter("country").value(country).notBlank().notExceedingLengthOf(60);
        
        final String pin = this.fromApiJsonHelper.extractStringNamed("pin", element);
        baseDataValidator.reset().parameter("pin").value(pin).notBlank().notExceedingLengthOf(10);

        if (this.fromApiJsonHelper.parameterExists(ClientApiConstants.contactNameParamName, element)) {
            final String contactName = this.fromApiJsonHelper.extractStringNamed(ClientApiConstants.contactNameParamName, element);
            baseDataValidator.reset().parameter(ClientApiConstants.contactNameParamName).value(contactName).ignoreIfNull()
                    .notExceedingLengthOf(60);
        }

        if (this.fromApiJsonHelper.parameterExists("gstin", element)) {
            final String gstinp = this.fromApiJsonHelper.extractStringNamed("gstin", element);
            baseDataValidator.reset().parameter("gstin").value(gstinp).notBlank();
        }

        if (this.fromApiJsonHelper.parameterExists("gstinComp", element)) {
            final String gstinComp = this.fromApiJsonHelper.extractStringNamed("gstinComp", element);
            baseDataValidator.reset().parameter("gstinComp").value(gstinComp).notBlank();
        }
        
        if (this.fromApiJsonHelper.parameterExists("officePhone", element)) {
            final String  officePhone = this.fromApiJsonHelper.extractStringNamed("officePhone", element);
            baseDataValidator.reset().parameter("officePhone").value(officePhone).notBlank();
        }

        if (this.fromApiJsonHelper.parameterExists("rmn", element)) {
        	final String rmnn = this.fromApiJsonHelper.extractStringNamed("rmn", element);
        	baseDataValidator.reset().parameter("rmn").value(rmnn).notBlank();
        }
        
        if (this.fromApiJsonHelper.parameterExists("rmail", element)) {
        	final String rmaill = this.fromApiJsonHelper.extractStringNamed("rmail", element);
        	baseDataValidator.reset().parameter("rmn").value(rmaill).notBlank();
        }
        if (this.fromApiJsonHelper.parameterExists("pin", element)) {
        	final String pinn = this.fromApiJsonHelper.extractStringNamed("pin", element);
        	baseDataValidator.reset().parameter("pin").value(pinn).notBlank();
        }

        throwExceptionIfValidationWarningsExist(dataValidationErrors);
    }
    public void validateForUpdate(final String json) {
        validateForUpdate(json, null); 
    }
    
    public void validateForUpdate(final String json,String gstin) {
        if (StringUtils.isBlank(json)) { throw new InvalidJsonException(); }

        final Type typeOfMap = new TypeToken<Map<String, Object>>() {}.getType();
        this.fromApiJsonHelper.checkForUnsupportedParameters(typeOfMap, json, this.supportedParameters);

        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();
        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("supplier");

        final JsonElement element = this.fromApiJsonHelper.parse(json);
        if (this.fromApiJsonHelper.parameterExists("gstin", element)) {
            final String gst_in = this.fromApiJsonHelper.extractStringNamed("gstin", element);
            baseDataValidator.reset().parameter("gstin").value(gst_in).notBlank().notExceedingLengthOf(15);
        }

        if (this.fromApiJsonHelper.parameterExists("gstin_Comp", element)) {
            final String gstin_Comp = this.fromApiJsonHelper.extractStringNamed("gstin_Comp", element);
            baseDataValidator.reset().parameter("gstin_Comp").value(gstin_Comp).notBlank().notExceedingLengthOf(15);
        }

        if (this.fromApiJsonHelper.parameterExists("supplier_Name", element)) {
            final String supplier_Name = this.fromApiJsonHelper.extractStringNamed("supplier_Name", element);
            baseDataValidator.reset().parameter("supplier_Name").value(supplier_Name).notBlank().notExceedingLengthOf(256);
        }

        if (this.fromApiJsonHelper.parameterExists(ClientApiConstants.contactNameParamName, element)) {
            final String contactName = this.fromApiJsonHelper.extractStringNamed(ClientApiConstants.contactNameParamName, element);
            baseDataValidator.reset().parameter(ClientApiConstants.contactNameParamName).value(contactName).notExceedingLengthOf(60);
        }

        if (this.fromApiJsonHelper.parameterExists("home_Phone", element)) {
            final Boolean homePhone = this.fromApiJsonHelper.extractBooleanNamed("home_Phone", element);
            baseDataValidator.reset().parameter("home_Phone").value(homePhone).notNull();
        }
                
        if (this.fromApiJsonHelper.parameterExists("rmn", element)) {
            final String rmn = this.fromApiJsonHelper.extractStringNamed("rmn", element);
            baseDataValidator.reset().parameter("rmn").value(rmn).notBlank();
        }

        if (this.fromApiJsonHelper.parameterExists("fax", element)) {
        	final String fax = this.fromApiJsonHelper.extractStringNamed("fax", element);
        	baseDataValidator.reset().parameter("fax").value(fax).notBlank();
        }
        
        if (this.fromApiJsonHelper.parameterExists("rmail", element)) {
        	final String rmail = this.fromApiJsonHelper.extractStringNamed("rmail", element);
        	baseDataValidator.reset().parameter("rmail").value(rmail).notBlank();
        }
        if (this.fromApiJsonHelper.parameterExists("pan_No", element)) {
        	final String panNo = this.fromApiJsonHelper.extractStringNamed("pan_No", element);
        	baseDataValidator.reset().parameter("pan_No").value(panNo).notBlank();
        }
        if (this.fromApiJsonHelper.parameterExists("etin", element)) {
        	final String etin = this.fromApiJsonHelper.extractStringNamed("etin", element);
        	baseDataValidator.reset().parameter("pan_No").value(etin).notBlank();
        }
        if (this.fromApiJsonHelper.parameterExists("city", element)) {
        	final String city = this.fromApiJsonHelper.extractStringNamed("city", element);
        	baseDataValidator.reset().parameter("city").value(city).notBlank();
        }
        if (this.fromApiJsonHelper.parameterExists("state", element)) {
        	final String state = this.fromApiJsonHelper.extractStringNamed("state", element);
        	baseDataValidator.reset().parameter("state").value(state).notBlank();
        }
        if (this.fromApiJsonHelper.parameterExists("country", element)) {
        	final String country = this.fromApiJsonHelper.extractStringNamed("country", element);
        	baseDataValidator.reset().parameter("country").value(country).notBlank();
        }
        if (this.fromApiJsonHelper.parameterExists("pin", element)) {
        	final String pin = this.fromApiJsonHelper.extractStringNamed("pin", element);
        	baseDataValidator.reset().parameter("pin").value(pin).notBlank();
        }

        throwExceptionIfValidationWarningsExist(dataValidationErrors);
    }

    private void throwExceptionIfValidationWarningsExist(final List<ApiParameterError> dataValidationErrors) {
        if (!dataValidationErrors.isEmpty()) { throw new PlatformApiDataValidationException("validation.msg.validation.errors.exist",
                "Validation errors exist.", dataValidationErrors); }
    }
}
