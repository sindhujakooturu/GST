package com.gst.organisation.outwardstaginginv.data;

<<<<<<< HEAD
import java.math.BigDecimal;
import java.util.List;

=======
import java.util.Date;
>>>>>>> upstream/master

public class OutWardStagingInvData {

	private Long id;
	private String gstin;
	private String gstinPurchaser;
	private String cName;
	private String supplierInvNo;
<<<<<<< HEAD
	private String supplierInvDate;
	private Long supplierInvValue;
	private String supplyStateCode;
	private String orderNo;
	private String orderDate;
	private String etin;
	private Long invoiceId;
	private String receiptStateCode;
	private int status;
	private String errorCode;
	private String errorDescripter;
	int modeNo;

=======
	private Date supplierInvDate;
	private Long supplierInvValue;
	private String supplyStateCode;
	private String orderNo;
	private Date orderDate;
	private String etin;
	private Long invoiceId;
	private String receiptStateCode;
	private Long status;
	private String errorCode;
	private String errorDescripter;
>>>>>>> upstream/master

	public OutWardStagingInvData() {
		
	}

<<<<<<< HEAD
	
	public OutWardStagingInvData(final Long id, final String gstin, final String gstinPurchaser,
			final String cName, final String supplierInvNo, final String supplierInvDate,
			final Long supplierInvValue, final String supplyStateCode,final String orderNo ,final String orderDate,
			final String etin,final Long invoiceId,final String receiptStateCode,final Integer status,final String errorCode,final String errorDescripter,
			final Integer modeNo) {
=======
	public OutWardStagingInvData(final Long id, final String gstin, final String gstinPurchaser,
			final String cName, final String supplierInvNo, final Date supplierInvDate,
			final Long supplierInvValue, final String supplyStateCode,final String orderNo ,final Date orderDate,
			final String etin,final Long invoiceId,final String receiptStateCode,final Long status,final String errorCode,
			final String errorDescripter) {
>>>>>>> upstream/master
		
		this.id = id;
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
<<<<<<< HEAD
		this.modeNo = modeNo;
=======
>>>>>>> upstream/master
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

	public String getGstinPurchaser() {
		return gstinPurchaser;
	}

	public void setGstinPurchaser(String gstinPurchaser) {
		this.gstinPurchaser = gstinPurchaser;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getSupplierInvNo() {
		return supplierInvNo;
	}

	public void setSupplierInvNo(String supplierInvNo) {
		this.supplierInvNo = supplierInvNo;
	}

<<<<<<< HEAD
	public String getSupplierInvDate() {
		return supplierInvDate;
	}

	public void setSupplierInvDate(String supplierInvDate) {
=======
	public Date getSupplierInvDate() {
		return supplierInvDate;
	}

	public void setSupplierInvDate(Date supplierInvDate) {
>>>>>>> upstream/master
		this.supplierInvDate = supplierInvDate;
	}

	public Long getSupplierInvValue() {
		return supplierInvValue;
	}

	public void setSupplierInvValue(Long supplierInvValue) {
		this.supplierInvValue = supplierInvValue;
	}

	public String getSupplyStateCode() {
		return supplyStateCode;
	}

	public void setSupplyStateCode(String supplyStateCode) {
		this.supplyStateCode = supplyStateCode;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

<<<<<<< HEAD
	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
=======
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
>>>>>>> upstream/master
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

	public String getReceiptStateCode() {
		return receiptStateCode;
	}

	public void setReceiptStateCode(String receiptStateCode) {
		this.receiptStateCode = receiptStateCode;
	}

<<<<<<< HEAD
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
=======
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
>>>>>>> upstream/master
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescripter() {
		return errorDescripter;
	}

	public void setErrorDescripter(String errorDescripter) {
		this.errorDescripter = errorDescripter;
	}

<<<<<<< HEAD
	public int getModeNo() {
		return modeNo;
	}

	public void setModeNo(int modeNo) {
		this.modeNo = modeNo;
	}


=======
>>>>>>> upstream/master
}
