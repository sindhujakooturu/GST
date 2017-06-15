package com.gst.organisation.purchaser.data;




public class PurchaserData {
	
	private final Long id;
	private final String gstin;
    private final String gstinComp;
    private final String purchaserName;
    private final String contactName;
    private final String officePhone;
    private final String homePhone;
    private final String rmn;
    private final String fax;
    private final String rmail;
    private final String panNo;
    private final String etin;
    private final String addrLine1;
    private final String addrLine2;
    private final String city;
    private final String state;
    private final String country;
    private final String pin;

    public PurchaserData(final Long id,final String gstin,final String gstinComp,
    		final String purchaserName,final String contactName,final String officePhone,
    		final String homePhone,final String rmn,final String fax,final String rmail,
    		final String panNo, final String etin, String addrLine1,
    		final String addrLine2, final String city,final String state,final String country,
    		final String pin) {
    	
		    	this.id=id;
		    	this.gstin=gstin;
		    	this.gstinComp=gstinComp;
		    	this.purchaserName=purchaserName;
		    	this.contactName=contactName;
		    	this.officePhone=officePhone;
		    	this.homePhone=homePhone;
		    	this.rmn=rmn;
		    	this.fax=fax;
		    	this.rmail=rmail;
		    	this.panNo=panNo;
		    	this.etin=etin;
		    	this.addrLine1=addrLine1;
		    	this.addrLine2=addrLine2;
		    	this.city=city;
		    	this.state=state;
		    	this.country=country;
		    	this.pin=pin;
	}
    
	public Long getId() {
		return id;
	}
	public String getGstin() {
		return gstin;
	}
	public String getGstinComp() {
		return gstinComp;
	}
	public String getPurchaserName() {
		return purchaserName;
	}
	public String getContactName() {
		return contactName;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public String getRmn() {
		return rmn;
	}
	public String getFax() {
		return fax;
	}
	public String getRmail() {
		return rmail;
	}
	public String getPanNo() {
		return panNo;
	}
	public String getEtin() {
		return etin;
	}
	public String getAddrLine1() {
		return addrLine1;
	}
	public String getAddrLine2() {
		return addrLine2;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getCountry() {
		return country;
	}
	public String getPin() {
		return pin;
	}
    
}
