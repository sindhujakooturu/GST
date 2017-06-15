package com.gst.organisation.GstCaluculate.data;

import java.math.BigDecimal;





public class GstCaluculateData{
	

			private  Long id;
			private  String itemCode;
			private  String itemDesc;
			private  BigDecimal sgstRate;
			private  BigDecimal cgstRate;
			private BigDecimal igstRate;
			private BigDecimal cessRate;
			private BigDecimal itemAmount;
			private String itemName;
			private BigDecimal sgstAmount;
			private BigDecimal cgstAmount;
			private BigDecimal igstAmount;
			private BigDecimal cessAmount;
			private String ssc;
			private String csc;

    public GstCaluculateData(final Long id,final String itemCode,final String itemDesc,
    		final BigDecimal sgstRate,final BigDecimal cgstRate,final BigDecimal igstRate,
    		final BigDecimal cessRate,final BigDecimal itemAmount,final String itemName
    		,final BigDecimal sgstAmount,final BigDecimal cgstAmount,final BigDecimal igstAmount,final BigDecimal cessAmount,final String ssc,final String csc) {
    	
		    	this.id=id;
		    	this.itemCode=itemCode;
		    	this.itemDesc=itemDesc;
		    	this.sgstRate=sgstRate;
		    	this.cgstRate=cgstRate;
		    	this.igstRate=igstRate;
		    	this.cessRate=cessRate;
		    	this.itemAmount=itemAmount;
		    	this.itemName=itemName;
		    	this.sgstAmount=sgstAmount;
		    	this.cessAmount=cessAmount;
		    	this.igstAmount=igstAmount;
		    	this.cgstAmount=cgstAmount;
		    	this.ssc=ssc;
		    	this.csc=csc;
		    	
	}
    
    public GstCaluculateData(){
    	
    }
   /* static {
    	GstCaluculateData my;
        try {
            my = new GstCaluculateData();
            my.init();
        } catch (Exception e) {
            my = null;
            // log
        }
    }*/
	public Long getId() {
		return id;
	}

	public String getItemCode() {
		return itemCode;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public BigDecimal getSgstRate() {
		return sgstRate;
	}

	public BigDecimal getCgstRate() {
		return cgstRate;
	}

	public BigDecimal getIgstRate() {
		return igstRate;
	}

	public BigDecimal getCessRate() {
		return cessRate;
	}

	public BigDecimal getItemAmount() {
		return itemAmount;
	}

	public String getItemName() {
		return itemName;
	}

	public BigDecimal getSgstAmount() {
		return sgstAmount;
	}

	public BigDecimal getCgstAmount() {
		return cgstAmount;
	}

	public BigDecimal getIgstAmount() {
		return igstAmount;
	}

	public BigDecimal getCessAmount() {
		return cessAmount;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSsc() {
		return ssc;
	}

	public void setSsc(String ssc) {
		this.ssc = ssc;
	}

	public String getCsc() {
		return csc;
	}

	public void setCsc(String csc) {
		this.csc = csc;
	}
}
