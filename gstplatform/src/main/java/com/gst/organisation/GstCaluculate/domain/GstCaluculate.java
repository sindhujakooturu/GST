package com.gst.organisation.GstCaluculate.domain;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.apache.commons.lang.StringUtils;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.domain.AbstractPersistableCustom;
import com.gst.organisation.GstCaluculate.data.GstCaluculateData;

@Entity
@Table(name="g_gst_rate")
public class GstCaluculate extends AbstractPersistableCustom<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="item_code", length = 16)
	private String itemcode;
	
	@Column(name="item_desc", length = 255)
	private String itemdesc;
	
	@Column(name="sgst_rate", length = 6)
    private Double sgstrate;
	
	@Column(name="cgst_rate", length = 6)
    private  Double cgstrate;
	
	@Column(name="igst_rate", length = 6)
    private  Double igstrate;
	
	@Column(name="cess_rate", length = 6)
    private  Double cessrate;
	
	
	public GstCaluculate(){
		
	}
	
	 public static GstCaluculate fromJson(final JsonCommand command) {

	        final String itemcodeParamName = "itemcode";
	        final String itemcode = command.stringValueOfParameterNamed(itemcodeParamName);

	        final String itemdescParamName = "itemdesc";
	        final String itemdesc = command.stringValueOfParameterNamed(itemdescParamName);

	        final String sgstrateParamName = "sgstrate";
	        final Long sgstrate = command.longValueOfParameterNamed(sgstrateParamName);

	        final String cgstrateParamName = "cgstrate";
	        final Long cgstrate = command.longValueOfParameterNamed(cgstrateParamName);

	        final String igstrateParamName = "igstrate";
	        final Long igstrate = command.longValueOfParameterNamed(igstrateParamName);
	        
	        final String cessrateParamName = "cessrate";
	        final Long cessrate = command.longValueOfParameterNamed(cessrateParamName);
	        

	        return new GstCaluculate(itemcode, itemdesc, sgstrate, cgstrate, igstrate, cessrate);
	    }
	
	public GstCaluculate(final String itemcode,final String itemdesc,final Double sgstrate,
			final Double cgstrate, final Double igstrate, final Double cessrate) {
		
		this.itemcode=itemcode;
		this.itemdesc=itemdesc;
		this.sgstrate=sgstrate;
		this.cgstrate=cgstrate;
		this.igstrate=igstrate;
		this.cessrate=cessrate;
		
	}
	
	
	
	public String getItemcode() {
		return itemcode;
	}

	public String getItemdesc() {
		return itemdesc;
	}

	public Double getSgstrate() {
		return sgstrate;
	}

	public Double getCgstrate() {
		return cgstrate;
	}

	public Double getIgstrate() {
		return igstrate;
	}

	public Double getCessrate() {
		return cessrate;
	}

	public Map<String, Object> update(final JsonCommand command) {
		
		        final Map<String, Object> actualChanges = new LinkedHashMap<>(7);
		        
		        final String itemcode= "itemcode";
		        if (command.isChangeInStringParameterNamed(itemcode, this.itemcode)) {
		            final String newValue = command.stringValueOfParameterNamed(itemcode);
		            actualChanges.put(itemcode, newValue);
		            this.itemcode=StringUtils.defaultIfEmpty(newValue, null);
		        }
		        final String itemdescParamName = "itemdesc";
		        if (command.isChangeInStringParameterNamed(itemdescParamName, this.itemdesc)) {
		            final String newValue = command.stringValueOfParameterNamed(itemdescParamName);
		            actualChanges.put(itemdescParamName, newValue);
		            this.itemdesc= StringUtils.defaultIfEmpty(newValue, null);
		        }
		        final String sgstrateParamName = "sgstrate";
		        if (command.isChangeInDoubleParameterNamed(sgstrateParamName, this.sgstrate)) {
		            final String newValue = command.stringValueOfParameterNamed(sgstrateParamName);
		            actualChanges.put(sgstrateParamName, newValue);
		            this.sgstrate= StringUtils.defaultIfEmpty(newValue, null);
		        }
		        final String contactParamName = "contactName";
		        if (command.isChangeInStringParameterNamed(contactParamName, this.contactName)) {
		            final String newValue = command.stringValueOfParameterNamed(contactParamName);
		            actualChanges.put(contactParamName, newValue);
		            this.contactName= StringUtils.defaultIfEmpty(newValue, null);
		        }
		        final String officePhoneParamName = "officePhone";
		        if (command.isChangeInStringParameterNamed(officePhoneParamName, this.officePhone)) {
		            final String newValue = command.stringValueOfParameterNamed(officePhoneParamName);
		            actualChanges.put(officePhoneParamName, newValue);
		            this.officePhone= StringUtils.defaultIfEmpty(newValue, null);
		        }
		        final String homePhoneParamName = "homePhone";
		        if (command.isChangeInStringParameterNamed(homePhoneParamName, this.homePhone)) {
		            final String newValue = command.stringValueOfParameterNamed(homePhoneParamName);
		            actualChanges.put(homePhoneParamName, newValue);
		            this.homePhone= StringUtils.defaultIfEmpty(newValue, null);
		        }
		        return actualChanges; 
	}

	
}
