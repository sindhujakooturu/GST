package com.gst.organisation.sacdata.exception;

import com.gst.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class SacdataNotFoundException extends AbstractPlatformResourceNotFoundException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SacdataNotFoundException(final Long id){
	
        super("error.msg.sacdata.id.invalid", "Sacdata with identifier " + id + " does not exist", id);
    }
}

