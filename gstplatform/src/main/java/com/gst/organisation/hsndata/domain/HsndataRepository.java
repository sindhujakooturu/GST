package com.gst.organisation.hsndata.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;



public interface HsndataRepository extends JpaRepository<Hsndata, Long>, JpaSpecificationExecutor<Hsndata> {
	
	
	
	
}
