package com.gst.organisation.MasterScreenTest.domain;

import java.math.BigDecimal;
import java.util.Date;

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
@Table(name = "tablename")
public class MasterTest extends AbstractPersistableCustom<Long> {

	private static final long serialVersionUID = 1L;

	@Column(name = "column1")
	private String column1;

	@Column(name = "column2")
	private String column2;

	@Column(name = "column3")
	private String column3;

	@Column(name = "column4")
	private String column4;

	@Column(name = "column5")
	private Date column5;

	@Column(name = "column6")
	private BigDecimal column6;

	@Column(name = "column7")
	private String column7;
	
	@Column(name = "column8")
	private String column8;

	@Column(name = "column9")
	private Date column9;

	@Column(name = "column10")
	private String column10;

	@Column(name = "column11")
	private Long column11;

	@Column(name = "column12")
	private String column12;

	@Column(name = "status")
	private Integer status;

	@Column(name = "column13")
	private String column13;
	
	@Column(name = "column14")
	private String column14;
	
	public MasterTest() {
		
	}

	public MasterTest(final String column1, final String column2,final String column3, final String column4,
			final Date column5, final BigDecimal column6,final String column7,final String column8,final Date column9,
			final String column10,final Long column11,final String column12,final Integer status,
			final String column13,final String column14) {

		this.column1 = column1;
		this.column2 = column2;
		this.column3 = column3;
		this.column4 = column4;
		this.column5 = column5;
		this.column6 = column6;
		this.column7 = column7;
		this.column8 = column8;
		this.column9 = column9;
		this.column10 = column10;
		this.column11 = column11;
		this.column12 = column12;
		this.status = status;
		this.column13 = column13;
		this.column14 = column14;
	}
	
	/**
	 * @param command
	 * @return OutWardInv constructor
	 */
	public static MasterTest fromJson(final JsonCommand command) {

		final String column1 = command.stringValueOfParameterNamed("column1");
		final String column2 = command.stringValueOfParameterNamed("column2");
		final String column3 = command.stringValueOfParameterNamed("column3");
		final String column4 = command.stringValueOfParameterNamed("column4");
		final Date column5 = command.DateValueOfParameterNamed("column5");
		final BigDecimal column6 = command.bigDecimalValueOfParameterNamed("column6");
		final String column7 = command.stringValueOfParameterNamed("column7");
		final String column8 = command.stringValueOfParameterNamed("column8");
		final Date column9 = command.DateValueOfParameterNamed("column9");
		final String column10 = command.stringValueOfParameterNamed("column10");
		final Long column11 = command.longValueOfParameterNamed("column11");
		final String column12 = command.stringValueOfParameterNamed("column12");
		final Integer status = command.integerValueOfParameterNamed("status");
		final String column13 = command.stringValueOfParameterNamed("column13");
		final String column14 = command.stringValueOfParameterNamed("column14");

		return new MasterTest(column1, column2, column3,
				column4, column5, column6, column7,column8,column9,column10,column11,column12,
				status,column13,column14);
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
