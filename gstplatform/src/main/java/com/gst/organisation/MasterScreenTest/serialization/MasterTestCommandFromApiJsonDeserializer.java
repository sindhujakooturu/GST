package com.gst.organisation.MasterScreenTest.serialization;

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
import com.google.gson.JsonArray;
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
public class MasterTestCommandFromApiJsonDeserializer {

	/**
	 * The parameters supported for this command.
	 */
	final private Set<String> supportedParameters = new HashSet<String>(Arrays.asList("column1","column2","column3","column4",
			          "column5", "column6", "column7","column8" , "column9","column10","column11",
			          "column12", "status", "column13", "column14","dateFormat", "locale","childDetails"));
	
	private final FromJsonHelper fromApiJsonHelper;

	@Autowired
	public MasterTestCommandFromApiJsonDeserializer(final FromJsonHelper fromApiJsonHelper) {
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

		this.fromApiJsonHelper.checkForUnsupportedParameters(typeOfMap, json,this.supportedParameters);

		final List<ApiParameterError> dataValidationErrors = new ArrayList<ApiParameterError>();
		final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("outwardinv");

		final JsonElement element = this.fromApiJsonHelper.parse(json);

		final String gstin = this.fromApiJsonHelper.extractStringNamed("gstin", element);
		baseDataValidator.reset().parameter("gstin").value(gstin);

		final String gstinPurchaser = this.fromApiJsonHelper.extractStringNamed("gstinPurchaser", element);
		baseDataValidator.reset().parameter("gstinPurchaser").value(gstinPurchaser);

		final String cName = this.fromApiJsonHelper.extractStringNamed("cName", element);
		baseDataValidator.reset().parameter("cName").value(cName);
		
		final String supplierInvNo = this.fromApiJsonHelper.extractStringNamed("supplierInvNo", element);
		baseDataValidator.reset().parameter("supplierInvNo").value(supplierInvNo);
	
		final String supplierInvDate = this.fromApiJsonHelper.extractStringNamed("supplierInvDate", element);
		baseDataValidator.reset().parameter("supplierInvDate").value(supplierInvDate);

		final Long supplierInvValue = fromApiJsonHelper.extractLongNamed("supplierInvValue", element);
		baseDataValidator.reset().parameter("supplierInvValue").value(supplierInvValue);
		
		final String supplyStateCode = this.fromApiJsonHelper.extractStringNamed("supplyStateCode", element);
		baseDataValidator.reset().parameter("supplyStateCode").value(supplyStateCode);
		
		final String orderNo = this.fromApiJsonHelper.extractStringNamed("orderNo", element);
		baseDataValidator.reset().parameter("orderNo").value(orderNo);

		final String orderDate = this.fromApiJsonHelper.extractStringNamed("orderDate", element);
		baseDataValidator.reset().parameter("orderDate").value(orderDate);

		final String etin = this.fromApiJsonHelper.extractStringNamed("etin", element);
		baseDataValidator.reset().parameter("etin").value(etin);
		
		final Long invoiceId = this.fromApiJsonHelper.extractLongNamed("invoiceId", element);
		baseDataValidator.reset().parameter("invoiceId").value(invoiceId);
	
		final String receiptStateCode = this.fromApiJsonHelper.extractStringNamed("receiptStateCode", element);
		baseDataValidator.reset().parameter("receiptStateCode").value(receiptStateCode);

		final Long status = this.fromApiJsonHelper.extractLongNamed("status", element);
		baseDataValidator.reset().parameter("status").value(status);
		
		final String errorCode = this.fromApiJsonHelper.extractStringNamed("errorCode", element);
		baseDataValidator.reset().parameter("errorCode").value(errorCode);
		
		final String errorDescripter = this.fromApiJsonHelper.extractStringNamed("errorDescripter", element);
		baseDataValidator.reset().parameter("errorDescripter").value(errorDescripter);
		
		if(true == this.fromApiJsonHelper.extractBooleanNamed("isDetails", element)){
			final JsonArray outwardStagingItemsArray = this.fromApiJsonHelper.extractJsonArrayNamed("itemDetails",element);
			String[] outwardStagingItems = new String[outwardStagingItemsArray.size()];
	        final int outwardStagingItemsArraySize = outwardStagingItemsArray.size();
			baseDataValidator.reset().parameter("itemDetails").value(outwardStagingItemsArraySize).integerGreaterThanZero();
	        if(outwardStagingItemsArraySize > 0){
		    for(int i = 0; i < outwardStagingItemsArraySize; i++){
		    	outwardStagingItems[i] = outwardStagingItemsArray.get(i).toString();
		    	
		    }

			 for (final String outwardStagingItem : outwardStagingItems) {
				 
				     final JsonElement detailElement = fromApiJsonHelper.parse(outwardStagingItem);
				     
				    final String itemType = this.fromApiJsonHelper.extractStringNamed("itemType", detailElement);
					baseDataValidator.reset().parameter("itemType").value(itemType);

					final String itemCode = this.fromApiJsonHelper.extractStringNamed("itemCode", detailElement);
					baseDataValidator.reset().parameter("itemCode").value(itemCode);

					final Double taxValue = this.fromApiJsonHelper.extractDoubleNamed("taxValue", detailElement);
					baseDataValidator.reset().parameter("taxValue").value(taxValue);
						
					final Double  igstRate = this.fromApiJsonHelper.extractDoubleNamed("igstRate", detailElement);
					baseDataValidator.reset().parameter("igstRate").value(igstRate);
						
					final Double igstAmount = this.fromApiJsonHelper.extractDoubleNamed("igstAmount", detailElement);
					baseDataValidator.reset().parameter("igstAmount").value(igstAmount);
						
					final Double cgstRate = this.fromApiJsonHelper.extractDoubleNamed("cgstRate", detailElement);
					baseDataValidator.reset().parameter("cgstRate").value(cgstRate);
						
					final Double cgstAmount = this.fromApiJsonHelper.extractDoubleNamed("cgstAmount", detailElement);
					baseDataValidator.reset().parameter("cgstAmount").value(cgstAmount);
						
					final Double sgstRate = this.fromApiJsonHelper.extractDoubleNamed("sgstRate", detailElement);
					baseDataValidator.reset().parameter("sgstRate").value(sgstRate);
						
					final Double sgstAmount = this.fromApiJsonHelper.extractDoubleNamed("sgstAmount", detailElement);
					baseDataValidator.reset().parameter("sgstAmount").value(sgstAmount);
						
						
					final Double cessRate = this.fromApiJsonHelper.extractDoubleNamed("cessRate", detailElement);
					baseDataValidator.reset().parameter("cessRate").value(cessRate);
						
					final Double cessAmount = this.fromApiJsonHelper.extractDoubleNamed("cessAmount", detailElement);
					baseDataValidator.reset().parameter("cessAmount").value(cessAmount);
			  }
	        }
			
			
		}
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
