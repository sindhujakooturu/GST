package com.gst.organisation.hsndata.domain;

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
@Table(name = "g_hsn_data", uniqueConstraints = {@UniqueConstraint(columnNames = { "hsn_code" }, name = "hsnCode_UNIQUE") })
public class Hsndata extends AbstractPersistableCustom<Long>{
	private static final long serialVersionUID = 1L;
	
	@Column(name = "hsn_code", length = 8)
    private String hsnCode;
	
    @Column(name = "description", length = 256)
    private String description;
    
    @Column(name = "is_deleted", length = 6)
    private String deleted="false";
  
    
    public static Hsndata fromJson(final JsonCommand command) {
    	
    final String hsnCodeParamName = "hsnCode";
    final String hsnCode = command.stringValueOfParameterNamed(hsnCodeParamName);
    final String descriptionParamName = "description";
    final String description = command.stringValueOfParameterNamed(descriptionParamName);
    
    return new Hsndata(hsnCode,description);
    
  }

    public  Hsndata() {}
    
    private Hsndata(final String hsnCode, final String description) {
	this.hsnCode = hsnCode;
    this.description = description;
    
  }

    public String getHsnCode() {
	return hsnCode;
  }

    public String getDescription() {
	return description;
   }

    public Map<String, Object> update(final JsonCommand command) {
    final Map<String, Object> actualChanges = new LinkedHashMap<>();
    
    
    final String hsnCodeParamName = "hsnCode";
    if (command.isChangeInStringParameterNamed(hsnCodeParamName, this.hsnCode)) {
        final String newValue = command.stringValueOfParameterNamed(hsnCodeParamName);
        actualChanges.put(hsnCodeParamName, newValue);
        this.hsnCode=StringUtils.defaultIfEmpty(newValue, null);
    }
    
    final String descriptionParamName = "description";
    if (command.isChangeInStringParameterNamed(descriptionParamName, this.description)) {
        final String newValue = command.stringValueOfParameterNamed(descriptionParamName);
        actualChanges.put(descriptionParamName, newValue);
        this.description=StringUtils.defaultIfEmpty(newValue, null);
    }
   
    return actualChanges;
}
    public void delete() {
    	this.hsnCode=this.getId()+"_"+this.hsnCode;
    	this.deleted="true";   
      }

}
    
