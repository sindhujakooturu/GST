package com.gst.organisation.gstr1fileinvoice.exception;

import com.gst.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class Gstr1FileInvoiceNotFoundException extends AbstractPlatformResourceNotFoundException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param OutWardStagingInvId
	 */
	public Gstr1FileInvoiceNotFoundException(final Long gstr1FileInvId) {
		super("error.msg.gstr1fileinvid.not.found", "gstr1fileinvid with this id"
				+ gstr1FileInvId + "not exist", gstr1FileInvId);

	}

}