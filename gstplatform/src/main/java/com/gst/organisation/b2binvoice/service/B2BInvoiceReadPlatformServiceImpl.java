package com.gst.organisation.b2binvoice.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.gst.infrastructure.core.service.RoutingDataSource;
import com.gst.infrastructure.security.service.PlatformSecurityContext;
import com.gst.organisation.b2binvoice.data.B2BInvoiceData;
import com.gst.organisation.b2binvoice.data.B2BInvoiceDetailsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class B2BInvoiceReadPlatformServiceImpl implements B2BInvoiceReadPlatformService {

	private final PlatformSecurityContext context;
    private final JdbcTemplate jdbcTemplate;
    
    
    @Autowired
	public B2BInvoiceReadPlatformServiceImpl(final PlatformSecurityContext context, final RoutingDataSource dataSource) {
		
		this.context = context;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}



	@Override
	public List<B2BInvoiceData> retriveB2BInvoiceData() {
       

        final B2BInvoiceDataMapper mapper = new B2BInvoiceDataMapper();
        String sql = "SELECT DISTINCT " + mapper.schema();


        return this.jdbcTemplate.query(sql, mapper, new Object[] {});
    }

	private static final class B2BInvoiceDataMapper implements RowMapper<B2BInvoiceData> {

        public String schema() {
            return " ind.id AS id, ind.GSTIN AS gstin, ind.ctin AS ctin, ind.action_flag AS actionFlag, ind.SUPPLIER_INV_NO AS supplierInvNo, " +
            	   " ind.SUPPLIER_INV_DATE AS supplierInvDate, ind.SUPPLIER_INV_VALUE AS supplierInvValue, ind.SUPPLY_PLACE AS supplyPlace, ind.IS_REVERSE AS isReverse, " +
                   " ind.IS_PROV_ASSESSMENT AS isProvAssessment, ind.ORDER_NO AS orderNo, ind.ORDER_DATE AS orderdate, ind.ETIN AS etin, ind.INVOICE_ID AS invoiceId " +
            	   " FROM g_b2b_inv_details ind where ind.status = 0 ";
        }

        @Override
        public B2BInvoiceData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {
            
        	final Long id = rs.getLong("id");
        	final String gstin = rs.getString("gstin");
        	final String ctin = rs.getString("ctin");
        	final String actionFlag = rs.getString("actionFlag");
        	final String supplierInvNo = rs.getString("supplierInvNo");
        	final String supplierInvDate = rs.getString("supplierInvDate");
        	final Double supplierInvValue = rs.getDouble("supplierInvValue");
        	final String supplyPlace = rs.getString("supplyPlace");
        	final Boolean isReverse = rs.getBoolean("isReverse");
        	final Boolean isProvAssessment = rs.getBoolean("isProvAssessment");
        	final String orderNo = rs.getString("orderNo");
        	final String orderdate = rs.getString("orderdate");
        	final String etin = rs.getString("etin");
        	final Long invoiceId = rs.getLong("invoiceId");

            return new B2BInvoiceData(id, gstin, ctin, actionFlag, supplierInvNo, 
            		supplierInvDate, supplierInvValue, supplyPlace, isReverse, isProvAssessment, 
            		orderNo, orderdate, etin, invoiceId);
        }

    }

	@Override
	public List<B2BInvoiceDetailsData> retriveB2BInvoiceDetailsData(Long b2bInvoiceId) {
       

        final B2BInvoiceDetailsDataMapper mapper = new B2BInvoiceDetailsDataMapper();
        final String sql = "SELECT DISTINCT " + mapper.schema() +" WHERE imd.b2b_inv_id = ?";


        return this.jdbcTemplate.query(sql, mapper, new Object[] {b2bInvoiceId});
    }
	
	
	private static final class B2BInvoiceDetailsDataMapper implements RowMapper<B2BInvoiceDetailsData> {

        public String schema() {
            return " imd.id AS id, imd.b2b_inv_id AS b2bInvId, imd.item_type AS itemType, imd.item_code AS itemCode, " +
            	   " imd.tax_value AS taxValue, imd.igst_rate AS igstRate, imd.igst_amount AS igstAmount, imd.cgst_rate AS cgstRate, " +
            	   " imd.cgst_amount AS cgstAmount, imd.sgst_rate AS sgstRate, imd.sgst_amount AS sgstAmount, " +
            	   " imd.cess_rate AS cessRate, imd.cess_amount AS cessAmount " +
            	   " FROM g_b2b_item_details imd JOIN g_b2b_inv_details ind ON ind.id = imd.b2b_inv_id ";
        }

        @Override
        public B2BInvoiceDetailsData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {
            
        	final Long id = rs.getLong("id");

            final Long b2bInvId = rs.getLong("b2bInvId");
            final String itemType = rs.getString("itemType");
            final String itemCode = rs.getString("itemCode");
            final Double taxValue = rs.getDouble("taxValue");
            final Double igstRate = rs.getDouble("igstRate");
            final Double igstAmount = rs.getDouble("igstAmount");
            final Double cgstRate = rs.getDouble("cgstRate");
            final Double cgstAmount = rs.getDouble("cgstAmount");
            final Double sgstRate = rs.getDouble("sgstRate");
            final Double sgstAmount = rs.getDouble("sgstAmount");
            final Double cessRate = rs.getDouble("cessRate");
            final Double cessAmount = rs.getDouble("cessAmount");
            
           
			return new B2BInvoiceDetailsData(id, b2bInvId, itemType, itemCode, taxValue, igstRate, igstAmount, cgstRate, 
					cgstAmount, sgstRate, sgstAmount, cessRate, cessAmount);
        }

    }
	
}
