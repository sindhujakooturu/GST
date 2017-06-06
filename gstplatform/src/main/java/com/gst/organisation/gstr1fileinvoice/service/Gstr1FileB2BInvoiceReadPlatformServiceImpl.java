package com.gst.organisation.gstr1fileinvoice.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.gst.infrastructure.core.service.RoutingDataSource;
import com.gst.organisation.gstr1fileinvoice.data.Gstr1FileB2BInvoiceData;
import com.gst.organisation.gstr1fileinvoice.data.Gstr1FileB2BItemData;


@Service
public class Gstr1FileB2BInvoiceReadPlatformServiceImpl implements Gstr1FileB2BInvoiceReadPlatformService {

	
	private final JdbcTemplate jdbcTemplate;
	
	 @Autowired
		public Gstr1FileB2BInvoiceReadPlatformServiceImpl(final RoutingDataSource dataSource) {
	    	
	    	this.jdbcTemplate = new JdbcTemplate(dataSource);
		}



	@Override
	public Collection<Gstr1FileB2BInvoiceData> retriveGstr1FileB2BInvoiceData(final String fileNo) {

		final Gstr1FileB2BInvoiceMapper mapper = new Gstr1FileB2BInvoiceMapper();
		String sql = "SELECT DISTINCT " + mapper.schema()+" where fbi.file_no = '"+fileNo+"' ";


        return this.jdbcTemplate.query(sql, mapper, new Object[] {});
	
	}
	
	private static final class Gstr1FileB2BInvoiceMapper implements RowMapper<Gstr1FileB2BInvoiceData> {

        public String schema() {
            return " fbi.id as id, fbi.gstin as gstin, fbi.fp as fp, fbi.gross_turnover as grossTurnover, fbi.file_no as fileNo, "+
            	   " fbi.supplier_inv_no as supplierInvNo, fbi.supplier_inv_date as supplierInvDate, fbi.supplier_inv_value as supplierInvValue, "+
                   " fbi.supply_place as supplyPlace, fbi.order_no as orderNo, fbi.order_date as orderdate, fbi.etin as etin, "+
                   " fbi.invoice_id as invoiceId, fbi.flag as flag, fbi.chk_sum as chkSum, fbi.is_reverse as isReverse, "+
                   " fbi.is_provisional as isProvisional, fbi.record_type as recordType, fbi.status as status, fbi.error_code as errorCode, "+
                   " fbi.error_descriptor as errorDescriptor FROM g_gstr1_file_b2b_invoice fbi ";
        }

        @Override
        public Gstr1FileB2BInvoiceData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {
            
        	final Long id = rs.getLong("id");
        	final String gstin = rs.getString("gstin"); 
        	final Date fp = rs.getDate("fp");
        	final String grossTurnover = rs.getString("grossTurnover");
        	final String fileNo = rs.getString("fileNo");
        	final String supplierInvNo = rs.getString("supplierInvNo");
        	final String supplierInvDate = rs.getString("supplierInvDate");
        	final Double supplierInvValue = rs.getDouble("supplierInvValue");
        	final String supplyPlace = rs.getString("supplyPlace");
        	final String orderNo = rs.getString("orderNo");
        	final Date orderDate = rs.getDate("orderDate");
        	final String etin = rs.getString("etin");
        	final Long invoiceId = rs.getLong("invoiceId"); 
        	final String flag = rs.getString("flag");
        	final String checkSum = rs.getString("checkSum");
        	final int isReverse = rs.getInt("isReverse");
        	final int isProvisional = rs.getInt("isProvisional");
        	final int recordType = rs.getInt("recordType");
        	final String status = rs.getString("status");
        	final String errorCode = rs.getString("errorCode");
        	final String errorDescriptor = rs.getString("errorDescriptor");
			
        	return new Gstr1FileB2BInvoiceData(id, gstin, fp, grossTurnover, fileNo, supplierInvNo, supplierInvDate, 
        			supplierInvValue, supplyPlace, orderNo, orderDate, etin, invoiceId, flag, 
        			checkSum, isReverse, isProvisional, recordType, status, errorCode, errorDescriptor);
            		
        }

    }

	@Override
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

    }

}
