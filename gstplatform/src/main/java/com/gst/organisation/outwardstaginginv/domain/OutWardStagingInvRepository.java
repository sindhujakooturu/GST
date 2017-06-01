package com.gst.organisation.outwardstaginginv.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OutWardStagingInvRepository extends JpaRepository<OutWardStagingInv, Long>, JpaSpecificationExecutor<OutWardStagingInv> {

	/*@Query("from OutWardStagingInv charge where charge.chargeCode =:chargeCode")
	OutWardStagingInv findOneByChargeCode(@Param("chargeCode") String chargeCode);
	
	@Query("from OutWardStagingInv charge where charge.billFrequencyCode =:billFrequencyCode")
	List<OutWardStagingInv> findOneByBillFrequency(@Param("billFrequencyCode") String billFrequencyCode);*/

}
