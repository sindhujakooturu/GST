package com.gst.organisation.GstCaluculate.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface GstCaluculateRepository extends JpaRepository<GstCaluculate, Long>{

    @Query("Select g from GstCaluculate g where g.itemCode = :itemCode")
	GstCaluculate findOneByItemCode(@Param("itemCode") Long itemCode);
    
	
}
