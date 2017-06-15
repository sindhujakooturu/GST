package com.gst.organisation.MasterScreenTest.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.domain.AbstractPersistableCustom;

/**
 * @author Trigital
 * 
 */
@Entity
@Table(name = "ctablename")
public class MasterChildTest extends AbstractPersistableCustom<Long> {

	private static final long serialVersionUID = 1L;

	@Column(name = "invoice_id")
	private Long invoiceId;

	@Column(name = "column15")
	private String column15;

	@Column(name = "column16")
	private String column16;

	@Column(name = "column17")
	private BigDecimal column17;

	@Column(name = "column18")
	private BigDecimal column18;

	@Column(name = "column19")
	private BigDecimal column19;

	@Column(name = "column20")
	private BigDecimal column20;
	
	@Column(name = "column21")
	private BigDecimal column21;

	@Column(name = "column22")
	private BigDecimal column22;

	@Column(name = "column23")
	private BigDecimal column23;

	@Column(name = "column24")
	private BigDecimal column24;

	@Column(name = "column25")
	private BigDecimal column25;

	@Column(name = "status")
	private Integer status;

	@Column(name = "column26")
	private String column26;
	
	@Column(name = "column27")
	private String column27;
	
	public MasterChildTest() {
		
	}

	public MasterChildTest(final Long invoiceId, final String column15, final String column16,final BigDecimal column17, final BigDecimal column18,
			final BigDecimal column19, final BigDecimal column20,final BigDecimal column21,final BigDecimal column22,final BigDecimal column23,
			final BigDecimal column24,final BigDecimal column25,final Integer status,
			final String column26,final String column27) {

		this.invoiceId = invoiceId;
		this.column15 = column15;
		this.column16 = column16;
		this.column17 = column17;
		this.column18 = column18;
		this.column19 = column19;
		this.column20 = column20;
		this.column21 = column21;
		this.column22 = column22;
		this.column23 = column23;
		this.column24 = column24;
		this.column25 = column25;
		this.status = status;
		this.column26 = column26;
		this.column27 = column27;
	}
	
	/**
	 * @param command
	 * @return MasterTestChild constructor
	 */
	public static MasterChildTest fromJson(final JsonCommand command) {

		final Long invoiceId = command.longValueOfParameterNamed("invoiceId");
		final String column15 = command.stringValueOfParameterNamed("column15");
		final String column16 = command.stringValueOfParameterNamed("column16");
		final BigDecimal column17 = command.bigDecimalValueOfParameterNamed("column17");
		final BigDecimal column18 = command.bigDecimalValueOfParameterNamed("column18");
		final BigDecimal column19 = command.bigDecimalValueOfParameterNamed("column19");
		final BigDecimal column20 = command.bigDecimalValueOfParameterNamed("column20");
		final BigDecimal column21 = command.bigDecimalValueOfParameterNamed("column21");
		final BigDecimal column22 = command.bigDecimalValueOfParameterNamed("column22");
		final BigDecimal column23 = command.bigDecimalValueOfParameterNamed("column23");
		final BigDecimal column24 = command.bigDecimalValueOfParameterNamed("column24");
		final BigDecimal column25 = command.bigDecimalValueOfParameterNamed("column25");
		final Integer status = command.integerValueOfParameterNamed("status");
		final String column26 = command.stringValueOfParameterNamed("column26");
		final String column27 = command.stringValueOfParameterNamed("column27");

		return new MasterChildTest(invoiceId,column15, column16, column17,
				column18, column19, column20, column21,column22,column23,column24,column25,
				status,column26,column27);
	}

	/**
	 * @param command
	 * @return changes of OutWardStagingInv object
	 */
	/*public Map<String, Object> update(JsonCommand command) {

		final Map<String, Object> actualChanges = new LinkedHashMap<String, Object>(1);
		
		if (command.isChangeInStringParameterNamed("gstin",this.gstin)) {
			final String newValue = command.stringValueOfParameterNamed("gstin");
			actualChanges.put("gstin", newValue);
			this.gstin = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInStringParameterNamed("gstinPurchaser",this.gstinPurchaser)) {
			final String newValue = command.stringValueOfParameterNamed("gstinPurchaser");
			actualChanges.put("gstinPurchaser", newValue);
			this.gstinPurchaser = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInStringParameterNamed("cName",this.cName)) {
			final String newValue = command.stringValueOfParameterNamed("cName");
			actualChanges.put("cName", newValue);
			this.cName = StringUtils.defaultIfEmpty(newValue, null);
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
		
		if (command.isChangeInStringParameterNamed("supplyStateCode",this.supplyStateCode)) {
			final String newValue = command.stringValueOfParameterNamed("supplyStateCode");
			actualChanges.put("supplyStateCode", newValue);
			this.supplyStateCode = StringUtils.defaultIfEmpty(newValue, null);
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
		if (command.isChangeInStringParameterNamed("receiptStateCode",this.receiptStateCode)) {
			final String newValue = command.stringValueOfParameterNamed("receiptStateCode");
			actualChanges.put("receiptStateCode", newValue);
			this.receiptStateCode = newValue;
		}
		if (command.isChangeInIntegerParameterNamed("status",this.status)) {
			final Integer newValue = command.integerValueOfParameterNamed("status");
			actualChanges.put("status", newValue);
			this.status = newValue;
		}
		
		if (command.isChangeInStringParameterNamed("errorCode",this.errorCode)) {
			final String newValue = command.stringValueOfParameterNamed("errorCode");
			actualChanges.put("errorCode", newValue);
			this.errorCode = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInStringParameterNamed("errorDescripter",this.errorDescripter)) {
			final String newValue = command.stringValueOfParameterNamed("errorDescripter");
			actualChanges.put("errorDescripter", newValue);
			this.errorDescripter = StringUtils.defaultIfEmpty(newValue, null);
		}

		return actualChanges;
	}*/

}
