package com.gst.organisation.sacdata.domain;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.StringUtils;

import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.domain.AbstractPersistableCustom;

@Entity
@Table(name = "cfg_sac_data", uniqueConstraints = {@UniqueConstraint(columnNames = { "sac_seq_id" }, name = "sacSeqId_UNIQUE"),
        @UniqueConstraint(columnNames = { "service_name" }, name = "serviceName_UNIQUE") })

public class Sacdata extends AbstractPersistableCustom<Long>{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "sac_seq_id", length = 4)
    private String sacSeqId;

    @Column(name = "service_name", length = 60)
    private String serviceName;

    @Column(name = "description", length = 256)
    private String description;
    
    @Column(name = "sac_tax_collection", length = 10)
    private String sacTaxCollection;

    @Column(name = "sac_other_reciept", length = 10)
    private String sacOtherReciept;

    @Column(name = "sac_deduct_refund", length = 10)
    private String sacDeductRefund;
    
    @Column(name = "sac_penalty", length = 10)
    private String sacPenalty;

	

    public static Sacdata fromJson(final JsonCommand command) {
    	
    	final String sacSeqIdParamName = "sacSeqId";
        final String sacSeqId = command.stringValueOfParameterNamed(sacSeqIdParamName);

        final String serviceNameParamName = "serviceName";
        final String serviceName = command.stringValueOfParameterNamed(serviceNameParamName);

        final String descriptionParamName = "description";
        final String description = command.stringValueOfParameterNamed(descriptionParamName);

        final String sacTaxCollectionParamName = "sacTaxCollection";
        final String sacTaxCollection = command.stringValueOfParameterNamedAllowingNull(sacTaxCollectionParamName);

        final String sacOtherRecieptParamName = "sacOtherReciept";
        final String sacOtherReciept = command.stringValueOfParameterNamed(sacOtherRecieptParamName);

        final String sacDeductRefundParamName = "sacDeductRefund";
        final String sacDeductRefund = command.stringValueOfParameterNamed(sacDeductRefundParamName);
        
        final String sacPenaltyParamName = "sacPenalty";
        final String sacPenalty = command.stringValueOfParameterNamed(sacPenaltyParamName);

    return new Sacdata(sacSeqId, serviceName, description, sacTaxCollection, sacOtherReciept, sacDeductRefund, sacPenalty);
    }

    public  Sacdata() {

    		}
private Sacdata(final String sacSeqId, final String serviceName, final String description, final String sacTaxCollection, final String sacOtherReciept,
        final String sacDeductRefund, final String sacPenalty) {
	

	this.sacSeqId=sacSeqId;
	this.serviceName = serviceName;
    this.description = description;
    this.sacTaxCollection = sacTaxCollection;
    this.sacOtherReciept = sacOtherReciept;
    this.sacDeductRefund = sacDeductRefund;
    this.sacPenalty = sacPenalty;
  }
  	


public String getSacSeqId() {
	return sacSeqId;
}

public String getServiceName() {
	return serviceName;
}

public String getDescription() {
	return description;
}

public String getSacTaxCollection() {
	return sacTaxCollection;
}

public String getSacOtherReciept() {
	return sacOtherReciept;
}

public String getSacDeductRefund() {
	return sacDeductRefund;
}

public String getSacPenalty() {
	return sacPenalty;
}

public Map<String, Object> update(final JsonCommand command) {

    final Map<String, Object> actualChanges = new LinkedHashMap<>();
    
    
    final String sacSeqIdParamName = "sacSeqId";
    if (command.isChangeInStringParameterNamed(sacSeqIdParamName, this.sacSeqId)) {
        final String newValue = command.stringValueOfParameterNamed(sacSeqIdParamName);
        actualChanges.put(sacSeqIdParamName, newValue);
        this.sacSeqId = StringUtils.defaultIfEmpty(newValue, null);
    }
    
    final String serviceNameParamName = "serviceName";
    if (command.isChangeInStringParameterNamed(serviceNameParamName, this.serviceName)) {
        final String newValue = command.stringValueOfParameterNamed(serviceNameParamName);
        actualChanges.put(serviceNameParamName, newValue);
        this.serviceName = StringUtils.defaultIfEmpty(newValue, null);
    }
    
    final String descriptionParamName = "description";
    if (command.isChangeInStringParameterNamed(descriptionParamName, this.description)) {
        final String newValue = command.stringValueOfParameterNamed(descriptionParamName);
        actualChanges.put(descriptionParamName, newValue);
        this.description=StringUtils.defaultIfEmpty(newValue, null);
    }
    
    final String sacTaxCollectionParamName = "sacTaxCollection";
    if (command.isChangeInStringParameterNamed(sacTaxCollectionParamName, this.sacTaxCollection)) {
        final String newValue = command.stringValueOfParameterNamed(sacTaxCollectionParamName);
        actualChanges.put(sacTaxCollectionParamName, newValue);
        this.sacTaxCollection=StringUtils.defaultIfEmpty(newValue, null);
    }
    
    final String sacOtherRecieptParamName = "sacOtherReciept";
    if (command.isChangeInStringParameterNamed(sacTaxCollectionParamName, this.sacOtherReciept)) {
        final String newValue = command.stringValueOfParameterNamed(sacOtherRecieptParamName);
        actualChanges.put(sacOtherRecieptParamName, newValue);
        this.sacOtherReciept =StringUtils.defaultIfEmpty(newValue, null);
    }
    
   
	final String sacDeductRefundParamName = "sacDeductRefund";
    if (command.isChangeInStringParameterNamed(sacDeductRefundParamName, this.sacDeductRefund)) {
        final String newValue = command.stringValueOfParameterNamed(sacDeductRefundParamName);
        actualChanges.put(sacDeductRefundParamName, newValue);
        this.sacDeductRefund = StringUtils.defaultIfEmpty(newValue, null);
    }
    
    final String sacPenaltyParamName = "sacPenalty";
    if (command.isChangeInStringParameterNamed(sacPenaltyParamName, this.sacPenalty)) {
        final String newValue = command.stringValueOfParameterNamed(sacPenaltyParamName);
        actualChanges.put(sacPenaltyParamName, newValue);
        this.sacPenalty = StringUtils.defaultIfEmpty(newValue, null);
    }
    return actualChanges;
}

}
    
