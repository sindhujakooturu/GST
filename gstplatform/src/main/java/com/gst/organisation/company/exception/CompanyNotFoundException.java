package com.gst.organisation.company.exception;

import com.gst.infrastructure.core.exception.AbstractPlatformResourceNotFoundException;

public class CompanyNotFoundException extends AbstractPlatformResourceNotFoundException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param OutWardStagingInvId
	 */
	public CompanyNotFoundException(final Long companyId) {
		super("error.msg.company.not.found", "companyId with this id"
				+ companyId + "not exist", companyId);

	}

}