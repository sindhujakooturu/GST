package com.gst.organisation.hsndata.service;


import java.util.Collection;

import com.gst.organisation.hsndata.data.HsndataData;


public interface HsndataReadPlatformService {
	
	
			Collection<HsndataData> retrieveAllHsndata();

		//	HsndataData retrieveHsndata(String hscCode);
			

}


