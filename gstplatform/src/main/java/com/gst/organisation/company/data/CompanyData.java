package com.gst.organisation.company.data;



public class CompanyData {

	private Long id;
	private String gstin;
	private String companyName;
	private String contactName;
	private String officePhone;
	private String homePhone;
	private String mobile;
	private String fax;
	private String email;
	private String gstnRegNo;
	private String panNo;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String country;
	private String pin;
	private Long officeId;

	
	public CompanyData() {
		
	}

	public CompanyData(final Long id, final String gstin, final String companyName, final String contactName,
			final String officePhone, final String homePhone, final String mobile,
			final String fax, final String email,final String gstnRegNo ,final String panNo,
			final String addressLine1,final String addressLine2,final String city,final String state,final String country,
			final String pin,final Long officeId) {
		
		this.id = id;
		this.gstin = gstin;
		this.companyName = companyName;
		this.contactName = contactName;
		this.officePhone = officePhone;
		this.homePhone = homePhone;
		this.mobile = mobile;
		this.fax = fax;
		this.email = email;
		this.gstnRegNo = gstnRegNo;
		this.panNo = panNo;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pin = pin;
		this.officeId = officeId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGstnRegNo() {
		return gstnRegNo;
	}

	public void setGstnRegNo(String gstnRegNo) {
		this.gstnRegNo = gstnRegNo;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public Long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Long officeId) {
		this.officeId = officeId;
	}
	

}
