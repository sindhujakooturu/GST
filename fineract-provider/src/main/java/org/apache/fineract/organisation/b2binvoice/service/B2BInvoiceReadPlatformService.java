package org.apache.fineract.organisation.b2binvoice.service;

import java.util.List;

import org.apache.fineract.organisation.b2binvoice.data.B2BInvoiceDetailsData;

public interface B2BInvoiceReadPlatformService {
	
	List<B2BInvoiceDetailsData> retriveB2BInvoiceDetailsData();

}
