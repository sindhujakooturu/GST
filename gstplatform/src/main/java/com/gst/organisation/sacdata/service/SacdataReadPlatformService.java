package com.gst.organisation.sacdata.service;


import java.util.Collection;


import com.gst.organisation.sacdata.data.SacdataData;


public interface SacdataReadPlatformService {
	
			
	
			Collection<SacdataData> retrieveAllSacdata();
		
 
  

        //Collection<SacdataData> retrieveAllSacdata(String sqlSearch, String sacseqid, String status);
    
    //Object[] hasAssociatedItems(final String sacseqid);
}


