package com.gst.organisation.outwardstaginginv.service;

import java.math.BigDecimal;

import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.organisation.outwardstaginginv.data.OutWardStagingInvData;

/**
 * @author hugo
 *
 */
public interface OutWardStagingInvWritePlatformService {

	 CommandProcessingResult createChargeCode(JsonCommand command);
	
	 CommandProcessingResult updateChargeCode(JsonCommand command,Long chargeCodeId);
	
	 BigDecimal calculateFinalAmount(OutWardStagingInvData chargeCodeData,Long clientId, Long priceId);
}
