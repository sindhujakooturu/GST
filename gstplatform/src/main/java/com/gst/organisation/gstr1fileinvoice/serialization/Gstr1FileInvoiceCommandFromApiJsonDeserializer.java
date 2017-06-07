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

/**
 * @author Deserializer for code JSON to validate API request.
 */
@Component
public class Gstr1FileInvoiceCommandFromApiJsonDeserializer {

	/**
	 * The parameters supported for this command.
	 */
	final private Set<String> supportedParameters = new HashSet<String>(Arrays.asList("gstin","fp","grossTurnover","fileNo","version",
			           "status", "assignedTo","errorCode" , "errorDescr","reviewComments","refNo","transId","dateFormat", "locale"));
			          
	
	
	private final FromJsonHelper fromApiJsonHelper;

	@Autowired
	public Gstr1FileInvoiceCommandFromApiJsonDeserializer(final FromJsonHelper fromApiJsonHelper) {
		this.fromApiJsonHelper = fromApiJsonHelper;
	}

	/**
	 * @param json
	 * check validation for create gstr1FileInvoice codes
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
		final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("gstr1fileinvoice");

		final JsonElement element = this.fromApiJsonHelper.parse(json);

		final String gstin = this.fromApiJsonHelper.extractStringNamed("gstin", element);
		baseDataValidator.reset().parameter("gstin").value(gstin);

		final LocalDate fp = this.fromApiJsonHelper.extractLocalDateNamed("fp", element);
		baseDataValidator.reset().parameter("fp").value(fp);

		final String grossTurnover = this.fromApiJsonHelper.extractStringNamed("grossTurnover", element);
		baseDataValidator.reset().parameter("grossTurnover").value(grossTurnover);
		
		final String fileNo = this.fromApiJsonHelper.extractStringNamed("fileNo", element);
		baseDataValidator.reset().parameter("fileNo").value(fileNo);
	
		final Integer version = this.fromApiJsonHelper.extractIntegerWithLocaleNamed("version", element);
		baseDataValidator.reset().parameter("version").value(version);

		final Integer status = fromApiJsonHelper.extractIntegerWithLocaleNamed("status", element);
		baseDataValidator.reset().parameter("status").value(status);
		
		final String assignedTo = this.fromApiJsonHelper.extractStringNamed("assignedTo", element);
		baseDataValidator.reset().parameter("assignedTo").value(assignedTo);
		
		final String errorCode = this.fromApiJsonHelper.extractStringNamed("errorCode", element);
		baseDataValidator.reset().parameter("errorCode").value(errorCode);

		final String errorDescr = this.fromApiJsonHelper.extractStringNamed("errorDescr", element);
		baseDataValidator.reset().parameter("errorDescr").value(errorDescr);

		final String reviewComments = this.fromApiJsonHelper.extractStringNamed("reviewComments", element);
		baseDataValidator.reset().parameter("reviewComments").value(reviewComments);
		
		final Long refNo = this.fromApiJsonHelper.extractLongNamed("refNo", element);
		baseDataValidator.reset().parameter("refNo").value(refNo);
	
		final String transId = this.fromApiJsonHelper.extractStringNamed("transId", element);
		baseDataValidator.reset().parameter("transId").value(transId);

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
