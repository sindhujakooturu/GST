package com.gst.organisation.outwardstaginginv.serialization;

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
public class OutWardStagingInvCommandFromApiJsonDeserializer {

	/**
	 * The parameters supported for this command.
	 */
	final private Set<String> supportedParameters = new HashSet<String>(Arrays.asList("gstin","gstinPurchaser","cName","supplierInvNo",
			          "supplierInvDate", "supplierInvValue", "supplyStateCode","orderNo" , "orderDate","etin","invoiceId",
			          "receiptStateCode", "status", "errorCode", "errorDescripter","dateFormat", "locale"));
	
	private final FromJsonHelper fromApiJsonHelper;

	@Autowired
	public OutWardStagingInvCommandFromApiJsonDeserializer(final FromJsonHelper fromApiJsonHelper) {
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
		final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("outwardinv");

		final JsonElement element = fromApiJsonHelper.parse(json);

		final String gstin = fromApiJsonHelper.extractStringNamed("gstin", element);
		baseDataValidator.reset().parameter("gstin").value(gstin);

		final String gstinPurchaser = fromApiJsonHelper.extractStringNamed("gstinPurchaser", element);
		baseDataValidator.reset().parameter("gstinPurchaser").value(gstinPurchaser);

		final String cName = fromApiJsonHelper.extractStringNamed("cName", element);
		baseDataValidator.reset().parameter("cName").value(cName);
		
		final String supplierInvNo = fromApiJsonHelper.extractStringNamed("supplierInvNo", element);
		baseDataValidator.reset().parameter("supplierInvNo").value(supplierInvNo);
	
		final String supplierInvDate = fromApiJsonHelper.extractStringNamed("supplierInvDate", element);
		baseDataValidator.reset().parameter("supplierInvDate").value(supplierInvDate);

		final Long supplierInvValue = fromApiJsonHelper.extractLongNamed("supplierInvValue", element);
		baseDataValidator.reset().parameter("supplierInvValue").value(supplierInvValue);
		
		final String supplyStateCode = fromApiJsonHelper.extractStringNamed("supplyStateCode", element);
		baseDataValidator.reset().parameter("supplyStateCode").value(supplyStateCode);
		
		final String orderNo = fromApiJsonHelper.extractStringNamed("orderNo", element);
		baseDataValidator.reset().parameter("orderNo").value(orderNo);

		final String orderDate = fromApiJsonHelper.extractStringNamed("orderDate", element);
		baseDataValidator.reset().parameter("orderDate").value(orderDate);

		final String etin = fromApiJsonHelper.extractStringNamed("etin", element);
		baseDataValidator.reset().parameter("etin").value(etin);
		
		final Long invoiceId = fromApiJsonHelper.extractLongNamed("invoiceId", element);
		baseDataValidator.reset().parameter("invoiceId").value(invoiceId);
	
		final String receiptStateCode = fromApiJsonHelper.extractStringNamed("receiptStateCode", element);
		baseDataValidator.reset().parameter("receiptStateCode").value(receiptStateCode);

		final Long status = fromApiJsonHelper.extractLongNamed("status", element);
		baseDataValidator.reset().parameter("status").value(status);
		
		final String errorCode = fromApiJsonHelper.extractStringNamed("errorCode", element);
		baseDataValidator.reset().parameter("errorCode").value(errorCode);
		
		final String errorDescripter = fromApiJsonHelper.extractStringNamed("errorDescripter", element);
		baseDataValidator.reset().parameter("errorDescripter").value(errorDescripter);

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
