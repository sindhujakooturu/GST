package com.gst.organisation.gstr1fileinvoice.service;

import java.util.Collection;

import com.gst.organisation.gstr1fileinvoice.data.Gstr1FileInvoiceData;

public interface Gstr1FileInvoiceReadPlatformService {
	
	Collection<Gstr1FileInvoiceData> retriveGstr1FileInvoiceData();
	
	Gstr1FileInvoiceData retrieveSingleGstr1Details(Long gstr1InvId);

}
