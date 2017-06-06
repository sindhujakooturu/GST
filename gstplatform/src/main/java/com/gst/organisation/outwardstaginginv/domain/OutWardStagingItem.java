package com.gst.organisation.outwardstaginginv.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.domain.AbstractPersistableCustom;

@Entity
@Table(name = "g_ow_stg_items")
public class OutWardStagingItem extends AbstractPersistableCustom<Long>{

	@Column(name="invoice_id")
	private Long invoiceId;
	
	@Column(name = "item_type")
	private String itemType;
	
	@Column(name = "item_code")
	private String itemCode;
	
	@Column(name = "tax_value")
	private Double taxValue;
	
	@Column(name = "igst_rate")
	private Double igstRate;
	
	@Column(name = "igst_amount")
	private Double igstAmount;
	
	@Column(name = "cgst_rate")
	private Double cgstRate;
	
	@Column(name = "cgst_amount")
	private Double cgstAmount;
	
	@Column(name = "sgst_rate")
	private Double sgstRate;
	
	@Column(name = "sgst_amount")
	private Double sgstAmount;
	
	@Column(name = "cess_rate")
	private Double cessRate;
	
	@Column(name = "cess_amount")
	private Double cessAmount;
	
	@Column(name = "status")
	private int status;
	
	@Column(name = "error_code")
	private String errorCode;
	
	@Column(name = "error_descriptor")
	private String errorDescriptor;

	
	
	
	public OutWardStagingItem() {
	}




	public OutWardStagingItem(Long invoiceId, String itemType, String itemCode, Double taxValue, Double igstRate,
			Double igstAmount, Double cgstRate, Double cgstAmount, Double sgstRate, Double sgstAmount, Double cessRate,
			Double cessAmount) {

		this.invoiceId = invoiceId;
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
	}
	
	public static OutWardStagingItem fromJson(final JsonCommand command, final Long invoiceId) {

		final String itemType = command.stringValueOfParameterNamed("itemType");
		final String itemCode = command.stringValueOfParameterNamed("itemCode");
		final Double taxValue = command.doubleValueOfParameterNamed("taxValue");
		final Double igstRate = command.doubleValueOfParameterNamed("igstRate");
		final Double igstAmount = command.doubleValueOfParameterNamed("igstAmount");
		final Double cgstRate = command.doubleValueOfParameterNamed("cgstRate");
		final Double cgstAmount = command.doubleValueOfParameterNamed("cgstAmount");
		final Double sgstRate = command.doubleValueOfParameterNamed("sgstRate");
		final Double sgstAmount = command.doubleValueOfParameterNamed("sgstAmount");
		final Double cessRate = command.doubleValueOfParameterNamed("cessRate");
		final Double cessAmount = command.doubleValueOfParameterNamed("cessAmount");
		
		return new OutWardStagingItem(invoiceId, itemType, itemCode, taxValue, igstRate, igstAmount, cgstRate, cgstAmount,
				sgstRate, sgstAmount, cessRate, cessAmount);
	}

}
