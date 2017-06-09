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
@Table(name = "g_gstr1_file_invoice_data")
public class Gstr1FileInvoice extends AbstractPersistableCustom<Long> {

	private static final long serialVersionUID = 1L;

	@Column(name = "gstin")
	private String gstin;

	@Column(name = "fp")
	private Date fp;

	@Column(name = "gross_turnover")
	private String grossTurnover;

	@Column(name = "file_no")
	private String fileNo;

	@Column(name = "version")
	private Integer version;

	@Column(name = "status")
	private Integer status;

	@Column(name = "assigned_to")
	private String assignedTo;
	
	@Column(name = "error_code")
	private String errorCode;

	@Column(name = "error_descriptor")
	private String errorDescr;

	@Column(name = "review_comments")
	private String reviewComments;

	/*@Column(name = "ref_no")
	private String refNo;

	@Column(name = "trans_id")
	private String transId;*/

	public Gstr1FileInvoice() {
		
	}

	public Gstr1FileInvoice(final String gstin, final Date fp,
			final String grossTurnover, final String fileNo,
			final Integer version, final Integer status,final String assignedTo,
			final String errorCode,final String errorDescr,final String reviewComments
			/*final String refNo,final String transId*/) {

		this.gstin = gstin;
		this.fp = fp;
		this.grossTurnover = grossTurnover;
		this.fileNo = fileNo;
		this.version = version;
		this.status = status;
		this.assignedTo = assignedTo;
		this.errorCode = errorCode;
		this.errorDescr = errorDescr;
		this.reviewComments = reviewComments;
		/*this.refNo = refNo;
		this.transId = transId;*/
	}
	
	/**
	 * @param command
	 * @return OutWardInv constructor
	 */
	public static Gstr1FileInvoice fromJson(final JsonCommand command) {

		final String gstin = command.stringValueOfParameterNamed("gstin");
		final Date fp = command.DateValueOfParameterNamed("fp");
		final String grossTurnover = command.stringValueOfParameterNamed("grossTurnover");
		final String fileNo = command.stringValueOfParameterNamed("fileNo");
		final Integer version = command.integerValueOfParameterNamed("version");
		final Integer status = command.integerValueOfParameterNamed("status");
		final String assignedTo = command.stringValueOfParameterNamed("assignedTo");
		final String errorCode = command.stringValueOfParameterNamed("errorCode");
		final String errorDescr = command.stringValueOfParameterNamed("errorDescr");
		final String reviewComments = command.stringValueOfParameterNamed("reviewComments");
		/*final String refNo = command.stringValueOfParameterNamed("refNo");
		final String transId = command.stringValueOfParameterNamed("transId");*/

		return new Gstr1FileInvoice(gstin, fp, grossTurnover,
				fileNo, version, status,assignedTo, errorCode,errorDescr,reviewComments/*,refNo,transId*/);
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
		if (command.isChangeInStringParameterNamed("grossTurnover",this.grossTurnover)) {
			final String newValue = command.stringValueOfParameterNamed("grossTurnover");
			actualChanges.put("grossTurnover", newValue);
			this.grossTurnover = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInStringParameterNamed("fileNo",this.fileNo)) {
			final String newValue = command.stringValueOfParameterNamed("fileNo");
			actualChanges.put("fileNo", newValue);
			this.fileNo = newValue;
		}
		if (command.isChangeInIntegerParameterNamed("version",this.version)) {
			final Integer newValue = command.integerValueOfParameterNamed("version");
			actualChanges.put("version", newValue);
			this.version = newValue;
		}
		
		if (command.isChangeInIntegerParameterNamed("status",this.status)) {
			final Integer newValue = command.integerValueOfParameterNamed("status");
			actualChanges.put("status", newValue);
			this.status = newValue;
		}
		
		if (command.isChangeInStringParameterNamed("assignedTo",this.assignedTo)) {
			final String newValue = command.stringValueOfParameterNamed("assignedTo");
			actualChanges.put("assignedTo", newValue);
			this.assignedTo = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInStringParameterNamed("errorCode",this.errorCode)) {
			final String newValue = command.stringValueOfParameterNamed("errorCode");
			actualChanges.put("errorCode", newValue);
			this.errorCode = StringUtils.defaultIfEmpty(newValue, null);
		}
		if (command.isChangeInStringParameterNamed("errorDescr",this.errorDescr)) {
			final String newValue = command.stringValueOfParameterNamed("errorDescr");
			actualChanges.put("errorDescr", newValue);
			this.errorDescr = newValue;
		}
		if (command.isChangeInStringParameterNamed("reviewComments",this.reviewComments)) {
			final String newValue = command.stringValueOfParameterNamed("reviewComments");
			actualChanges.put("reviewComments", newValue);
			this.reviewComments = newValue;
		}
		/*if (command.isChangeInStringParameterNamed("refNo",this.refNo)) {
			final String newValue = command.stringValueOfParameterNamed("refNo");
			actualChanges.put("refNo", newValue);
			this.refNo = newValue;
		}
		if (command.isChangeInStringParameterNamed("transId",this.transId)) {
			final String newValue = command.stringValueOfParameterNamed("transId");
			actualChanges.put("transId", newValue);
			this.transId = newValue;
		}*/

		return actualChanges;
	}

}
