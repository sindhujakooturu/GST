package com.gst.organisation.gstr1fileinvoice.exception;

import com.gst.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class Gstr1FileB2BItemNotFoundException extends AbstractPlatformResourceNotFoundException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param OutWardStagingInvId
	 */
	public Gstr1FileB2BItemNotFoundException(final Long gstr1Fileb2bItmId) {
		super("error.msg.gstr1fileb2bitmid.not.found", "gstr1fileb2bitmid with this id"
				+ gstr1Fileb2bItmId + "not exist", gstr1Fileb2bItmId);

	}

}