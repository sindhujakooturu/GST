package com.gst.organisation.b2binvoice.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.gst.infrastructure.core.domain.AbstractPersistableCustom;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "g_b2b_inv_details", uniqueConstraints = {})
public class B2BInvoice extends AbstractPersistableCustom<Long>{
	
	@Column(name = "gstin", length = 15)
    private String gstin;
	
	@Column(name = "ctin", length = 15)
    private String ctin;
	
	@Column(name = "action_flag", length = 1)
    private String actionFlag;
	
	@Column(name = "supplier_inv_no", length = 1)
    private String supplierInvNo;
	
	@Column(name = "supplier_inv_date")
    private Date supplierInvDate;
	
	@Column(name = "supplier_inv_value")
    private Float supplierInvValue;
	
	@Column(name = "supply_place")
	private String supplyPlace;
	
	@Column(name = "is_reverse")
	private boolean isReverse;
	
	@Column(name = "is_prov_assessment")
	private boolean isProvAssessment;
	
	@Column(name = "order_no", length = 30)
    private String orderNo;
	
	@Column(name = "order_date")
    private Date orderDate;
	
	@Column(name = "etin", length = 15)
    private String etin;
	
	@Column(name = "invoice_id", length = 15)
    private Long invoiceId;
	
	
	@Column(name = "status")
    private boolean status;


	public B2BInvoice() {
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}
	

	
}
