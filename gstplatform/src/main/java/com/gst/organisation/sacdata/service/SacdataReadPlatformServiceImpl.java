package com.gst.organisation.sacdata.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import com.gst.infrastructure.core.service.RoutingDataSource;
import com.gst.organisation.sacdata.data.SacdataData;

@Service
public class SacdataReadPlatformServiceImpl implements SacdataReadPlatformService{
	
	private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public SacdataReadPlatformServiceImpl(final RoutingDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
   
    

	@Override
	public Collection<SacdataData> retrieveAllSacdata() {
		
		final SacdataMapper rm = new SacdataMapper();
		String sql = "select " + rm.schema();
		return this.jdbcTemplate.query(sql, rm, new Object[] {});
	}

    
    @Override
    public SacdataData retrieveSacdata(Long id) {
            
    	final SacdataMapper rm = new SacdataMapper();
        final String sql = "select " + rm.schema() + " where s.id = ?" ;
        return this.jdbcTemplate.queryForObject(sql, rm, new Object[] { id });
    }
    
	
    private static final class SacdataMapper implements RowMapper<SacdataData> {

        public String schema() {
            return " s.id as id, s.sac_seq_id as sacSeqId, s.service_name as serviceName, " +
            	   " s.description as description, s.sac_tax_collection as sacTaxCollection, " +
            	   " s.sac_other_reciept as sacOtherReciept, s.sac_deduct_refund as sacDeductRefund, " +
            	   " s.sac_penalty as sacPenalty from g_sac_data s ";
        }
	
        @Override
        public SacdataData mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        	
        	final Long id=rs.getLong("id");
            final String sacSeqId = rs.getString("sacSeqId");
            final String serviceName = rs.getString("serviceName");
            final String description = rs.getString("description");
            final String sacTaxCollection = rs.getString("sacTaxCollection");
            final String sacOtherReciept = rs.getString("sacOtherReciept");
            final String sacDeductRefund = rs.getString("sacDeductRefund");
            final String sacPenalty = rs.getString("sacPenalty");

            return new SacdataData(id,sacSeqId, serviceName, description, sacTaxCollection, sacOtherReciept, sacDeductRefund, sacPenalty);
        }
    }


	

}
    