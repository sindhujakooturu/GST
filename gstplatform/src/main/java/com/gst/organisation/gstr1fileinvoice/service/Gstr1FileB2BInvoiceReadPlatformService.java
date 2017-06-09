package com.gst.organisation.gstr1fileinvoice.service;

import java.util.Collection;

import com.gst.organisation.gstr1fileinvoice.data.Gstr1FileB2BInvoiceData;
import com.gst.organisation.gstr1fileinvoice.data.Gstr1FileB2BItemData;

public interface Gstr1FileB2BInvoiceReadPlatformService {

	Collection<Gstr1FileB2BInvoiceData> retriveGstr1FileB2BInvoiceData(String fileNo);

	Collection<Gstr1FileB2BItemData> retriveB2BinvoiceItems(Long invoiceId);

}
