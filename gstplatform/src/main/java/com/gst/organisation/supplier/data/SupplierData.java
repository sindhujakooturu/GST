package com.gst.organisation.supplier.data;




public class SupplierData {
	private final Long id;
	private final String gstin;
    private final String gstinComp;
    private final String supplierName;
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
    
	/*public static SupplierData templateData(final SupplierData supply) {
        return new SupplierData(supply.id,supply.gstin, supply.gstinComp, supply.supplierName,supply.contactName, supply.officePhone, supply.homePhone,
        		supply.rmn, supply.fax, supply.rmail, supply.panNo, supply.etin, supply.addrLine1,supply.addrLine2,supply.city,supply.state,supply.country
        		,supply.pin);
    }*/
   /* public static SupplierData(final Long id,final String gstin, final String gstinComp, final String supplierName, final String contactName,
            final String officePhone, final String homePhone, final String rmn, final String fax, final String rmail,
            final String panNo, final String etin,final String addrLine1,final String addrLine2,final String city,final String state
            ,final String country,final String pin) {
        return new SupplierData(id,gstin,gstinComp,supplierName,contactName,officePhone,homePhone,rmn,fax,rmail,panNo,etin,addrLine1,addrLine2,city,state,country
        		,pin);
    }*/
    public static SupplierData lookup(final Long id, final String gstin) {
        return new SupplierData(id, null, gstin, null, null, null, null, null, null, null, null, null,null,null,null
        		,null,null,null);
    }
    public SupplierData(final Long id,final String gstin,final String gstinComp,
    		final String supplierName,final String contactName,final String officePhone,
    		final String homePhone,final String rmn,final String fax,final String rmail,
    		final String panNo, final String etin, String addrLine1,
    		final String addrLine2, final String city,final String state,final String country,
    		final String pin) {
		// TODO Auto-generated constructor stub
    	this.id=id;
    	this.gstin=gstin;
    	this.gstinComp=gstinComp;
    	this.supplierName=supplierName;
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
	public String getSupplierName() {
		return supplierName;
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
