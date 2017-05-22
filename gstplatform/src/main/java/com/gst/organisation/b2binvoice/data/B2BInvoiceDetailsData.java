package com.gst.organisation.b2binvoice.data;

public class B2BInvoiceDetailsData {
	
	private Long id;
	private Long b2bInvId;
	private String itemType;
	private String itemCode;
	private Double taxValue;
	private Double igstRate;
	private Double igstAmount;
	private Double cgstRate;
	private Double cgstAmount;
	private Double sgstRate;
	private Double sgstAmount;
	private Double cessRate;
	private Double cessAmount;
	
	public B2BInvoiceDetailsData() {
	}

	public B2BInvoiceDetailsData(final Long id, final Long b2bInvId, final String itemType, final String itemCode, final Double taxValue,
			final Double igstRate, final Double igstAmount, final Double cgstRate, final Double cgstAmount, final Double sgstRate, final Double sgstAmount,
			final Double cessRate, final Double cessAmount) {

		this.id = id;
		this.b2bInvId = b2bInvId;
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

	



}
