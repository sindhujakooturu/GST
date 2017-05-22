package com.gst.organisation.b2binvoice.service;

import java.util.List;

import com.gst.organisation.b2binvoice.data.B2BInvoiceData;
import com.gst.organisation.b2binvoice.data.B2BInvoiceDetailsData;

public interface B2BInvoiceReadPlatformService {
	
	List<B2BInvoiceData> retriveB2BInvoiceData();
	
	List<B2BInvoiceDetailsData> retriveB2BInvoiceDetailsData(Long b2BInvoiceId);

}
