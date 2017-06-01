package com.gst.organisation.outwardstaginginv.domain;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

	@Column(name = "charge_code")
	private String gstin;

	@Column(name = "charge_description")
	private String gstinPurchaser;

	@Column(name = "charge_type")
	private String cName;

	@Column(name = "charge_duration")
	private String supplierInvNo;

	@Column(name = "duration_type")
	private String supplierInvDate;

	@Column(name = "tax_inclusive")
	private Long supplierInvValue;

	@Column(name = "billfrequency_code")
	private String supplyStateCode;
	
	@Column(name = "charge_code")
	private String orderNo;

	@Column(name = "charge_description")
	private String orderDate;

	@Column(name = "charge_type")
	private String etin;

	@Column(name = "charge_duration")
	private Long invoiceId;

	@Column(name = "duration_type")
	private String receiptStateCode;

	@Column(name = "tax_inclusive")
	private Integer status;

	@Column(name = "billfrequency_code")
	private String errorCode;
	
	@Column(name = "billfrequency_code")
	private String errorDescripter;
	
	@Column(name = "billfrequency_code")
	private Integer modeNo;
	
	public OutWardStagingInv() {
		
	}

	public OutWardStagingInv(final String gstin, final String gstinPurchaser,
			final String cName, final String supplierInvNo,
			final String supplierInvDate, final Long supplierInvValue,
			final String supplyStateCode,final String orderNo,final String orderDate,
			final String etin,final Long invoiceId,final String receiptStateCode,final Integer status,
			final String errorCode,final String errorDescripter,final Integer modeNo) {

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
		this.modeNo = modeNo;
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
		final String supplierInvDate = command.stringValueOfParameterNamed("supplierInvDate");
		final Long supplierInvValue = command.longValueOfParameterNamed("supplierInvValue");
		final String supplyStateCode = command.stringValueOfParameterNamed("supplyStateCode");
		final String orderNo = command.stringValueOfParameterNamed("orderNo");
		final String orderDate = command.stringValueOfParameterNamed("orderDate");
		final String etin = command.stringValueOfParameterNamed("etin");
		final Long invoiceId = command.longValueOfParameterNamed("invoiceId");
		final String receiptStateCode = command.stringValueOfParameterNamed("receiptStateCode");
		final Integer status = command.integerValueOfParameterNamed("status");
		final String errorCode = command.stringValueOfParameterNamed("errorCode");
		final String errorDescripter = command.stringValueOfParameterNamed("errorDescripter");
		final Integer modeNo = command.integerValueOfParameterNamed("modeNo");

		return new OutWardStagingInv(gstin, gstinPurchaser, cName,
				supplierInvNo, supplierInvDate, supplierInvValue, supplyStateCode,orderNo,orderDate,etin,invoiceId,receiptStateCode,
				status,errorCode,errorDescripter,modeNo);
	}

	/**
	 * @param command
	 * @return changes of OutWardInv object
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
		if (command.isChangeInStringParameterNamed("supplierInvDate",this.supplierInvDate)) {
			final String newValue = command.stringValueOfParameterNamed("supplierInvDate");
			actualChanges.put("supplierInvDate", newValue);
			this.supplierInvDate = StringUtils.defaultIfEmpty(newValue, null);
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
		if (command.isChangeInStringParameterNamed("orderDate",this.orderDate)) {
			final String newValue = command.stringValueOfParameterNamed("orderDate");
			actualChanges.put("orderDate", newValue);
			this.orderDate = StringUtils.defaultIfEmpty(newValue, null);
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
		if (command.isChangeInIntegerParameterNamed("modeNo",this.modeNo)) {
			final Integer newValue = command.integerValueOfParameterNamed("modeNo");
			actualChanges.put("modeNo", newValue);
			this.modeNo = newValue;
		}

		return actualChanges;
	}

}
