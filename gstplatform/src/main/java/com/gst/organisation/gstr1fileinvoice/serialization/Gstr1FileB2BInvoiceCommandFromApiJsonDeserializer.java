package com.gst.organisation.gstr1fileinvoice.serialization;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
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
	
	final private Set<String> supportedParameters = new HashSet<String>(Arrays.asList("gstin", "fp", "fileNo", "supplierInvNo", "supplierInvDate",
			"supplerInvValue","supplyPlace","orderNo", "orderDate", "etin", "invoiceId", "flag","chkSum","isReverse","isProvisional","recordType","status",
			"errorCode","errorDescr","dateFormat", "locale"));
	

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
		
		final String gstin = this.fromApiJsonHelper.extractStringNamed("gstin", element);
		baseDataValidator.reset().parameter("gstin").value(gstin);

		final LocalDate fp = this.fromApiJsonHelper.extractLocalDateNamed("fp", element);
		baseDataValidator.reset().parameter("fp").value(fp);

		final String fileNo = this.fromApiJsonHelper.extractStringNamed("fileNo", element);
		baseDataValidator.reset().parameter("fileNo").value(fileNo);
		
		final String  supplierInvNo = this.fromApiJsonHelper.extractStringNamed("supplierInvNo", element);
		baseDataValidator.reset().parameter("supplierInvNo").value(supplierInvNo);
		
		final String supplierInvDate = this.fromApiJsonHelper.extractStringNamed("supplierInvDate", element);
		baseDataValidator.reset().parameter("supplierInvDate").value(supplierInvDate);
		
		final Double supplerInvValue = this.fromApiJsonHelper.extractDoubleNamed("supplerInvValue", element);
		baseDataValidator.reset().parameter("supplerInvValue").value(supplerInvValue);
		
		final String supplyPlace = this.fromApiJsonHelper.extractStringNamed("supplyPlace", element);
		baseDataValidator.reset().parameter("supplyPlace").value(supplyPlace);
		
		final String orderNo = this.fromApiJsonHelper.extractStringNamed("orderNo", element);
		baseDataValidator.reset().parameter("orderNo").value(orderNo);
		
		final LocalDate orderDate = this.fromApiJsonHelper.extractLocalDateNamed("orderDate", element);
		baseDataValidator.reset().parameter("orderDate").value(orderDate);
		
		final String etin = this.fromApiJsonHelper.extractStringNamed("etin", element);
		baseDataValidator.reset().parameter("etin").value(etin);
		
		final Long invoiceId = this.fromApiJsonHelper.extractLongNamed("invoiceId", element);
		baseDataValidator.reset().parameter("invoiceId").value(invoiceId);
		
		final String flag = this.fromApiJsonHelper.extractStringNamed("flag", element);
		baseDataValidator.reset().parameter("flag").value(flag);
		
		final String chkSum = this.fromApiJsonHelper.extractStringNamed("chkSum", element);
		baseDataValidator.reset().parameter("chkSum").value(chkSum);
		
		final Integer isReverse = this.fromApiJsonHelper.extractIntegerNamed("isReverse", element, this.fromApiJsonHelper.extractLocaleParameter(element.getAsJsonObject()));
		baseDataValidator.reset().parameter("isReverse").value(isReverse);
		
		final Integer isProvisional = this.fromApiJsonHelper.extractIntegerNamed("isProvisional", element, this.fromApiJsonHelper.extractLocaleParameter(element.getAsJsonObject()));
		baseDataValidator.reset().parameter("isProvisional").value(isProvisional);
		
		final Integer recordType = this.fromApiJsonHelper.extractIntegerNamed("recordType", element, this.fromApiJsonHelper.extractLocaleParameter(element.getAsJsonObject()));
		baseDataValidator.reset().parameter("recordType").value(recordType);
		
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
