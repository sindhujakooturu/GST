package com.gst.organisation.supplier.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface SupplierRepository extends JpaRepository<Supplier, Long>, JpaSpecificationExecutor<Supplier> {
	
	/*public final static String FIND_BY_SUPPLIER_QUERY="select s from supplier_t s where s.id = :id";
	
	@Query(FIND_BY_SUPPLIER_QUERY)
	public Supplier findById(@Param("id") Long id);
	@Query("select from supplier_t supplier where supplier.id =:gstin")
	Supplier findById(@Param("gstin") String gstin);*/
}
