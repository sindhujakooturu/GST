package com.gst.organisation.purchaser.exception;

import com.gst.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class PurchaserNotFoundException extends AbstractPlatformResourceNotFoundException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PurchaserNotFoundException(final String gstin,final Long id) {
        super("error.msg.purchaser.gstin.invalid", "purchaser with identifier " +gstin+ " and Id `"+id+ "`",gstin);
    }

}
