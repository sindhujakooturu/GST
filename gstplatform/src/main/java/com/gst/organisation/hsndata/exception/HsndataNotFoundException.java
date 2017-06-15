package com.gst.organisation.hsndata.exception;

import com.gst.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class HsndataNotFoundException extends AbstractPlatformResourceNotFoundException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HsndataNotFoundException(final Long id){
	
        super("error.msg.hsndata.id.invalid", "Hsndata with identifier " + id + " does not exist", id);
    }
}

