package com.gst.organisation.gstr1fileinvoice.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface Gstr1FileB2BInvoiceRepository extends JpaRepository<Gstr1FileB2BInvoice, Long>, JpaSpecificationExecutor<Gstr1FileB2BInvoice> {


}
