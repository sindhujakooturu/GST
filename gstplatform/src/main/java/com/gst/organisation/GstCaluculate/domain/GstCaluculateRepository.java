package com.gst.organisation.GstCaluculate.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface GstCaluculateRepository extends JpaRepository<GstCaluculate, Long>, JpaSpecificationExecutor<GstCaluculate> {
	
	public static final  String sgstrate = "g.sgst_rate as sgstrate from g_gst_rate g";
	public static final  String cgstrate = "g.cgst_rate as cgstrate from g_gst_rate g";
	public static final  String igstrate="g.igst_rate as igstrate from g_gst_rate g";
	

    /**
     * 
     */
    @Query(sgstrate)
    public Double findBySgstrate(@Param("sgst_rate") Double sgstrate);
    
    @Query(cgstrate)
    public GstCaluculate findByCgstrate(@Param("cgst_rate") Double cgstrate);
    
    @Query(igstrate)
    public GstCaluculate findByIgstrate(@Param("igst_rate") Double igstrate);
    
	
}
