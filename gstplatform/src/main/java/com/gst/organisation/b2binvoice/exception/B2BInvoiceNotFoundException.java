package com.gst.organisation.b2binvoice.exception;

import com.gst.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class B2BInvoiceNotFoundException extends AbstractPlatformResourceNotFoundException {

	public B2BInvoiceNotFoundException(Long id) {
        super("error.msg.b2binvoice.id.invalid", "B2bInvoice with identifier " + id + " does not exist", id);
        }

}
