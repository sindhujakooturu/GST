package com.gst.organisation.gstr1fileinvoice.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface Gstr1FileB2BItemRepository extends JpaRepository<Gstr1FileB2BItem, Long>, JpaSpecificationExecutor<Gstr1FileB2BItem> {


}
