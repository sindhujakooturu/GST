package com.gst.organisation.MasterScreenTest.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MasterChildTestRepository extends JpaRepository<MasterChildTest, Long>, JpaSpecificationExecutor<MasterChildTest> {


}
