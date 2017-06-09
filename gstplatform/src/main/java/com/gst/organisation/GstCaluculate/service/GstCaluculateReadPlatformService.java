package com.gst.organisation.GstCaluculate.service;


import java.util.Collection;

import com.amazonaws.util.json.JSONException;
import com.gst.organisation.GstCaluculate.data.GstCaluculateData;

public interface GstCaluculateReadPlatformService{
	
    	//Collection<GstCaluculateData> rateCaluculation(double itemamount);
		Collection<GstCaluculateData> retrieveAllCaluculations(Double sgstrate, Double igstrate) throws JSONException;

		//Collection<GstCaluculateData> retrieveAllCaluculations();
	
}
