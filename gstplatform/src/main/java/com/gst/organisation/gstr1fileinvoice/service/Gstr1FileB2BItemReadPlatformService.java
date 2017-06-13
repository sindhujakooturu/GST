package com.gst.organisation.gstr1fileinvoice.service;

import java.util.Collection;

import com.gst.organisation.gstr1fileinvoice.data.Gstr1FileB2BItemData;

public interface Gstr1FileB2BItemReadPlatformService {

	Collection<Gstr1FileB2BItemData> retriveGstr1FileB2BItemData();

	//Collection<Gstr1FileB2BItemData> retriveB2BinvoiceItems(Long invoiceId);

}
