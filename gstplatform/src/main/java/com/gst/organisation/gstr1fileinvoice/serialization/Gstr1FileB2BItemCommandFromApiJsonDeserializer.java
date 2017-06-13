package com.gst.organisation.gstr1fileinvoice.serialization;

import java.lang.reflect.Type;
import java.math.BigDecimal;
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
public class Gstr1FileB2BItemCommandFromApiJsonDeserializer {
	
	final private Set<String> supportedParameters = new HashSet<String>(Arrays.asList("invoiceId", "fileNo", "itemType", "itemCode",
			"taxValue","igstRate","igstAmount", "cgstRate", "cgstAmount", "sgstRate", "sgstAmount","cessRate","cessAmount","status",
			"errorCode","errorDescriptor","dateFormat", "locale"));
	

	private final FromJsonHelper fromApiJsonHelper;

	@Autowired
	public Gstr1FileB2BItemCommandFromApiJsonDeserializer(final FromJsonHelper fromApiJsonHelper) {
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
		
		final String invoiceId = this.fromApiJsonHelper.extractStringNamed("invoiceId", element);
		baseDataValidator.reset().parameter("invoiceId").value(invoiceId);

		final String fileNo = this.fromApiJsonHelper.extractStringNamed("fileNo", element);
		baseDataValidator.reset().parameter("fileNo").value(fileNo);
		
		final String  itemType = this.fromApiJsonHelper.extractStringNamed("itemType", element);
		baseDataValidator.reset().parameter("itemType").value(itemType);
		
		final String itemCode = this.fromApiJsonHelper.extractStringNamed("itemCode", element);
		baseDataValidator.reset().parameter("itemCode").value(itemCode);
		
		final BigDecimal taxValue = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("taxValue", element);
		baseDataValidator.reset().parameter("taxValue").value(taxValue);
		
		final BigDecimal igstRate = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("igstRate", element);
		baseDataValidator.reset().parameter("igstRate").value(igstRate);
		
		final BigDecimal igstAmount = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("igstAmount", element);
		baseDataValidator.reset().parameter("igstAmount").value(igstAmount);
		
		final BigDecimal cgstRate = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("cgstRate", element);
		baseDataValidator.reset().parameter("cgstRate").value(cgstRate);
		
		final BigDecimal cgstAmount = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("cgstAmount", element);
		baseDataValidator.reset().parameter("cgstAmount").value(cgstAmount);
		
		final BigDecimal sgstRate = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("sgstRate", element);
		baseDataValidator.reset().parameter("sgstRate").value(sgstRate);
		
		final BigDecimal sgstAmount = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("sgstAmount", element);
		baseDataValidator.reset().parameter("sgstAmount").value(sgstAmount);
		
		final BigDecimal cessRate = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("cessRate", element);
		baseDataValidator.reset().parameter("cessRate").value(cessRate);
		
		final BigDecimal cessAmount = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("cessAmount", element);
		baseDataValidator.reset().parameter("cessAmount").value(cessAmount);
		
		final String status = this.fromApiJsonHelper.extractStringNamed("status", element);
		baseDataValidator.reset().parameter("status").value(status);
		
		final String errorCode = this.fromApiJsonHelper.extractStringNamed("errorCode", element);
		baseDataValidator.reset().parameter("errorCode").value(errorCode);
		
		final String errorDescr = this.fromApiJsonHelper.extractStringNamed("errorDescr", element);
		baseDataValidator.reset().parameter("errorDescr").value(errorDescr);
		

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
