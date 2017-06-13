package com.gst.organisation.outwardstaginginv.exception;

import com.gst.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class OutWardStagingItemNotFoundException extends AbstractPlatformResourceNotFoundException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param OutWardStagingInvoiceId
	 */
	public OutWardStagingItemNotFoundException(final Long outWardInvoiceId) {
		super("error.msg.outwarditemid.not.found", "outWardItemId with this id"
				+ outWardInvoiceId + "not exist", outWardInvoiceId);

	}

}