package com.gst.organisation.outwardstaginginv.exception;

import com.gst.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

/**
 * @author hugo
 * 
 *         this class {@link RuntimeException} thrown when a code is not found.
 */

public class OutWardStagingInvNotFoundException extends
		AbstractPlatformResourceNotFoundException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param chargeCodeId
	 */
	public OutWardStagingInvNotFoundException(final String chargeCodeId) {
		super("error.msg.chargeCode.not.found", "chargeCode with this id"
				+ chargeCodeId + "not exist", chargeCodeId);

	}

}