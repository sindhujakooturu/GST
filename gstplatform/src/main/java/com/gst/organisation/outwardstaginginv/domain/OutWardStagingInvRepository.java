package com.gst.organisation.outwardstaginginv.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface OutWardStagingInvRepository extends JpaRepository<OutWardStagingInv, Long>, JpaSpecificationExecutor<OutWardStagingInv> {
	
	
	@Query("select osi from OutWardStagingInv osi where osi.status = '0' ")
    public List<OutWardStagingInv> findAllOutWardStagingInv();

}
