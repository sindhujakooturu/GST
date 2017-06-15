package com.gst.organisation.purchaser.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface PurchaserRepository extends JpaRepository<Purchaser, Long>, JpaSpecificationExecutor<Purchaser> {
	
}
