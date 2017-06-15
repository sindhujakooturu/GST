package com.gst.organisation.gstr1fileinvoice.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface Gstr1FileInvoiceRepository extends JpaRepository<Gstr1FileInvoice, Long>, JpaSpecificationExecutor<Gstr1FileInvoice> {


}
