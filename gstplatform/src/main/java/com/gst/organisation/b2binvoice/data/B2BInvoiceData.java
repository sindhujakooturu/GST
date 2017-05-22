package com.gst.organisation.b2binvoice.data;

import java.util.Collection;

public class B2BInvoiceData {
	
	private Long id;
	private String gstin;
	private String ctin;
	private String actionFlag;
	private String supplierInvNo;
	private String supplierInvDate;
	private Double supplierInvValue;
	private String supplyPlace;
	private Boolean isReverse;
	private Boolean isProvAssessment;
	private String orderNo;
	private String orderdate;
	private String etin;
	private Long invoiceId;
	private Boolean status;
	
	
	private Collection<B2BInvoiceDetailsData> b2BInvoiceDetailsData;
	
	public B2BInvoiceData() {

	}


	public B2BInvoiceData(final Long id, final String gstin, final String ctin, final String actionFlag, final String supplierInvNo,
			final String supplierInvDate, final Double supplierInvValue, final String supplyPlace, final Boolean isReverse,
			final Boolean isProvAssessment, final String orderNo, final String orderdate, final String etin, final Long invoiceId) {
		
		this.id = id;
		this.gstin = gstin;
		this.ctin = ctin;
		this.actionFlag = actionFlag;
		this.supplierInvNo = supplierInvNo;
		this.supplierInvDate = supplierInvDate;
		this.supplierInvValue = supplierInvValue;
		this.supplyPlace = supplyPlace;
		this.isReverse = isReverse;
		this.isProvAssessment = isProvAssessment;
		this.orderNo = orderNo;
		this.orderdate = orderdate;
		this.etin = etin;
		this.invoiceId = invoiceId;
	}


	
	public Long getId() {
		return id;
	}


	public void setB2BInvoiceDetailsData(Collection<B2BInvoiceDetailsData> b2bInvoiceDetailsData) {
		b2BInvoiceDetailsData = b2bInvoiceDetailsData;
	}
	
	

	
	
}
