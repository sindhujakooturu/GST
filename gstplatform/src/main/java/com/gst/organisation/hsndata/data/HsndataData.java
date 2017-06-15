package com.gst.organisation.hsndata.data;



public class HsndataData {

    private final Long id;
    private final String hsnCode;
    private final String description;
    
    public HsndataData( final Long id ,final String hsnCode,  final String description) {

    	this.id = id;
    	this.hsnCode = hsnCode;
        this.description = description;
        
    }


	public Long getId() {
		return id;
	}

	public String getHsnCode() {
		return hsnCode;
	}


	public String getDescription() {
		return description;
	}


   
}