package com.gst.organisation.gstr1fileinvoice.service;

import java.sql.ResultSet;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.gst.infrastructure.core.service.RoutingDataSource;
import com.gst.organisation.gstr1fileinvoice.data.Gstr1FileInvoiceData;

@Service
public class Gstr1FileInvoiceReadPlatformServiceImpl implements Gstr1FileInvoiceReadPlatformService {
	
    private final JdbcTemplate jdbcTemplate;
    
    
    @Autowired
	public Gstr1FileInvoiceReadPlatformServiceImpl(final RoutingDataSource dataSource) {
    	
    	this.jdbcTemplate = new JdbcTemplate(dataSource);
	}



	@Override
	public Collection<Gstr1FileInvoiceData> retriveGstr1FileInvoiceData() {
		final Gstr1FileInvoiceMapper mapper = new Gstr1FileInvoiceMapper();
		String sql = "SELECT DISTINCT " + mapper.schema();


        return this.jdbcTemplate.query(sql, mapper, new Object[] {});
	}
	
	private static final class Gstr1FileInvoiceMapper implements RowMapper<Gstr1FileInvoiceData> {

        public String schema() {
            return " fid.id as id, fid.gstin as gstin, fid.fp as fp, fid.gross_turnover as grossTurnover, "+
            	   " fid.file_no as fileNo, fid.version as version, fid.status as status, fid.assigned_to as assignedTo, "+
            	   " fid.error_code as errorCode, fid.error_descriptor as errorDescriptor, fid.review_comments as reviewComments "+
            	   " FROM g_gstr1_file_invoice_data fid ";
        }

        @Override
        public Gstr1FileInvoiceData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) {
            try{
        	final Long id = rs.getLong("id");
        	final String gstin = rs.getString("gstin");
        	//final Date fp = rs.getDate("fp");
        	final String fp = rs.getString("fp");
			final String grossTurnover = rs.getString("grossTurnover");
			final String fileNo = rs.getString("fileNo");
			final int version = rs.getInt("version") ;
			final int status = rs.getInt("status");
			final String assignedTo = rs.getString("assignedTo");
			final String errorCode = rs.getString("errorCode");
			final String errorDescriptor = rs.getString("errorDescriptor");
			final String reviewComments = rs.getString("reviewComments");
			
			return new Gstr1FileInvoiceData(id, gstin, fp, grossTurnover, fileNo,  
				  version, status, assignedTo, errorCode, errorDescriptor, reviewComments);
            }catch(Exception e){
            	return null;
            }
			
        }

    }

	@Override
	public Gstr1FileInvoiceData retrieveSingleGstr1Details(Long gstr1InvId) {
		
		try {

			final Gstr1FileInvoiceMapper mapper = new Gstr1FileInvoiceMapper();

			final String sql = "select " + mapper.schema() + " where fid.id = ?";

			return jdbcTemplate.queryForObject(sql, mapper, new Object[] { gstr1InvId });
		} catch (EmptyResultDataAccessException accessException) {
			return null;
		}
		
	}


}
