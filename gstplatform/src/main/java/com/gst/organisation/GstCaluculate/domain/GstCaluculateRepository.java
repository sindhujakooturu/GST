package com.gst.organisation.GstCaluculate.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface GstCaluculateRepository extends JpaRepository<GstCaluculate, Long>{
	
	/*public static final  String sgstrate = "select from g_gst_rate g where g.sgst_rate=:sgst_rate";
	public static final  String cgstrate = "select from g_gst_rate g where g.cgst_rate=:cgst_rate";
	public static final  String igstrate="select from g_gst_rate g where g.igst_rate=:igst_rate";*/
	
	/*double sgstrate1 = Double.parseDouble(sgstrate);7780455799
	double cgstrate1 = Double.parseDouble(cgstrate);
	double igstrate1 = Double.parseDouble(igstrate);*/
    /**
     * 
     */
	
    /*@Query("Select g from GstCaluculate g where g.sgstrate = :sgstrate")
    public BigDecimal findBySgstrate(@Param("sgstrate") BigDecimal sgstrate);
	
    @Query("Select g from GstCaluculate g where g.cgstrate = :cgstrate")
    public BigDecimal findByCgstrate(@Param("cgstrate") BigDecimal cgstrate);
	
    @Query("Select g from GstCaluculate g where g.igstrate = :igstrate")
    public BigDecimal findByIgstrate(@Param("igstrate") BigDecimal igstrate);*/
    
	/*@Query("from Address address where address.clientId=:clientId and address.addressNo =:oldPropertyCode and deleted='n'")
	Address findOneByClientIdAndPropertyCode(@Param("clientId") Long clientId,@Param("oldPropertyCode") String oldPropertyCode);*/



    @Query("Select g from GstCaluculate g where g.itemCode = :itemCode")
	GstCaluculate findOneByItemCode(@Param("itemCode") Long itemCode);
    
	
}
