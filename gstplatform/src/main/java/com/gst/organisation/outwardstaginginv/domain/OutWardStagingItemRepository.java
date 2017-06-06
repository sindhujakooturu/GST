package com.gst.organisation.outwardstaginginv.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OutWardStagingItemRepository extends JpaRepository<OutWardStagingItem, Long>, JpaSpecificationExecutor<OutWardStagingItem> {


}