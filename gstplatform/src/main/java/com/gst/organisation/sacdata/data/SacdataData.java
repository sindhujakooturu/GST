package com.gst.organisation.sacdata.data;


/**
 * Immutable data object representing sac data.
 */
public class SacdataData {

    private final Long id;
    private final String sacSeqId;
    private final String serviceName;
    private final String description;
    private final String sacTaxCollection;
    private final String sacOtherReciept;
    private final String sacDeductRefund;
    private final String sacPenalty;
    

    public SacdataData( final Long id ,final String sacSeqId, final String serviceName, final String description,
            final String sacTaxCollection,  final String sacOtherReciept, final String sacDeductRefund, final String sacPenalty) {

    	this.id = id;
    	this.sacSeqId = sacSeqId;
        this.serviceName = serviceName;
        this.description = description;
        this.sacTaxCollection = sacTaxCollection;
        this.sacOtherReciept = sacOtherReciept;
        this.sacDeductRefund = sacDeductRefund;
        this.sacPenalty = sacPenalty;
        
    }


	public Long getId() {
		return id;
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

   
}