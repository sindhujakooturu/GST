package com.gst.organisation.gstr1fileinvoice.domain;

import java.math.BigDecimal;
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
@Table(name = "g_gstr1_file_b2b_items")
public class Gstr1FileB2BItem extends AbstractPersistableCustom<Long> {

	private static final long serialVersionUID = 1L;

	@Column(name = "invoice_id")
	private Long invoiceId;

	@Column(name = "file_no")
	private String fileNo;

	@Column(name = "item_type")
	private String itemType;

	@Column(name = "item_code")
	private String itemCode;

	@Column(name = "tax_value")
	private BigDecimal taxValue;

	@Column(name = "igst_rate")
	private BigDecimal igstRate;

	@Column(name = "igst_amount")
	private BigDecimal igstAmount;
	
	@Column(name = "cgst_rate")
	private BigDecimal cgstRate;

	@Column(name = "cgst_amount")
	private BigDecimal cgstAmount;

	@Column(name = "sgst_rate")
	private BigDecimal sgstRate;

	@Column(name = "sgst_amount")
	private BigDecimal sgstAmount;

	@Column(name = "cess_rate")
	private BigDecimal cessRate;

	@Column(name = "cess_amount")
	private BigDecimal cessAmount;

	@Column(name = "status")
	private Integer status;
	
	@Column(name = "error_code")
	private String errorCode;

	@Column(name = "error_descriptor")
	private String errorDescriptor;
	
	public Gstr1FileB2BItem() {
		
	}

	public Gstr1FileB2BItem(final Long invoiceId, final String fileNo,final String itemType, final String itemCode,
			final BigDecimal taxValue, final BigDecimal igstRate,final BigDecimal igstAmount,final BigDecimal cgstRate,final BigDecimal cgstAmount,
			final BigDecimal sgstRate,final BigDecimal sgstAmount,final BigDecimal cessRate,final BigDecimal cessAmount,
			final Integer status,final String errorCode,final String errorDescriptor) {

		this.invoiceId = invoiceId;
		this.fileNo = fileNo;
		this.itemType = itemType;
		this.itemCode = itemCode;
		this.taxValue = taxValue;
		this.igstRate = igstRate;
		this.igstAmount = igstAmount;
		this.cgstRate = cgstRate;
		this.cgstAmount = cgstAmount;
		this.sgstRate = sgstRate;
		this.sgstAmount = sgstAmount;
		this.cessRate = cessRate;
		this.cessAmount = cessAmount;
		this.status = status;
		this.errorCode = errorCode;
		this.errorDescriptor = errorDescriptor;
	}
	

	/**
	 * @param command
	 * @return OutWardInv constructor
	 */
	public static Gstr1FileB2BItem fromJson(final JsonCommand command) {

		final Long invoiceId = command.longValueOfParameterNamed("invoiceId");
		final String fileNo = command.stringValueOfParameterNamed("fileNo");
		final String itemType = command.stringValueOfParameterNamed("itemType");
		final String itemCode = command.stringValueOfParameterNamed("itemCode");
		final BigDecimal taxValue = command.bigDecimalValueOfParameterNamed("taxValue");
		final BigDecimal igstRate = command.bigDecimalValueOfParameterNamed("igstRate");
		final BigDecimal igstAmount = command.bigDecimalValueOfParameterNamed("igstAmount");
		final BigDecimal cgstRate = command.bigDecimalValueOfParameterNamed("cgstRate");
		final BigDecimal cgstAmount = command.bigDecimalValueOfParameterNamed("cgstAmount");
		final BigDecimal sgstRate = command.bigDecimalValueOfParameterNamed("sgstRate");
		final BigDecimal sgstAmount = command.bigDecimalValueOfParameterNamed("sgstAmount");
		final BigDecimal cessRate = command.bigDecimalValueOfParameterNamed("cessRate");
		final BigDecimal cessAmount = command.bigDecimalValueOfParameterNamed("cessAmount");
		final int status = command.integerValueOfParameterNamed("status");
		final String errorCode = command.stringValueOfParameterNamed("errorCode");
		final String errorDescriptor = command.stringValueOfParameterNamed("errorDescriptor");

		return new Gstr1FileB2BItem(invoiceId, fileNo, itemType, itemCode, taxValue, 
				igstRate,igstAmount,cgstRate,cgstAmount,sgstRate,sgstAmount,cessRate,cessAmount,status,errorCode,errorDescriptor);
	}

