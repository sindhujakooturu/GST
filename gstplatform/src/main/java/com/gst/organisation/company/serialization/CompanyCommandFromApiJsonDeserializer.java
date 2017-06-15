package com.gst.organisation.company.serialization;

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

import com.google.common.reflect.TypeToken;
import com.google.gson.JsonElement;
import com.gst.infrastructure.core.data.ApiParameterError;
import com.gst.infrastructure.core.data.DataValidatorBuilder;
import com.gst.infrastructure.core.exception.InvalidJsonException;
import com.gst.infrastructure.core.exception.PlatformApiDataValidationException;
import com.gst.infrastructure.core.serialization.FromJsonHelper;

/**
 * @author Deserializer for code JSON to validate API request.
 */
@Component
public class CompanyCommandFromApiJsonDeserializer {

	/**
	 * The parameters supported for this command.
	 */
	final private Set<String> supportedParameters = new HashSet<String>(Arrays.asList("gstin","companyName","contactName","officePhone","homePhone",
			          "mobile", "fax", "email","gstnRegNo" , "panNo","addressLine1","addressLine2",
			          "city", "state", "country", "pin","officeId","dateFormat", "locale"));
	
	
	private final FromJsonHelper fromApiJsonHelper;

	@Autowired
	public CompanyCommandFromApiJsonDeserializer(final FromJsonHelper fromApiJsonHelper) {
		this.fromApiJsonHelper = fromApiJsonHelper;
	}

	/**
	 * @param json
	 * check validation for create OutWardStagingInv codes
	 */

	public void validaForCreate(String json) {
		if (StringUtils.isBlank(json)) {
			throw new InvalidJsonException();
		}

		final Type typeOfMap = new TypeToken<Map<String, Object>>() {
			private static final long serialVersionUID = 1L;
		}.getType();

		fromApiJsonHelper.checkForUnsupportedParameters(typeOfMap, json,supportedParameters);

		final List<ApiParameterError> dataValidationErrors = new ArrayList<ApiParameterError>();
		final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("company");

		final JsonElement element = fromApiJsonHelper.parse(json);

		final String companyName = fromApiJsonHelper.extractStringNamed("companyName", element);
		baseDataValidator.reset().parameter("companyName").value(companyName);

		final String contactName = fromApiJsonHelper.extractStringNamed("contactName", element);
		baseDataValidator.reset().parameter("contactName").value(contactName);

		final String officePhone = fromApiJsonHelper.extractStringNamed("officePhone", element);
		baseDataValidator.reset().parameter("officePhone").value(officePhone);
		
		final String homePhone = fromApiJsonHelper.extractStringNamed("homePhone", element);
		baseDataValidator.reset().parameter("homePhone").value(homePhone);
	
		final String mobile = fromApiJsonHelper.extractStringNamed("mobile", element);
		baseDataValidator.reset().parameter("mobile").value(mobile);

		final String fax = fromApiJsonHelper.extractStringNamed("fax", element);
		baseDataValidator.reset().parameter("fax").value(fax);
		
		final String email = fromApiJsonHelper.extractStringNamed("email", element);
		baseDataValidator.reset().parameter("email").value(email);
		
		final String gstnRegNo = fromApiJsonHelper.extractStringNamed("gstnRegNo", element);
		baseDataValidator.reset().parameter("gstnRegNo").value(gstnRegNo);

		final String panNo = fromApiJsonHelper.extractStringNamed("panNo", element);
		baseDataValidator.reset().parameter("panNo").value(panNo);

		final String addressLine1 = fromApiJsonHelper.extractStringNamed("addressLine1", element);
		baseDataValidator.reset().parameter("addressLine1").value(addressLine1);
		
		final String addressLine2 = fromApiJsonHelper.extractStringNamed("addressLine2", element);
		baseDataValidator.reset().parameter("addressLine2").value(addressLine2);
	
		final String city = fromApiJsonHelper.extractStringNamed("city", element);
		baseDataValidator.reset().parameter("city").value(city);

		final String state = fromApiJsonHelper.extractStringNamed("state", element);
		baseDataValidator.reset().parameter("state").value(state);
		
		final String country = fromApiJsonHelper.extractStringNamed("country", element);
		baseDataValidator.reset().parameter("country").value(country);
		
		final String pin = fromApiJsonHelper.extractStringNamed("pin", element);
		baseDataValidator.reset().parameter("pin").value(pin);
		
		final Long officeId = fromApiJsonHelper.extractLongNamed("officeId", element);
		baseDataValidator.reset().parameter("officeId").value(officeId);

		throwExceptionIfValidationWarningsExist(dataValidationErrors);

	}

	private void throwExceptionIfValidationWarningsExist(
			final List<ApiParameterError> dataValidationErrors) {
		if (!dataValidationErrors.isEmpty()) {
			throw new PlatformApiDataValidationException(
					"validation.msg.validation.errors.exist",
					"Validation errors exist.", dataValidationErrors);
		}
	}
}
