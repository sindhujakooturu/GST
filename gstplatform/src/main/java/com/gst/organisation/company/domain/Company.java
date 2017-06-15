package com.gst.organisation.company.domain;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.domain.AbstractPersistableCustom;

/**
 * @author Trigital
 * 
 */
@Entity
@Table(name = "company_t")
public class Company extends AbstractPersistableCustom<Long> {

	private static final long serialVersionUID = 1L;

	@Column(name = "gstin")
	private String gstin;
	
	@Column(name = "company_name")
	private String companyName;

	@Column(name = "contact_name")
	private String contactName;

	@Column(name = "office_phone")
	private String officePhone;

	@Column(name = "home_phone")
	private String homePhone;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "fax")
	private String fax;

	@Column(name = "email")
	private String email;
	
	@Column(name = "gstn_reg_no")
	private String gstnRegNo;

	@Column(name = "pan_no")
	private String panNo;

	@Column(name = "address_line1")
	private String addressLine1;

	@Column(name = "address_line2")
	private String addressLine2;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "country")
	private String country;
	
	@Column(name = "pin")
	private String pin;
	
	@Column(name = "office_id")
	private Long officeId;
	
	public Company() {
		
	}
	
	public Company(final String gstin, final String companyName, final String contactName,
			final String officePhone, final String homePhone,final String mobile, final String fax,
			final String email,final String gstnRegNo,final String panNo,final String addressLine1,
			final String addressLine2,final String city,final String state,final String country,
			final String pin,final Long officeId) {

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
	
	/**
	 * @param command
	 * @return OutWardInv constructor
	 */
	public static Company fromJson(final JsonCommand command) {

		final String gstin = command.stringValueOfParameterNamed("gstin");
		final String companyName = command.stringValueOfParameterNamed("companyName");
		final String contactName = command.stringValueOfParameterNamed("contactName");
		final String officePhone = command.stringValueOfParameterNamed("officePhone");
		final String homePhone = command.stringValueOfParameterNamed("homePhone");
		final String mobile = command.stringValueOfParameterNamed("mobile");
		final String fax = command.stringValueOfParameterNamed("fax");
		final String email = command.stringValueOfParameterNamed("email");
		final String gstnRegNo = command.stringValueOfParameterNamed("gstnRegNo");
		final String panNo = command.stringValueOfParameterNamed("panNo");
		final String addressLine1 = command.stringValueOfParameterNamed("addressLine1");
		final String addressLine2 = command.stringValueOfParameterNamed("addressLine2");
		final String city = command.stringValueOfParameterNamed("city");
		final String state = command.stringValueOfParameterNamed("state");
		final String country = command.stringValueOfParameterNamed("country");
		final String pin = command.stringValueOfParameterNamed("pin");
		final Long officeId = command.longValueOfParameterNamed("pin");

		return new Company(gstin,companyName, contactName, officePhone, homePhone, mobile, fax,
				email, gstnRegNo, panNo, addressLine1, addressLine2, city, state, country, pin, officeId);
	}

	/**
	 * @param command
	 * @return changes of OutWardStagingInv object
	 */
	public Map<String, Object> update(JsonCommand command) {

		final Map<String, Object> actualChanges = new LinkedHashMap<String, Object>(1);
		
		if (command.isChangeInStringParameterNamed("gstin",this.gstin)) {
			final String newValue = command.stringValueOfParameterNamed("gstin");
			actualChanges.put("gstin", newValue);
			this.gstin = StringUtils.defaultIfEmpty(newValue, null);
		}
		
		if (command.isChangeInStringParameterNamed("companyName",this.companyName)) {
			final String newValue = command.stringValueOfParameterNamed("companyName");
			actualChanges.put("companyName", newValue);
			this.companyName = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInStringParameterNamed("contactName",this.contactName)) {
			final String newValue = command.stringValueOfParameterNamed("contactName");
			actualChanges.put("contactName", newValue);
			this.contactName = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInStringParameterNamed("officePhone",this.officePhone)) {
			final String newValue = command.stringValueOfParameterNamed("officePhone");
			actualChanges.put("officePhone", newValue);
			this.officePhone = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInStringParameterNamed("homePhone",this.homePhone)) {
			final String newValue = command.stringValueOfParameterNamed("homePhone");
			actualChanges.put("homePhone", newValue);
			this.homePhone = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInStringParameterNamed("mobile",this.mobile)) {
			final String newValue = command.stringValueOfParameterNamed("mobile");
			actualChanges.put("mobile", newValue);
			this.mobile = StringUtils.defaultIfEmpty(newValue, null);
		}
		
		if (command.isChangeInStringParameterNamed("fax",this.fax)) {
			final String newValue = command.stringValueOfParameterNamed("fax");
			actualChanges.put("fax", newValue);
			this.fax = StringUtils.defaultIfEmpty(newValue, null);
		}
		
		if (command.isChangeInStringParameterNamed("email",this.email)) {
			final String newValue = command.stringValueOfParameterNamed("email");
			actualChanges.put("email", newValue);
			this.email = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInStringParameterNamed("gstnRegNo",this.gstnRegNo)) {
			final String newValue = command.stringValueOfParameterNamed("gstnRegNo");
			actualChanges.put("gstnRegNo", newValue);
			this.gstnRegNo = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInStringParameterNamed("panNo",this.panNo)) {
			final String newValue = command.stringValueOfParameterNamed("panNo");
			actualChanges.put("panNo", newValue);
			this.panNo = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInStringParameterNamed("addressLine1",this.addressLine1)) {
			final String newValue = command.stringValueOfParameterNamed("addressLine1");
			actualChanges.put("addressLine1", newValue);
			this.addressLine1 = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInStringParameterNamed("addressLine2",this.addressLine2)) {
			final String newValue = command.stringValueOfParameterNamed("addressLine2");
			actualChanges.put("addressLine2", newValue);
			this.addressLine2 = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInStringParameterNamed("city",this.city)) {
			final String newValue = command.stringValueOfParameterNamed("city");
			actualChanges.put("city", newValue);
			this.city = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInStringParameterNamed("state",this.state)) {
			final String newValue = command.stringValueOfParameterNamed("state");
			actualChanges.put("state", newValue);
			this.state = StringUtils.defaultIfEmpty(newValue, null);
		}
		
		if (command.isChangeInStringParameterNamed("country",this.country)) {
			final String newValue = command.stringValueOfParameterNamed("country");
			actualChanges.put("country", newValue);
			this.country = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInStringParameterNamed("pin",this.pin)) {
			final String newValue = command.stringValueOfParameterNamed("pin");
			actualChanges.put("pin", newValue);
			this.pin = StringUtils.defaultIfEmpty(newValue, null);
		}
		
		if (command.isChangeInLongParameterNamed("officeId",this.officeId)) {
			final Long newValue = command.longValueOfParameterNamed("officeId");
			actualChanges.put("officeId", newValue);
			this.officeId = newValue;
		}

		return actualChanges;
	}

}
