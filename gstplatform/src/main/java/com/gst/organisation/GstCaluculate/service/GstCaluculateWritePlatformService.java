package com.gst.organisation.GstCaluculate.service;

import com.gst.infrastructure.core.api.JsonCommand;

public interface GstCaluculateWritePlatformService {
	
	String createGstCaluculate(final JsonCommand command);

	//public BigDecimal findCaluculation(BigDecimal itemamount,final Double sgstrate,final Double igstrate);

	}
