package com.gst.organisation.gstr1fileinvoice.data;

import java.math.BigDecimal;

public class Gstr1FileB2BItemData {

	private Long id;
	private Long invoiceId;
	private String fileNo;
	private String itemType;
	private String itemCode;
	private BigDecimal taxValue;
	private BigDecimal igstRate;
	private BigDecimal igstAmount;
	private BigDecimal cgstRate;
	private BigDecimal cgstAmount;
	private BigDecimal sgstRate;
	private BigDecimal sgstAmount;
	private BigDecimal cessRate;
	private BigDecimal cessAmount;
	private int status;
	private String errorCode;
	private String errorDescriptor;
	
	public Gstr1FileB2BItemData() {
	}

	public Gstr1FileB2BItemData(final Long id, final Long invoiceId, final String fileNo, final String itemType, final String itemCode,
			final BigDecimal taxValue, final BigDecimal igstRate, final BigDecimal igstAmount, final BigDecimal cgstRate, final BigDecimal cgstAmount, final BigDecimal sgstRate,
			final BigDecimal sgstAmount, final BigDecimal cessRate, final BigDecimal cessAmount, final int status, final String errorCode,
			final String errorDescriptor) {

		this.id = id;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public BigDecimal getTaxValue() {
		return taxValue;
	}

	public void setTaxValue(BigDecimal taxValue) {
		this.taxValue = taxValue;
	}

	public BigDecimal getIgstRate() {
		return igstRate;
	}

	public void setIgstRate(BigDecimal igstRate) {
		this.igstRate = igstRate;
	}

	public BigDecimal getIgstAmount() {
		return igstAmount;
	}

	public void setIgstAmount(BigDecimal igstAmount) {
		this.igstAmount = igstAmount;
	}

	public BigDecimal getCgstRate() {
		return cgstRate;
	}

	public void setCgstRate(BigDecimal cgstRate) {
		this.cgstRate = cgstRate;
	}

	public BigDecimal getCgstAmount() {
		return cgstAmount;
	}

	public void setCgstAmount(BigDecimal cgstAmount) {
		this.cgstAmount = cgstAmount;
	}

	public BigDecimal getSgstRate() {
		return sgstRate;
	}

	public void setSgstRate(BigDecimal sgstRate) {
		this.sgstRate = sgstRate;
	}

	public BigDecimal getSgstAmount() {
		return sgstAmount;
	}

	public void setSgstAmount(BigDecimal sgstAmount) {
		this.sgstAmount = sgstAmount;
	}

	public BigDecimal getCessRate() {
		return cessRate;
	}

	public void setCessRate(BigDecimal cessRate) {
		this.cessRate = cessRate;
	}

	public BigDecimal getCessAmount() {
		return cessAmount;
	}

	public void setCessAmount(BigDecimal cessAmount) {
		this.cessAmount = cessAmount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescriptor() {
		return errorDescriptor;
	}

	public void setErrorDescriptor(String errorDescriptor) {
		this.errorDescriptor = errorDescriptor;
	}
}
