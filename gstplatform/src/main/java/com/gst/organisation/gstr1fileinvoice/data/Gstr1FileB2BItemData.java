package com.gst.organisation.gstr1fileinvoice.data;

public class Gstr1FileB2BItemData {

	private Long id;
	private Long invoiceId;
	private String fileNo;
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
	private int status;
	private String errorCode;
	private String errorDescriptor;
	
	public Gstr1FileB2BItemData() {
	}

	public Gstr1FileB2BItemData(Long id, Long invoiceId, String fileNo, String itemType, String itemCode,
			Double taxValue, Double igstRate, Double igstAmount, Double cgstRate, Double cgstAmount, Double sgstRate,
			Double sgstAmount, Double cessRate, Double cessAmount, int status, String errorCode,
			String errorDescriptor) {

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

	public Double getTaxValue() {
		return taxValue;
	}

	public void setTaxValue(Double taxValue) {
		this.taxValue = taxValue;
	}

	public Double getIgstRate() {
		return igstRate;
	}

	public void setIgstRate(Double igstRate) {
		this.igstRate = igstRate;
	}

	public Double getIgstAmount() {
		return igstAmount;
	}

	public void setIgstAmount(Double igstAmount) {
		this.igstAmount = igstAmount;
	}

	public Double getCgstRate() {
		return cgstRate;
	}

	public void setCgstRate(Double cgstRate) {
		this.cgstRate = cgstRate;
	}

	public Double getCgstAmount() {
		return cgstAmount;
	}

	public void setCgstAmount(Double cgstAmount) {
		this.cgstAmount = cgstAmount;
	}

	public Double getSgstRate() {
		return sgstRate;
	}

	public void setSgstRate(Double sgstRate) {
		this.sgstRate = sgstRate;
	}

	public Double getSgstAmount() {
		return sgstAmount;
	}

	public void setSgstAmount(Double sgstAmount) {
		this.sgstAmount = sgstAmount;
	}

	public Double getCessRate() {
		return cessRate;
	}

	public void setCessRate(Double cessRate) {
		this.cessRate = cessRate;
	}

	public Double getCessAmount() {
		return cessAmount;
	}

	public void setCessAmount(Double cessAmount) {
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
