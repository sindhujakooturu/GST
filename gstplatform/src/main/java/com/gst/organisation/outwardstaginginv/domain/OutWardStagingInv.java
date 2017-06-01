package com.gst.organisation.outwardstaginginv.domain;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.AbstractPersistable;

import com.gst.infrastructure.core.api.JsonCommand;

/**
 * @author hugo
 * 
 */
@Entity
@Table(name = "b_charge_codes")
public class OutWardStagingInv extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 1L;

/*<<<<<<< HEAD
	@Column(name = "charge_code")
	private String chargeCode;

	@Column(name = "charge_description")
	private String chargeDescription;

	@Column(name = "charge_type")
	private String chargeType;

	@Column(name = "charge_duration")
	private Integer chargeDuration;

	@Column(name = "duration_type")
	private String durationType;

	@Column(name = "tax_inclusive")
	private Integer taxInclusive;

	@Column(name = "billfrequency_code")
	private String billFrequencyCode;

	public OutWardStagingInv() {
	}

	public OutWardStagingInv(final String chargeCode, final String chargeDescription,
			final String chargeType, final Integer chargeDuration,
			final String durationType, final Integer taxInclusive,
			final String billFrequencyCode) {

		this.chargeCode = chargeCode;
		this.chargeDescription = chargeDescription;
		this.chargeType = chargeType;
		this.chargeDuration = chargeDuration;
		this.durationType = durationType;
		this.taxInclusive = taxInclusive;
		this.billFrequencyCode = billFrequencyCode;
	}

	*//**
	 * @param command
	 * @return chargeCode constructor
	 *//*
	public static OutWardStagingInv fromJson(final JsonCommand command) {

		final String chargeCode = command
				.stringValueOfParameterNamed("chargeCode");
		final String chargeDescription = command
				.stringValueOfParameterNamed("chargeDescription");
		final Integer chargeDuration = command
				.integerValueOfParameterNamed("chargeDuration");
		final String chargeType = command
				.stringValueOfParameterNamed("chargeType");
		final String durationType = command
				.stringValueOfParameterNamed("durationType");
		final boolean taxInclusive = command
				.booleanPrimitiveValueOfParameterNamed("taxInclusive");

		Integer tax = null;

		if (taxInclusive) {
			tax = 1;
		} else {
			tax = 0;
		}

		final String billFrequencyCode = command
				.stringValueOfParameterNamed("billFrequencyCode");

		return new OutWardStagingInv(chargeCode, chargeDescription, chargeType,
				chargeDuration, durationType, tax, billFrequencyCode);
	}

	*//**
	 * @return the chargeCode
	 *//*
	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	*//**
	 * @return the chargeDescription
	 *//*
	public String getChargeDescription() {
		return chargeDescription;
	}

	public void setChargeDescription(String chargeDescription) {
		this.chargeDescription = chargeDescription;
	}

	*//**
	 * @return the chargeType
	 *//*
	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	*//**
	 * @return the chargeDuration
	 *//*
	public Integer getChargeDuration() {
		return chargeDuration;
	}

	public void setChargeDuration(Integer chargeDuration) {
		this.chargeDuration = chargeDuration;
	}

	*//**
	 * @return the durationType
	 *//*
	public String getDurationType() {
		return durationType;
	}

	public void setDurationType(String durationType) {
		this.durationType = durationType;
	}

	*//**
	 * @return the taxInclusive
	 *//*
	public Integer getTaxInclusive() {
		return taxInclusive;
	}

	public void setTaxInclusive(Integer taxInclusive) {
		this.taxInclusive = taxInclusive;
	}

	*//**
	 * @return the billFrequencyCode
	 *//*
	public String getBillFrequencyCode() {
		return billFrequencyCode;
	}

	public void setBillFrequencyCode(String billFrequencyCode) {
		this.billFrequencyCode = billFrequencyCode;
=======*/
	@Column(name = "gstin")
	private String gstin;

	@Column(name = "gstin_purchaser")
	private String gstinPurchaser;

	@Column(name = "c_name")
	private String cName;

	@Column(name = "supplier_inv_no")
	private String supplierInvNo;

	@Column(name = "supplier_inv_date")
	private Date supplierInvDate;

	@Column(name = "supplier_inv_value")
	private Long supplierInvValue;

	@Column(name = "supply_state_code")
	private String supplyStateCode;
	
	@Column(name = "order_no")
	private String orderNo;

	@Column(name = "order_date")
	private Date orderDate;

	@Column(name = "etin")
	private String etin;

	@Column(name = "invoice_id")
	private Long invoiceId;

	@Column(name = "receipt_state_code")
	private String receiptStateCode;

	@Column(name = "status")
	private Integer status;

	@Column(name = "error_code")
	private String errorCode;
	
	@Column(name = "error_descr")
	private String errorDescripter;
	
	public OutWardStagingInv() {
		
	}

	public OutWardStagingInv(final String gstin, final String gstinPurchaser,
			final String cName, final String supplierInvNo,
			final Date supplierInvDate, final Long supplierInvValue,
			final String supplyStateCode,final String orderNo,final Date orderDate,
			final String etin,final Long invoiceId,final String receiptStateCode,final Integer status,
			final String errorCode,final String errorDescripter) {

		this.gstin = gstin;
		this.gstinPurchaser = gstinPurchaser;
		this.cName = cName;
		this.supplierInvNo = supplierInvNo;
		this.supplierInvDate = supplierInvDate;
		this.supplierInvValue = supplierInvValue;
		this.supplyStateCode = supplyStateCode;
		this.orderNo = orderNo;
		this.orderDate = orderDate;
		this.etin = etin;
		this.invoiceId = invoiceId;
		this.receiptStateCode = receiptStateCode;
		this.status = status;
		this.errorCode = errorCode;
		this.errorDescripter = errorDescripter;
	}
	
	/**
	 * @param command
	 * @return OutWardInv constructor
	 */
	public static OutWardStagingInv fromJson(final JsonCommand command) {

		final String gstin = command.stringValueOfParameterNamed("gstin");
		final String gstinPurchaser = command.stringValueOfParameterNamed("gstinPurchaser");
		final String cName = command.stringValueOfParameterNamed("cName");
		final String supplierInvNo = command.stringValueOfParameterNamed("supplierInvNo");
		final Date supplierInvDate = command.DateValueOfParameterNamed("supplierInvDate");
		final Long supplierInvValue = command.longValueOfParameterNamed("supplierInvValue");
		final String supplyStateCode = command.stringValueOfParameterNamed("supplyStateCode");
		final String orderNo = command.stringValueOfParameterNamed("orderNo");
		final Date orderDate = command.DateValueOfParameterNamed("orderDate");
		final String etin = command.stringValueOfParameterNamed("etin");
		final Long invoiceId = command.longValueOfParameterNamed("invoiceId");
		final String receiptStateCode = command.stringValueOfParameterNamed("receiptStateCode");
		final Integer status = command.integerValueOfParameterNamed("status");
		final String errorCode = command.stringValueOfParameterNamed("errorCode");
		final String errorDescripter = command.stringValueOfParameterNamed("errorDescripter");

		return new OutWardStagingInv(gstin, gstinPurchaser, cName,
				supplierInvNo, supplierInvDate, supplierInvValue, supplyStateCode,orderNo,orderDate,etin,invoiceId,receiptStateCode,
				status,errorCode,errorDescripter);
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
		if (command.isChangeInStringParameterNamed("gstinPurchaser",this.gstinPurchaser)) {
			final String newValue = command.stringValueOfParameterNamed("gstinPurchaser");
			actualChanges.put("gstinPurchaser", newValue);
			this.gstinPurchaser = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInStringParameterNamed("cName",this.cName)) {
			final String newValue = command.stringValueOfParameterNamed("cName");
			actualChanges.put("cName", newValue);
			this.cName = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInStringParameterNamed("supplierInvNo",this.supplierInvNo)) {
			final String newValue = command.stringValueOfParameterNamed("supplierInvNo");
			actualChanges.put("supplierInvNo", newValue);
			this.supplierInvNo = newValue;
		}
		if (command.isChangeInDateParameterNamed("supplierInvDate",this.supplierInvDate)) {
			final Date newValue = command.DateValueOfParameterNamed("supplierInvDate");
			actualChanges.put("supplierInvDate", newValue);
			this.supplierInvDate = newValue;
		}
		
		if (command.isChangeInLongParameterNamed("supplierInvValue",this.supplierInvValue)) {
			final Long newValue = command.longValueOfParameterNamed("supplierInvValue");
			actualChanges.put("supplierInvValue", newValue);
			this.supplierInvValue = newValue;
		}
		
		if (command.isChangeInStringParameterNamed("supplyStateCode",this.supplyStateCode)) {
			final String newValue = command.stringValueOfParameterNamed("supplyStateCode");
			actualChanges.put("supplyStateCode", newValue);
			this.supplyStateCode = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInStringParameterNamed("orderNo",this.orderNo)) {
			final String newValue = command.stringValueOfParameterNamed("orderNo");
			actualChanges.put("orderNo", newValue);
			this.orderNo = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInDateParameterNamed("orderDate",this.orderDate)) {
			final Date newValue = command.DateValueOfParameterNamed("orderDate");
			actualChanges.put("orderDate", newValue);
			this.orderDate = newValue;
		}
		if (command.isChangeInStringParameterNamed("etin",this.etin)) {
			final String newValue = command.stringValueOfParameterNamed("etin");
			actualChanges.put("etin", newValue);
			this.etin = newValue;
		}
		if (command.isChangeInLongParameterNamed("invoiceId",this.invoiceId)) {
			final Long newValue = command.longValueOfParameterNamed("invoiceId");
			actualChanges.put("invoiceId", newValue);
			this.invoiceId = newValue;
		}
		if (command.isChangeInStringParameterNamed("receiptStateCode",this.receiptStateCode)) {
			final String newValue = command.stringValueOfParameterNamed("receiptStateCode");
			actualChanges.put("receiptStateCode", newValue);
			this.receiptStateCode = newValue;
		}
		if (command.isChangeInIntegerParameterNamed("status",this.status)) {
			final Integer newValue = command.integerValueOfParameterNamed("status");
			actualChanges.put("status", newValue);
			this.status = newValue;
		}
		
		if (command.isChangeInStringParameterNamed("errorCode",this.errorCode)) {
			final String newValue = command.stringValueOfParameterNamed("errorCode");
			actualChanges.put("errorCode", newValue);
			this.errorCode = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInStringParameterNamed("errorDescripter",this.errorDescripter)) {
			final String newValue = command.stringValueOfParameterNamed("errorDescripter");
			actualChanges.put("errorDescripter", newValue);
			this.errorDescripter = StringUtils.defaultIfEmpty(newValue, null);
		}

		return actualChanges;
	}

}
