package com.gst.organisation.gstr1fileinvoice.domain;

import java.util.Date;
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
@Table(name = "g_gstr1_file_b2b_invoice")
public class Gstr1FileB2BInvoice extends AbstractPersistableCustom<Long> {

	private static final long serialVersionUID = 1L;

	@Column(name = "gstin")
	private String gstin;

	@Column(name = "fp")
	private Date fp;

	@Column(name = "file_no")
	private String fileNo;

	@Column(name = "supplier_inv_no")
	private String supplierInvNo;

	@Column(name = "supplier_inv_date")
	private Date supplierInvDate;

	@Column(name = "supplier_inv_value")
	private Long supplierInvValue;

	@Column(name = "supply_place")
	private String supplyPlace;
	
	@Column(name = "order_no")
	private String orderNo;

	@Column(name = "order_date")
	private Date orderDate;

	@Column(name = "etin")
	private String etin;

	@Column(name = "invoice_id")
	private Long invoiceId;

	@Column(name = "flag")
	private String flag;

	@Column(name = "check_sum")
	private String checkSum;

	@Column(name = "is_reverse")
	private Integer isReverse;
	
	@Column(name = "is_provisional")
	private Integer isProvisional;
	
	@Column(name = "record_type")
	private Integer recordType;

	@Column(name = "status")
	private String status;

	@Column(name = "error_code")
	private String errorCode;

	@Column(name = "error_descriptor")
	private String errorDescriptor;
	
	
	public Gstr1FileB2BInvoice() {
		
	}

	public Gstr1FileB2BInvoice(final String gstin, final Date fp,
			final String fileNo, final String supplierInvNo,
			final Date supplierInvDate, final Long supplierInvValue,
			final String supplyPlace,final String orderNo,final Date orderDate,
			final String etin,final Long invoiceId,final String flag,final String checkSum,
			final Integer isReverse,final Integer isProvisional,final Integer recordType,
			final String status,final String errorCode,final String errorDescriptor) {

		this.gstin = gstin;
		this.fp = fp;
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
	
	/**
	 * @param command
	 * @return OutWardInv constructor
	 */
	public static Gstr1FileB2BInvoice fromJson(final JsonCommand command) {

		final String gstin = command.stringValueOfParameterNamed("gstin");
		final Date fp = command.DateValueOfParameterNamed("fp");
		final String fileNo = command.stringValueOfParameterNamed("fileNo");
		final String supplierInvNo = command.stringValueOfParameterNamed("supplierInvNo");
		final Date supplierInvDate = command.DateValueOfParameterNamed("supplierInvDate");
		final Long supplierInvValue = command.longValueOfParameterNamed("supplierInvValue");
		final String supplyPlace = command.stringValueOfParameterNamed("supplyPlace");
		final String orderNo = command.stringValueOfParameterNamed("orderNo");
		final Date orderDate = command.DateValueOfParameterNamed("orderDate");
		final String etin = command.stringValueOfParameterNamed("etin");
		final Long invoiceId = command.longValueOfParameterNamed("invoiceId");
		final String flag = command.stringValueOfParameterNamed("flag");
		final String checkSum = command.stringValueOfParameterNamed("checkSum");
		final Integer isReverse = command.integerValueOfParameterNamed("isReverse");
		final Integer isProvisional = command.integerValueOfParameterNamed("isProvisional");
		final Integer recordType = command.integerValueOfParameterNamed("recordType");
		final String status = command.stringValueOfParameterNamed("status");
		final String errorCode = command.stringValueOfParameterNamed("errorCode");
		final String errorDescriptor = command.stringValueOfParameterNamed("errorDescriptor");

		return new Gstr1FileB2BInvoice(gstin, fp, fileNo, supplierInvNo, supplierInvDate, supplierInvValue, 
				supplyPlace,orderNo,orderDate,etin,invoiceId,flag,checkSum,isReverse,isProvisional,
				recordType,status,errorCode,errorDescriptor);
	}

	/**
	 * @param command
	 * @return changes of OutWardStagingInv object
	 */
	public Map<String, Object> update(JsonCommand command) {

		final Map<String, Object> actualChanges = new LinkedHashMap<String, Object>(1);
		
		if (command.isChangeInStringParameterNamed("gstin",this.gstin)) {
			final String newValue = command.stringValueOfParameterNamed("gstin");
			actualChanges.put("gstin", newValue);
			this.gstin = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInDateParameterNamed("fp",this.fp)) {
			final Date newValue = command.DateValueOfParameterNamed("fp");
			actualChanges.put("fp", newValue);
			this.fp = newValue;
		}
		if (command.isChangeInStringParameterNamed("fileNo",this.fileNo)) {
			final String newValue = command.stringValueOfParameterNamed("fileNo");
			actualChanges.put("fileNo", newValue);
			this.fileNo = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInStringParameterNamed("supplierInvNo",this.supplierInvNo)) {
			final String newValue = command.stringValueOfParameterNamed("supplierInvNo");
			actualChanges.put("supplierInvNo", newValue);
			this.supplierInvNo = newValue;
		}
		if (command.isChangeInDateParameterNamed("supplierInvDate",this.supplierInvDate)) {
			final Date newValue = command.DateValueOfParameterNamed("supplierInvDate");
			actualChanges.put("supplierInvDate", newValue);
			this.supplierInvDate = newValue;
		}
		
		if (command.isChangeInLongParameterNamed("supplierInvValue",this.supplierInvValue)) {
			final Long newValue = command.longValueOfParameterNamed("supplierInvValue");
			actualChanges.put("supplierInvValue", newValue);
			this.supplierInvValue = newValue;
		}
		
		if (command.isChangeInStringParameterNamed("supplyPlace",this.supplyPlace)) {
			final String newValue = command.stringValueOfParameterNamed("supplyPlace");
			actualChanges.put("supplyPlace", newValue);
			this.supplyPlace = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInStringParameterNamed("orderNo",this.orderNo)) {
			final String newValue = command.stringValueOfParameterNamed("orderNo");
			actualChanges.put("orderNo", newValue);
			this.orderNo = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInDateParameterNamed("orderDate",this.orderDate)) {
			final Date newValue = command.DateValueOfParameterNamed("orderDate");
			actualChanges.put("orderDate", newValue);
			this.orderDate = newValue;
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
		if (command.isChangeInStringParameterNamed("flag",this.flag)) {
			final String newValue = command.stringValueOfParameterNamed("flag");
			actualChanges.put("flag", newValue);
			this.flag = newValue;
		}
		
		if (command.isChangeInStringParameterNamed("checkSum",this.checkSum)) {
			final String newValue = command.stringValueOfParameterNamed("checkSum");
			actualChanges.put("checkSum", newValue);
			this.checkSum = newValue;
		}
		if (command.isChangeInIntegerParameterNamed("isReverse",this.isReverse)) {
			final Integer newValue = command.integerValueOfParameterNamed("isReverse");
			actualChanges.put("isReverse", newValue);
			this.isReverse = newValue;
		}
		if (command.isChangeInIntegerParameterNamed("isProvisional",this.isProvisional)) {
			final Integer newValue = command.integerValueOfParameterNamed("isProvisional");
			actualChanges.put("isProvisional", newValue);
			this.isProvisional = newValue;
		}
		if (command.isChangeInIntegerParameterNamed("recordType",this.recordType)) {
			final Integer newValue = command.integerValueOfParameterNamed("recordType");
			actualChanges.put("recordType", newValue);
			this.recordType = newValue;
		}
		
		if (command.isChangeInStringParameterNamed("status",this.status)) {
			final String newValue = command.stringValueOfParameterNamed("status");
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
