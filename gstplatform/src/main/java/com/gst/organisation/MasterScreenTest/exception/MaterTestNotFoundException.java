package com.gst.organisation.MasterScreenTest.exception;

import com.gst.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class MaterTestNotFoundException extends AbstractPlatformResourceNotFoundException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param OutWardStagingInvId
	 */
	public MaterTestNotFoundException(final Long outWardInvId) {
		super("error.msg.outwardinvid.not.found", "outWardInvId with this id"
				+ outWardInvId + "not exist", outWardInvId);

	}

}