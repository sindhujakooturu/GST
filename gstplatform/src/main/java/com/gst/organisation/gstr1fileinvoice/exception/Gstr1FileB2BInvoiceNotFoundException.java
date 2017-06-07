package com.gst.organisation.gstr1fileinvoice.exception;

import com.gst.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class Gstr1FileB2BInvoiceNotFoundException extends AbstractPlatformResourceNotFoundException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param OutWardStagingInvId
	 */
	public Gstr1FileB2BInvoiceNotFoundException(final Long gstr1Fileb2bInvId) {
		super("error.msg.gstr1fileb2binvid.not.found", "gstr1fileb2binvid with this id"
				+ gstr1Fileb2bInvId + "not exist", gstr1Fileb2bInvId);

	}

}