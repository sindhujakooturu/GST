package com.gst.organisation.sacdata.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;



public interface SacdataRepository extends JpaRepository<Sacdata, Long>, JpaSpecificationExecutor<Sacdata> {
	
	
	}
