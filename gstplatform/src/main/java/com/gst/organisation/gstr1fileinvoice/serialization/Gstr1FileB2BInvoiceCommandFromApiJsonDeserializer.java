package com.gst.organisation.gstr1fileinvoice.serialization;

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

@Component
public class Gstr1FileB2BInvoiceCommandFromApiJsonDeserializer {
	
	final private Set<String> supportedParameters = new HashSet<String>(Arrays.asList("invoiceId", "itemType", "itemCode", "taxValue", "igstRate",
			"igstAmount","cgstRate","cgstAmount", "sgstRate", "sgstAmount", "cessRate", "cessAmount","dateFormat", "locale"));

	private final FromJsonHelper fromApiJsonHelper;

	@Autowired
	public Gstr1FileB2BInvoiceCommandFromApiJsonDeserializer(final FromJsonHelper fromApiJsonHelper) {
		this.fromApiJsonHelper = fromApiJsonHelper;
	}

	
	public void validaForCreate(String json) {
		if (StringUtils.isBlank(json)) {
			throw new InvalidJsonException();
		}

		final Type typeOfMap = new TypeToken<Map<String, Object>>() {
			private static final long serialVersionUID = 1L;
		}.getType();

		fromApiJsonHelper.checkForUnsupportedParameters(typeOfMap, json,supportedParameters);

		final List<ApiParameterError> dataValidationErrors = new ArrayList<ApiParameterError>();
		final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("outwarditem");

		final JsonElement element = this.fromApiJsonHelper.parse(json);

		baseDataValidator.reset().parameter("invoiceId").value(invoiceId);
		
		final String itemType = this.fromApiJsonHelper.extractStringNamed("itemType", element);
		baseDataValidator.reset().parameter("itemType").value(itemType);

		final String itemCode = this.fromApiJsonHelper.extractStringNamed("itemCode", element);
		baseDataValidator.reset().parameter("itemCode").value(itemCode);

		final Double taxValue = this.fromApiJsonHelper.extractDoubleNamed("taxValue", element);
		baseDataValidator.reset().parameter("taxValue").value(taxValue);
		
		final Double  igstRate = this.fromApiJsonHelper.extractDoubleNamed("igstRate", element);
		baseDataValidator.reset().parameter("igstRate").value(igstRate);
		
		final Double igstAmount = this.fromApiJsonHelper.extractDoubleNamed("igstAmount", element);
		baseDataValidator.reset().parameter("igstAmount").value(igstAmount);
		
		final Double cgstRate = this.fromApiJsonHelper.extractDoubleNamed("cgstRate", element);
		baseDataValidator.reset().parameter("cgstRate").value(cgstRate);
		
		final Double cgstAmount = this.fromApiJsonHelper.extractDoubleNamed("cgstAmount", element);
		baseDataValidator.reset().parameter("cgstAmount").value(cgstAmount);
		
		final Double sgstRate = this.fromApiJsonHelper.extractDoubleNamed("sgstRate", element);
		baseDataValidator.reset().parameter("sgstRate").value(sgstRate);
		
		final Double sgstAmount = this.fromApiJsonHelper.extractDoubleNamed("sgstAmount", element);
		baseDataValidator.reset().parameter("sgstAmount").value(sgstAmount);
		
		
		final Double cessRate = this.fromApiJsonHelper.extractDoubleNamed("cessRate", element);
		baseDataValidator.reset().parameter("cessRate").value(cessRate);
		
		final Double cessAmount = this.fromApiJsonHelper.extractDoubleNamed("cessAmount", element);
		baseDataValidator.reset().parameter("cessAmount").value(cessAmount);
	

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
