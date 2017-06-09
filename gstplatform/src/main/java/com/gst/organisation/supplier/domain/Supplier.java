package com.gst.organisation.supplier.domain;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.apache.commons.lang.StringUtils;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.domain.AbstractPersistableCustom;

@Entity
@Table(name="supplier_t")
public class Supplier extends AbstractPersistableCustom<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="gstin", length = 15)
	private String gstin;
	
	@Column(name="gstin_comp", length = 15)
	private String gstinComp;
	
	@Column(name="supplier_name", length = 256)
    private String supplierName;
	
	@Column(name="contact_name", length = 60)
    private  String contactName;
	
	@Column(name="office_phone", length = 60)
    private  String officePhone;
	
	@Column(name="home_phone", length = 60)
    private  String homePhone;
	
	@Column(name="rmn", length = 60)
    private  String rmn;
	
	@Column(name="fax", length = 60)
    private  String fax;
	
	@Column(name="rmail", length = 60)
    private  String rmail;
	
	@Column(name="pan_no", length = 10)
    private  String panNo;
	
	@Column(name="etin", length = 10)
    private  String etin;
	
	@Column(name="addr_line1", length = 256)
    private  String addrLine1;
	
	@Column(name="addr_line2", length = 256)
    private  String addrLine2;
	
	@Column(name="city", length = 60)
    private  String city;
	
	@Column(name="state", length = 60)
    private  String state;
	
	@Column(name="country", length = 60)
    private  String country;
	
	@Column(name="pin", length = 10)
    private  String pin;
	
	public Supplier(){
		
	}
	
	 public static Supplier fromJson(final JsonCommand command) {

	        final String gstinParamName = "gstin";
	        final String gstin = command.stringValueOfParameterNamed(gstinParamName);

	        final String gstinCompParamName = "gstinComp";
	        final String gstinComp = command.stringValueOfParameterNamed(gstinCompParamName);

	        final String supplierParamName = "supplierName";
	        final String supplierName = command.stringValueOfParameterNamedAllowingNull(supplierParamName);

	        final String contactParamName = "contactName";
	        final String contactName = command.stringValueOfParameterNamedAllowingNull(contactParamName);

	        final String officePhoneParamName = "officePhone";
	        final String officePhone = command.stringValueOfParameterNamedAllowingNull(officePhoneParamName);
	        
	        final String homePhoneParamName = "homePhone";
	        final String homePhone = command.stringValueOfParameterNamedAllowingNull(homePhoneParamName);
	        
	        final String rmnParamName = "rmn";
	        final String rmn = command.stringValueOfParameterNamedAllowingNull(rmnParamName);
	        
	        final String faxParamName = "fax";
	        final String fax = command.stringValueOfParameterNamedAllowingNull(faxParamName);
	        
	     
	        final String rmailParamName = "rmail";
	        final String rmail= command.stringValueOfParameterNamedAllowingNull(rmailParamName);
	        
	        final String panNoParamName = "panNo";
	        final String panNo = command.stringValueOfParameterNamedAllowingNull(panNoParamName);
	        
	        final String etinParamName = "etin";
	        final String etin = command.stringValueOfParameterNamedAllowingNull(etinParamName);
	        
	        final String addressParamName = "addrLine1";
	        final String addrLine1 = command.stringValueOfParameterNamedAllowingNull(addressParamName);
	        
	        final String addressParamNameTwo = "addrLine2";
	        final String addrLine2 = command.stringValueOfParameterNamedAllowingNull(addressParamNameTwo);
	        
	        final String cityParamName = "city";
	        final String city= command.stringValueOfParameterNamedAllowingNull(cityParamName);
	        
	        final String stateParamName= "state";
	        final String state= command.stringValueOfParameterNamedAllowingNull(stateParamName);
	        
	        final String countryParamName= "country";
	        final String country= command.stringValueOfParameterNamedAllowingNull(countryParamName);
	        
	        final String pinParamName= "pin";
	        final String pin= command.stringValueOfParameterNamedAllowingNull(pinParamName);

	        return new Supplier(gstin, gstinComp, supplierName, contactName, officePhone, homePhone, rmn
	        		,fax,rmail,panNo,etin,addrLine1,addrLine2,city,state,country,pin);
	    }
	
	public Supplier(final String gstin,final String gstinComp,final String supplierName,
			final String contactName, final String officePhone, final String homePhone,
			final String rmn,final String fax,final String rmail,final String panNo,
			final String etin,final String addrLine1,final String addrLine2,final String city,
			final String state,final String country,final String pin) {
		
		
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
	
	
	public String getGstin(){
		return gstin;
	}
	public String getGstinComp(){
		return gstinComp;
	}
	public String getSupplierName(){
		return supplierName;
	}
	public String getContactName(){
		return contactName;
	}
	public String getOfficePhone(){
		return officePhone;
	}
	public String getHomePhone(){
		return homePhone;
	}
	public String getRmn(){
		return rmn;
	}
	public String getFax(){
		return fax;
	}
	public String getRmail(){
		return rmail;
	}
	public String getPanNo(){
		return panNo;
	}
	public String getEtin(){
		return etin;
	}
	public String getAddrLine1(){
		return addrLine1;
	}
	public String getAddrLine2(){
		return addrLine2;
	}
	public String getCity(){
		return city;
	}
	public String getState(){
		return state;
	}
	public String getCountry(){
		return country;
	}
	public String getPin(){
		return pin;
	}
	
	public Map<String, Object> update(final JsonCommand command) {
		
		        final Map<String, Object> actualChanges = new LinkedHashMap<>(7);
		        
		        final String gstin= "gstin";
		        if (command.isChangeInStringParameterNamed(gstin, this.gstin)) {
		            final String newValue = command.stringValueOfParameterNamed(gstin);
		            actualChanges.put(gstin, newValue);
		            this.gstin=StringUtils.defaultIfEmpty(newValue, null);
		        }
		        final String gstinCompParamName = "gstinComp";
		        if (command.isChangeInStringParameterNamed(gstinCompParamName, this.gstinComp)) {
		            final String newValue = command.stringValueOfParameterNamed(gstinCompParamName);
		            actualChanges.put(gstinCompParamName, newValue);
		            this.gstinComp= StringUtils.defaultIfEmpty(newValue, null);
		        }
		        final String supplierParamName = "supplierName";
		        if (command.isChangeInStringParameterNamed(supplierParamName, this.supplierName)) {
		            final String newValue = command.stringValueOfParameterNamed(supplierParamName);
		            actualChanges.put(supplierParamName, newValue);
		            this.supplierName= StringUtils.defaultIfEmpty(newValue, null);
		        }
		        final String contactParamName = "contactName";
		        if (command.isChangeInStringParameterNamed(contactParamName, this.contactName)) {
		            final String newValue = command.stringValueOfParameterNamed(contactParamName);
		            actualChanges.put(contactParamName, newValue);
		            this.contactName= StringUtils.defaultIfEmpty(newValue, null);
		        }
		        final String officePhoneParamName = "officePhone";
		        if (command.isChangeInStringParameterNamed(officePhoneParamName, this.officePhone)) {
		            final String newValue = command.stringValueOfParameterNamed(officePhoneParamName);
		            actualChanges.put(officePhoneParamName, newValue);
		            this.officePhone= StringUtils.defaultIfEmpty(newValue, null);
		        }
		        final String homePhoneParamName = "homePhone";
		        if (command.isChangeInStringParameterNamed(homePhoneParamName, this.homePhone)) {
		            final String newValue = command.stringValueOfParameterNamed(homePhoneParamName);
		            actualChanges.put(homePhoneParamName, newValue);
		            this.homePhone= StringUtils.defaultIfEmpty(newValue, null);
		        }
		        final String rmnParamName = "rmn";
		        if (command.isChangeInStringParameterNamed(rmnParamName, this.rmn)) {
		            final String newValue = command.stringValueOfParameterNamed(rmnParamName);
		            actualChanges.put(rmnParamName, newValue);
		            this.rmn= StringUtils.defaultIfEmpty(newValue, null);
		        }
		        final String faxParamName = "fax";
		        if (command.isChangeInStringParameterNamed(faxParamName, this.fax)) {
		            final String newValue = command.stringValueOfParameterNamed(faxParamName);
		            actualChanges.put(faxParamName, newValue);
		            this.fax= StringUtils.defaultIfEmpty(newValue, null);
		        }
		        final String rmailParamName = "rmail";
		        if (command.isChangeInStringParameterNamed(rmailParamName, this.rmail)) {
		            final String newValue = command.stringValueOfParameterNamed(rmailParamName);
		            actualChanges.put(rmailParamName, newValue);
		            this.rmail= StringUtils.defaultIfEmpty(newValue, null);
		        }
		        final String panNoParamName = "panNo";
		        if (command.isChangeInStringParameterNamed(panNoParamName, this.panNo)) {
		            final String newValue = command.stringValueOfParameterNamed(panNoParamName);
		            actualChanges.put(panNoParamName, newValue);
		            this.panNo= StringUtils.defaultIfEmpty(newValue, null);
		        }
		        final String etinParamName = "etin";
		        if (command.isChangeInStringParameterNamed(etinParamName, this.etin)) {
		            final String newValue = command.stringValueOfParameterNamed(etinParamName);
		            actualChanges.put(etinParamName, newValue);
		            this.etin= StringUtils.defaultIfEmpty(newValue, null);
		        }
		        final String addressParamName = "addrLine1";
		        if (command.isChangeInStringParameterNamed(addressParamName, this.addrLine1)) {
		            final String newValue = command.stringValueOfParameterNamed(addressParamName);
		            actualChanges.put(addressParamName, newValue);
		            this.addrLine1= StringUtils.defaultIfEmpty(newValue, null);
		        }
		        final String addressParamNameTwo = "addrLine2";
		        if (command.isChangeInStringParameterNamed(addressParamNameTwo, this.addrLine2)) {
		            final String newValue = command.stringValueOfParameterNamed(addressParamNameTwo);
		            actualChanges.put(addressParamNameTwo, newValue);
		            this.addrLine2= StringUtils.defaultIfEmpty(newValue, null);
		        }
		        final String cityParamName = "city";
		        if (command.isChangeInStringParameterNamed(cityParamName, this.city)) {
		            final String newValue = command.stringValueOfParameterNamed(cityParamName);
		            actualChanges.put(cityParamName, newValue);
		            this.city= StringUtils.defaultIfEmpty(newValue, null);
		        }
		        final String stateParamName = "state";
		        if (command.isChangeInStringParameterNamed(stateParamName, this.state)) {
		            final String newValue = command.stringValueOfParameterNamed(stateParamName);
		            actualChanges.put(stateParamName, newValue);
		            this.state= StringUtils.defaultIfEmpty(newValue, null);
		        }
		        final String countryParamName = "country";
		        if (command.isChangeInStringParameterNamed(countryParamName, this.country)) {
		            final String newValue = command.stringValueOfParameterNamed(countryParamName);
		            actualChanges.put(countryParamName, newValue);
		            this.country= StringUtils.defaultIfEmpty(newValue, null);
		        }
		        final String pinParamName = "pin";
		        if (command.isChangeInStringParameterNamed(pinParamName, this.pin)) {
		            final String newValue = command.stringValueOfParameterNamed(pinParamName);
		            actualChanges.put(pinParamName, newValue);
		            this.pin= StringUtils.defaultIfEmpty(newValue, null);
		        }
		        return actualChanges; 
	}

	
}
