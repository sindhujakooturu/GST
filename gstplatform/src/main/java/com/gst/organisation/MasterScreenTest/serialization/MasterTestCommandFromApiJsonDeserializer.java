package com.gst.organisation.MasterScreenTest.serialization;

import java.lang.reflect.Type;
import java.math.BigDecimal;
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
		final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("mastertest");

		final JsonElement element = this.fromApiJsonHelper.parse(json);

		final String column1 = this.fromApiJsonHelper.extractStringNamed("column1", element);
		baseDataValidator.reset().parameter("column1").value(column1);

		final String column2 = this.fromApiJsonHelper.extractStringNamed("column2", element);
		baseDataValidator.reset().parameter("column2").value(column2);

		final String column3 = this.fromApiJsonHelper.extractStringNamed("column3", element);
		baseDataValidator.reset().parameter("column3").value(column3);
		
		final String column4 = this.fromApiJsonHelper.extractStringNamed("column4", element);
		baseDataValidator.reset().parameter("column4").value(column4);
	
		final LocalDate column5 = this.fromApiJsonHelper.extractLocalDateNamed("column5",element);
		baseDataValidator.reset().parameter("column5").value(column5);

		final BigDecimal column6 = fromApiJsonHelper.extractBigDecimalWithLocaleNamed("column6", element);
		baseDataValidator.reset().parameter("column6").value(column6);
		
		final String column7 = this.fromApiJsonHelper.extractStringNamed("column7", element);
		baseDataValidator.reset().parameter("column7").value(column7);
		
		final String column8 = this.fromApiJsonHelper.extractStringNamed("column8", element);
		baseDataValidator.reset().parameter("column8").value(column8);

		final LocalDate column9 = this.fromApiJsonHelper.extractLocalDateNamed("column9", element);
		baseDataValidator.reset().parameter("column9").value(column9);

		final String column10 = this.fromApiJsonHelper.extractStringNamed("column10", element);
		baseDataValidator.reset().parameter("column10").value(column10);
		
		final Long column11 = this.fromApiJsonHelper.extractLongNamed("column11", element);
		baseDataValidator.reset().parameter("column11").value(column11);
	
		final String column12 = this.fromApiJsonHelper.extractStringNamed("column12", element);
		baseDataValidator.reset().parameter("column12").value(column12);

		final Integer status = this.fromApiJsonHelper.extractIntegerWithLocaleNamed("status", element);
		baseDataValidator.reset().parameter("status").value(status);
		
		final String column13 = this.fromApiJsonHelper.extractStringNamed("column13", element);
		baseDataValidator.reset().parameter("column13").value(column13);
		
		final String column14 = this.fromApiJsonHelper.extractStringNamed("column14", element);
		baseDataValidator.reset().parameter("column14").value(column14);
		
		/*if(true == this.fromApiJsonHelper.extractBooleanNamed("isDetails", element)){*/
			final JsonArray testMasterDetailsArray = this.fromApiJsonHelper.extractJsonArrayNamed("childDetails",element);
			String[] testChildDetails = new String[testMasterDetailsArray.size()];
	        final int testChildDetailsArraySize = testMasterDetailsArray.size();
			baseDataValidator.reset().parameter("childDetails").value(testChildDetailsArraySize).integerGreaterThanZero();
	        if(testChildDetailsArraySize > 0){
		    for(int i = 0; i < testChildDetailsArraySize; i++){
		    	testChildDetails[i] = testMasterDetailsArray.get(i).toString();
		    	
		    }

			 for (final String testChildDetail : testChildDetails) {
				 
				     final JsonElement detailElement = fromApiJsonHelper.parse(testChildDetail);
				     
				    final Long invoiceId = this.fromApiJsonHelper.extractLongNamed("invoiceId", detailElement);
					baseDataValidator.reset().parameter("invoiceId").value(invoiceId);

					final String column15 = this.fromApiJsonHelper.extractStringNamed("column15", detailElement);
					baseDataValidator.reset().parameter("column15").value(column15);

					final String column16 = this.fromApiJsonHelper.extractStringNamed("column16", detailElement);
					baseDataValidator.reset().parameter("column16").value(column16);
						
					final BigDecimal  column17 = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("column17", detailElement);
					baseDataValidator.reset().parameter("column17").value(column17);
						
					final BigDecimal column18 = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("column18", detailElement);
					baseDataValidator.reset().parameter("column18").value(column18);
						
					final BigDecimal column19 = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("column19", detailElement);
					baseDataValidator.reset().parameter("column19").value(column19);
						
					final BigDecimal column20 = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("column20", detailElement);
					baseDataValidator.reset().parameter("column20").value(column20);
						
					final BigDecimal column21 = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("column21", detailElement);
					baseDataValidator.reset().parameter("column21").value(column21);
						
					final BigDecimal column22 = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("column22", detailElement);
					baseDataValidator.reset().parameter("column22").value(column22);
						
						
					final BigDecimal column23 = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("column23", detailElement);
					baseDataValidator.reset().parameter("column23").value(column23);
					
					final BigDecimal column24 = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("column24", detailElement);
					baseDataValidator.reset().parameter("column24").value(column24);
					
					final BigDecimal column25 = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("column25", detailElement);
					baseDataValidator.reset().parameter("column25").value(column25);
					
					/*final Integer status = this.fromApiJsonHelper.extractIntegerWithLocaleNamed("status",detailElement);
					baseDataValidator.reset().parameter("status").value(status);*/
					
					final String column26 = this.fromApiJsonHelper.extractStringNamed("column26", detailElement);
					baseDataValidator.reset().parameter("column26").value(column26);
						
					final String column27 = this.fromApiJsonHelper.extractStringNamed("column27", detailElement);
					baseDataValidator.reset().parameter("column27").value(column27);
			  }
	        }
			
			
		/*}*/
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
