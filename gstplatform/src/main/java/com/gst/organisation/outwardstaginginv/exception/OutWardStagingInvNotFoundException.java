package com.gst.organisation.outwardstaginginv.exception;

import com.gst.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

/**
<<<<<<< HEAD
 * @author hugo
=======
 * @author 
>>>>>>> upstream/master
 * 
 *         this class {@link RuntimeException} thrown when a code is not found.
 */

<<<<<<< HEAD
public class OutWardStagingInvNotFoundException extends
		AbstractPlatformResourceNotFoundException {
=======
public class OutWardStagingInvNotFoundException extends AbstractPlatformResourceNotFoundException {
>>>>>>> upstream/master

	private static final long serialVersionUID = 1L;

	/**
<<<<<<< HEAD
	 * @param chargeCodeId
	 */
	public OutWardStagingInvNotFoundException(final String chargeCodeId) {
		super("error.msg.chargeCode.not.found", "chargeCode with this id"
				+ chargeCodeId + "not exist", chargeCodeId);
=======
	 * @param OutWardStagingInvId
	 */
	public OutWardStagingInvNotFoundException(final Long outWardInvId) {
		super("error.msg.outwardinvid.not.found", "outWardInvId with this id"
				+ outWardInvId + "not exist", outWardInvId);
>>>>>>> upstream/master

	}

}