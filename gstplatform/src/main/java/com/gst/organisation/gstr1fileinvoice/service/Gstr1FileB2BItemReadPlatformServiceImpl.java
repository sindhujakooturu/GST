package com.gst.organisation.gstr1fileinvoice.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.gst.infrastructure.core.service.RoutingDataSource;
import com.gst.organisation.gstr1fileinvoice.data.Gstr1FileB2BItemData;


@Service
public class Gstr1FileB2BItemReadPlatformServiceImpl implements Gstr1FileB2BItemReadPlatformService {

	
	private final JdbcTemplate jdbcTemplate;
	
	 @Autowired
		public Gstr1FileB2BItemReadPlatformServiceImpl(final RoutingDataSource dataSource) {
	    	
	    	this.jdbcTemplate = new JdbcTemplate(dataSource);
		}



	@Override
	public Collection<Gstr1FileB2BItemData> retriveGstr1FileB2BItemData() {

		final Gstr1FileB2BInvoiceMapper mapper = new Gstr1FileB2BInvoiceMapper();
		String sql = "SELECT DISTINCT " + mapper.schema();
		//if(null != fileNo)sql = sql + " where fbi.file_no = '"+fileNo+"' ";


        return this.jdbcTemplate.query(sql, mapper, new Object[] {});
	
	}
	
	private static final class Gstr1FileB2BInvoiceMapper implements RowMapper<Gstr1FileB2BItemData> {

        public String schema() {
            return " fbt.id as id, fbt.invoice_id as invoiceId, fbt.file_no as fileNo, fbt.item_type as itemType, "+
            	   " fbt.item_code as itemCode, fbt.tax_value as taxValue, fbt.igst_rate as igstRate, "+
                   " fbt.igst_amount as igstAmount, fbt.cgst_rate as cgstRate, fbt.cgst_amount as cgstAmount, fbt.sgst_rate as sgstRate, "+
                   " fbt.sgst_amount as sgstAmount, fbt.cessRate as cessRate, fbt.cess_amount as cessAmount, "+
                   " fbt.status as status, fbt.error_code as errorCode, "+
                   " fbt.error_descriptor as errorDescriptor FROM g_gstr1_file_b2b_item fbt ";
        }

        @Override
        public Gstr1FileB2BItemData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {
            
        	final Long id = rs.getLong("id");
        	final Long invoiceId = rs.getLong("invoiceId"); 
        	final String fileNo = rs.getString("fileNo");
        	final String itemType = rs.getString("itemType");
        	final String itemCode = rs.getString("itemCode");
        	final BigDecimal taxValue = rs.getBigDecimal("taxValue");
        	final BigDecimal igstRate = rs.getBigDecimal("igstRate");
        	final BigDecimal igstAmount = rs.getBigDecimal("igstAmount");
        	final BigDecimal cgstRate = rs.getBigDecimal("cgstRate");
        	final BigDecimal cgstAmount = rs.getBigDecimal("cgstAmount");
        	final BigDecimal sgstRate = rs.getBigDecimal("sgstRate");
        	final BigDecimal sgstAmount = rs.getBigDecimal("sgstAmount"); 
        	final BigDecimal cessRate = rs.getBigDecimal("cessRate");
        	final BigDecimal cessAmount = rs.getBigDecimal("cessAmount");
        	final int status = rs.getInt("status");
        	final String errorCode = rs.getString("errorCode");
        	final String errorDescriptor = rs.getString("errorDescriptor");
        	
        	return new Gstr1FileB2BItemData(id, invoiceId, fileNo, itemType, itemCode, taxValue, 
        			igstRate, igstAmount, cgstRate, cgstAmount, sgstRate, sgstAmount, cessRate, 
        			cessAmount, status, errorCode, errorDescriptor);
            		
        }

    }

	/*@Override
	public Collection<Gstr1FileB2BItemData> retriveB2BinvoiceItems(final Long invoiceId) {

		final Gstr1FileB2BInvoiceItemMapper mapper = new Gstr1FileB2BInvoiceItemMapper();
		String sql = "SELECT DISTINCT " + mapper.schema()+" where fbid.invoice_id = '"+invoiceId+"' ";


        return this.jdbcTemplate.query(sql, mapper, new Object[] {});
	
	}
	
	private static final class Gstr1FileB2BInvoiceItemMapper implements RowMapper<Gstr1FileB2BItemData> {

        public String schema() {
            return " fbid.id as id, fbid.invoice_id as invoiceId, fbid.file_no as fileNo, fbid.item_type as itemType, "+
            	   " fbid.item_code as itemCode, fbid.tax_value as taxValue, fbid.igst_rate as igstRate, fbid.igst_amount as igstAmount, "+
            	   " fbid.cgst_rate as cgstRate, fbid.cgst_amount as cgstAmount, fbid.sgst_rate as sgstRate, fbid.sgst_amount as sgstAmount, "+
            	   " fbid.cess_rate as cessRate, fbid.cess_amount as cessAmount, fbid.status as status, fbid.error_code as errorCode, "+
            	   " fbid.error_descriptor as errorDescriptor FROM g_gstr1_file_b2b_items fbid ";
        }

        @Override
        public Gstr1FileB2BItemData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {
            
        	final Long id = rs.getLong("id");
        	final Long invoiceId = rs.getLong("invoiceId");
        	final String fileNo =  rs.getString("fileNo");
        	final String itemType =  rs.getString("itemType");
        	final String itemCode =  rs.getString("itemCode");
        	final Double taxValue = rs.getDouble("taxValue");
        	final Double igstRate = rs.getDouble("igstRate");
        	final Double igstAmount = rs.getDouble("igstAmount");
        	final Double cgstRate = rs.getDouble("cgstRate");
        	final Double cgstAmount = rs.getDouble("cgstAmount");
        	final Double sgstRate = rs.getDouble("sgstRate");
        	final Double sgstAmount = rs.getDouble("sgstAmount");
        	final Double cessRate = rs.getDouble("cessRate");
        	final Double cessAmount = rs.getDouble("cessAmount");
			final int status = rs.getInt("status");
        	final String errorCode = rs.getString("errorCode");
        	final String errorDescriptor = rs.getString("errorDescriptor");
			
			return new Gstr1FileB2BItemData(id, invoiceId, fileNo, itemType, itemCode, taxValue, igstRate, igstAmount, 
					cgstRate, cgstAmount, sgstRate, sgstAmount, cessRate, cessAmount, status, errorCode, errorDescriptor);
            		
        }

    }*/

}
