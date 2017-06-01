package com.gst.organisation.outwardstaginginv.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OutWardStagingInvRepository extends JpaRepository<OutWardStagingInv, Long>, JpaSpecificationExecutor<OutWardStagingInv> {


}
