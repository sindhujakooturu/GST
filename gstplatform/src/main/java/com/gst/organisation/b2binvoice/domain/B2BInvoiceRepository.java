package com.gst.organisation.b2binvoice.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface B2BInvoiceRepository extends JpaRepository<B2BInvoice, Long>, JpaSpecificationExecutor<B2BInvoice>{

}
