package com.gst.organisation.outwardstaginginv.service;

<<<<<<< HEAD
import java.math.BigDecimal;

import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.organisation.outwardstaginginv.data.OutWardStagingInvData;

/**
 * @author hugo
=======
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;

/**
 * @author Trigital
>>>>>>> upstream/master
 *
 */
public interface OutWardStagingInvWritePlatformService {

<<<<<<< HEAD
	 CommandProcessingResult createChargeCode(JsonCommand command);
	
	 CommandProcessingResult updateChargeCode(JsonCommand command,Long chargeCodeId);
	
	 BigDecimal calculateFinalAmount(OutWardStagingInvData chargeCodeData,Long clientId, Long priceId);
=======
	 CommandProcessingResult createOutWardInv(JsonCommand command);
	
	 CommandProcessingResult updateOutWardInv(JsonCommand command,Long outWardInvId);
	
>>>>>>> upstream/master
}
