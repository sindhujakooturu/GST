package com.gst.organisation.supplier.exception;

import com.gst.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class SupplierNotFoundException extends AbstractPlatformResourceNotFoundException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SupplierNotFoundException(final String gstin,final Long id) {
        super("error.msg.supplier.gstin.invalid", "supplier with identifier " +gstin+ " and Id `"+id+ "`",gstin);
    }

}
