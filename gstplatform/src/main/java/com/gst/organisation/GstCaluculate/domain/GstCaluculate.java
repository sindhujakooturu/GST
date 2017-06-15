package com.gst.organisation.GstCaluculate.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.domain.AbstractPersistableCustom;

@Entity
@Table(name="g_gst_rate")
public class GstCaluculate extends AbstractPersistableCustom<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="item_code", length = 16)
	private Long itemCode;
	
	@Column(name="item_desc", length = 255)
	private String itemDesc;
	
	@Column(name="sgst_rate", length = 6)
    private BigDecimal sgstRate;
	
	@Column(name="cgst_rate", length = 6)
    private  BigDecimal cgstRate;
	
	@Column(name="igst_rate", length = 6)
    private  BigDecimal igstRate;
	
	@Column(name="cess_rate", length = 6)
    private  BigDecimal cessRate;
	
	
	public GstCaluculate(){
		
	}
	
	 public static GstCaluculate fromJson(final JsonCommand command) {

	        final String itemcodeParamName = "itemCode";
	        final Long itemcode = command.longValueOfParameterNamed(itemcodeParamName);

	        final String itemdescParamName = "itemDesc";
	        final String itemdesc = command.stringValueOfParameterNamed(itemdescParamName);

	        final String sgstrateParamName = "sgstRate";
	        final BigDecimal sgstrate = command.bigDecimalValueOfParameterNamed(sgstrateParamName);

	        final String cgstrateParamName = "cgstRate";
	        final BigDecimal cgstrate = command.bigDecimalValueOfParameterNamed(cgstrateParamName);

	        final String igstrateParamName = "igstRate";
	        final BigDecimal igstrate = command.bigDecimalValueOfParameterNamed(igstrateParamName);
	        
	        final String cessrateParamName = "cessRate";
	        final BigDecimal cessrate = command.bigDecimalValueOfParameterNamed(cessrateParamName);
	        

	        return new GstCaluculate(itemcode, itemdesc, sgstrate, cgstrate, igstrate, cessrate);
	    }
	
	public GstCaluculate(final Long itemCode,final String itemDesc,final BigDecimal sgstRate,
			final BigDecimal cgstRate, final BigDecimal igstRate, final BigDecimal cessRate) {
		
		this.itemCode=itemCode;
		this.itemDesc=itemDesc;
		this.sgstRate=sgstRate;
		this.cgstRate=cgstRate;
		this.igstRate=igstRate;
		this.cessRate=cessRate;
		
	}

	public Long getItemCode() {
		return itemCode;
	}

	public void setItemCode(Long itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public BigDecimal getSgstRate() {
		return sgstRate;
	}

	public void setSgstRate(BigDecimal sgstRate) {
		this.sgstRate = sgstRate;
	}

	public BigDecimal getCgstRate() {
		return cgstRate;
	}

	public void setCgstRate(BigDecimal cgstRate) {
		this.cgstRate = cgstRate;
	}

	public BigDecimal getIgstRate() {
		return igstRate;
	}

	public void setIgstRate(BigDecimal igstRate) {
		this.igstRate = igstRate;
	}

	public BigDecimal getCessRate() {
		return cessRate;
	}

	public void setCessRate(BigDecimal cessRate) {
		this.cessRate = cessRate;
	}

}
