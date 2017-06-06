package com.gst.organisation.gstr1fileinvoice.data;

import java.util.Collection;
import java.util.Date;

public class Gstr1FileB2BInvoiceData {
	
	private Long id;
	private String gstin;
	private Date fp;
	private String grossTurnover;
	private String fileNo;
	private String supplierInvNo;
	private String supplierInvDate;
	private Double supplierInvValue;
	private String supplyPlace;
	private String orderNo;
	private Date orderDate;
	private String etin;
	private Long invoiceId;
	private String flag;
	private String checkSum;
	private int isReverse;
	private int isProvisional;
	private int recordType;
	private String status;
	private String errorCode;
	private String errorDescriptor;
	
	
	private Collection<Gstr1FileB2BItemData> gstr1FileB2BItemData;
	
	public Gstr1FileB2BInvoiceData() {
	}


	public Gstr1FileB2BInvoiceData(Long id, String gstin, Date fp, String grossTurnover, String fileNo,
			String supplierInvNo, String supplierInvDate, Double supplierInvValue, String supplyPlace, String orderNo,
			Date orderDate, String etin, Long invoiceId, String flag, String checkSum, int isReverse, int isProvisional,
			int recordType, String status, String errorCode, String errorDescriptor) {

		this.id = id;
		this.gstin = gstin;
		this.fp = fp;
		this.grossTurnover = grossTurnover;
		this.fileNo = fileNo;
		this.supplierInvNo = supplierInvNo;
		this.supplierInvDate = supplierInvDate;
		this.supplierInvValue = supplierInvValue;
		this.supplyPlace = supplyPlace;
		this.orderNo = orderNo;
		this.orderDate = orderDate;
		this.etin = etin;
		this.invoiceId = invoiceId;
		this.flag = flag;
		this.checkSum = checkSum;
		this.isReverse = isReverse;
		this.isProvisional = isProvisional;
		this.recordType = recordType;
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


	public String getGstin() {
		return gstin;
	}


	public void setGstin(String gstin) {
		this.gstin = gstin;
	}


	public Date getFp() {
		return fp;
	}


	public void setFp(Date fp) {
		this.fp = fp;
	}


	public String getGrossTurnover() {
		return grossTurnover;
	}


	public void setGrossTurnover(String grossTurnover) {
		this.grossTurnover = grossTurnover;
	}


	public String getFileNo() {
		return fileNo;
	}


	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}


	public String getSupplierInvNo() {
		return supplierInvNo;
	}


	public void setSupplierInvNo(String supplierInvNo) {
		this.supplierInvNo = supplierInvNo;
	}


	public String getSupplierInvDate() {
		return supplierInvDate;
	}


	public void setSupplierInvDate(String supplierInvDate) {
		this.supplierInvDate = supplierInvDate;
	}


	public Double getSupplierInvValue() {
		return supplierInvValue;
	}


	public void setSupplierInvValue(Double supplierInvValue) {
		this.supplierInvValue = supplierInvValue;
	}


	public String getSupplyPlace() {
		return supplyPlace;
	}


	public void setSupplyPlace(String supplyPlace) {
		this.supplyPlace = supplyPlace;
	}


	public String getOrderNo() {
		return orderNo;
	}


	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}


	public Date getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}


	public String getEtin() {
		return etin;
	}


	public void setEtin(String etin) {
		this.etin = etin;
	}


	public Long getInvoiceId() {
		return invoiceId;
	}


	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}


	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
	}


	public String getCheckSum() {
		return checkSum;
	}


	public void setCheckSum(String checkSum) {
		this.checkSum = checkSum;
	}


	public int getIsReverse() {
		return isReverse;
	}


	public void setIsReverse(int isReverse) {
		this.isReverse = isReverse;
	}


	public int getIsProvisional() {
		return isProvisional;
	}


	public void setIsProvisional(int isProvisional) {
		this.isProvisional = isProvisional;
	}


	public int getRecordType() {
		return recordType;
	}


	public void setRecordType(int recordType) {
		this.recordType = recordType;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
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
