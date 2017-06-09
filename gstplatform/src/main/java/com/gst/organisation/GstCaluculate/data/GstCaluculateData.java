package com.gst.organisation.GstCaluculate.data;

import java.util.Collection;

import com.google.gson.JsonArray;



public class GstCaluculateData {
	

			private final Long id;
			private final String itemcode;
		    private final String itemdesc;
		    private final Double sgstrate;
		    private final Double cgstrate;
		    private final Double igstrate;
		    private final Double cessrate;
		    private Double ssc;
		    private Double csc;
		    private final Double itemamount;
		    private final String itemname;
		    private final Double sgstamount;
		    private final Double cgstamount;
		    private final Double igstamount;
		    private final Double cessamount;

    public GstCaluculateData(final Long id,final String itemcode,final String itemdesc,
    		final Double sgstrate,final Double cgstrate,final Double igstrate,
    		final Double cessrate,final Double ssc,final Double csc,final Double itemamount,final String itemname
    		,final Double sgstamount,final Double cgstamount,final Double igstamount,final Double cessamount) {
    	
		    	this.id=id;
		    	this.itemcode=itemcode;
		    	this.itemdesc=itemdesc;
		    	this.sgstrate=sgstrate;
		    	this.cgstrate=cgstrate;
		    	this.igstrate=igstrate;
		    	this.cessrate=cessrate;
		    	this.ssc=ssc;
		    	this.csc=csc;
		    	this.itemamount=itemamount;
		    	this.itemname=itemname;
		    	this.sgstamount=sgstamount;
		    	this.cessamount=cessamount;
		    	this.igstamount=igstamount;
		    	this.cgstamount=cgstamount;
		    	
	}

	public Double responce(final Long id,final boolean ssc,final boolean csc,final String itemcode,final String itemname,final Double itemamount,
			final Double sgstamount, final Double sgstrate, final Double cgstamount
			,final Double cgstrate,final Double igstamount,final Double igstrate,final Double cessamount,
			final Double cessrate) {
		return new GstCaluculateData(id, itemcode,itemname, itemamount,sgstamount,sgstrate,cgstamount,csc, csc, cgstrate,itemname, igstamount
				,igstrate,cessamount,cessrate);
	}
	public Long getId() {
		return id;
	}

	public String getItemcode() {
		return itemcode;
	}

	public String getItemdesc() {
		return itemdesc;
	}

	public Double getSgstrate() {
		return sgstrate;
	}

	public Double getCgstrate() {
		return cgstrate;
	}

	public Double getIgstrate() {
		return igstrate;
	}

	public Double getCessrate() {
		return cessrate;
	}

	public Double getSsc() {
		return ssc;
	}

	public Double getCsc() {
		return csc;
	}

	public Double getItemamount() {
		return itemamount;
	}

	public String getItemname() {
		return itemname;
	}

	public Double getSgstamount() {
		return sgstamount;
	}

	public Double getCgstamount() {
		return cgstamount;
	}

	public Double getIgstamount() {
		return igstamount;
	}

	public Double getCessamount() {
		return cessamount;
	}

	public void setSsc(Double ssc) {
		this.ssc = ssc;
	}

	public void setCsc(Double csc) {
		this.csc = csc;
	}

	public static Collection<GstCaluculateData> response(final String itemcode,final String itemname,final Double itemamount,final Double sgstrate
			,final Double cgstrate,final Double sgstamount,final Double cgstamount,final Double igstrate,final Double igstamount,final Double cessamount,final Double cessrate) {
		 return response( itemcode,itemname,itemamount,sgstrate,sgstamount,cgstrate,cgstamount,igstrate,igstamount,cessrate,cessamount);
		
	}
	
	
	public static Collection<GstCaluculateData> sameCodeResponse(final String itemcode,final String itemname,final Double itemamount,final Double sgstrate
			,final Double cgstrate,final Double sgstamount,final Double cgstamount,final Double igstrate,final Double igstamount,final Double cessamount,final Double cessrate) {
		
		return sameCodeResponse(itemcode,itemname,itemamount,sgstrate,sgstamount,cgstrate,cgstamount,igstrate,igstamount,cessrate,cessamount);
	}
	
	
}