	/**
	 * @param command
	 * @return changes of OutWardStagingInv object
	 */
	public Map<String, Object> update(JsonCommand command) {

		final Map<String, Object> actualChanges = new LinkedHashMap<String, Object>(1);
		
		if (command.isChangeInLongParameterNamed("invoiceId",this.invoiceId)) {
			final Long newValue = command.longValueOfParameterNamed("invoiceId");
			actualChanges.put("invoiceId", newValue);
			this.invoiceId = newValue;
		}
		if (command.isChangeInStringParameterNamed("fileNo",this.fileNo)) {
			final String newValue = command.stringValueOfParameterNamed("fileNo");
			actualChanges.put("fileNo", newValue);
			this.fileNo = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInStringParameterNamed("itemType",this.itemType)) {
			final String newValue = command.stringValueOfParameterNamed("itemType");
			actualChanges.put("itemType", newValue);
			this.itemType = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInStringParameterNamed("itemCode",this.itemCode)) {
			final String newValue = command.stringValueOfParameterNamed("itemCode");
			actualChanges.put("itemCode", newValue);
			this.itemCode = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInBigDecimalParameterNamed("taxValue",this.taxValue)) {
			final BigDecimal newValue = command.bigDecimalValueOfParameterNamed("taxValue");
			actualChanges.put("taxValue", newValue);
			this.taxValue = newValue;
		}
		
		if (command.isChangeInBigDecimalParameterNamed("igstRate",this.igstRate)) {
			final BigDecimal newValue = command.bigDecimalValueOfParameterNamed("igstRate");
			actualChanges.put("igstRate", newValue);
			this.igstRate = newValue;
		}
		
		if (command.isChangeInBigDecimalParameterNamed("igstAmount",this.igstAmount)) {
			final BigDecimal newValue = command.bigDecimalValueOfParameterNamed("igstAmount");
			actualChanges.put("igstAmount", newValue);
			this.igstAmount = newValue;
		}
		if (command.isChangeInBigDecimalParameterNamed("cgstRate",this.cgstRate)) {
			final BigDecimal newValue = command.bigDecimalValueOfParameterNamed("cgstRate");
			actualChanges.put("cgstRate", newValue);
			this.cgstRate = newValue;
		}
		if (command.isChangeInBigDecimalParameterNamed("cgstAmount",this.cgstAmount)) {
			final BigDecimal newValue = command.bigDecimalValueOfParameterNamed("cgstAmount");
			actualChanges.put("cgstAmount", newValue);
			this.cgstAmount = newValue;
		}
		if (command.isChangeInBigDecimalParameterNamed("sgstRate",this.sgstRate)) {
			final BigDecimal newValue = command.bigDecimalValueOfParameterNamed("sgstRate");
			actualChanges.put("sgstRate", newValue);
			this.sgstRate = newValue;
		}
		if (command.isChangeInBigDecimalParameterNamed("sgstAmount",this.sgstAmount)) {
			final BigDecimal newValue = command.bigDecimalValueOfParameterNamed("sgstAmount");
			actualChanges.put("sgstAmount", newValue);
			this.sgstAmount = newValue;
		}
		if (command.isChangeInBigDecimalParameterNamed("cessRate",this.cessRate)) {
			final BigDecimal newValue = command.bigDecimalValueOfParameterNamed("cessRate");
			actualChanges.put("cessRate", newValue);
			this.cessRate = newValue;
		}
		
		if (command.isChangeInBigDecimalParameterNamed("cessAmount",this.cessAmount)) {
			final BigDecimal newValue = command.bigDecimalValueOfParameterNamed("cessAmount");
			actualChanges.put("cessAmount", newValue);
			this.cessAmount = newValue;
		}
		
		if (command.isChangeInIntegerParameterNamed("status",this.status)) {
			final int newValue = command.integerValueOfParameterNamed("status");
			actualChanges.put("status", newValue);
			this.status = newValue;
		}
		
		if (command.isChangeInStringParameterNamed("errorCode",this.errorCode)) {
			final String newValue = command.stringValueOfParameterNamed("errorCode");
			actualChanges.put("errorCode", newValue);
			this.errorCode = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInStringParameterNamed("errorDescriptor",this.errorDescriptor)) {
			final String newValue = command.stringValueOfParameterNamed("errorDescriptor");
			actualChanges.put("errorDescriptor", newValue);
			this.errorDescriptor = StringUtils.defaultIfEmpty(newValue, null);
		}

		return actualChanges;
	}

}
