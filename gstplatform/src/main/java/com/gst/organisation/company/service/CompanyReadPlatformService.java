package com.gst.organisation.company.service;

import java.util.List;

import com.gst.organisation.company.data.CompanyData;

/**
 * @author Trigital
 * 
 */
public interface CompanyReadPlatformService {

	List<CompanyData> retrieveAllCompanyData();

	CompanyData retrieveSingleCompanyDetails(Long companyId);
	
	List<CompanyData> retrieveAllCompanyDetailsByUser(Long officeId);

}
