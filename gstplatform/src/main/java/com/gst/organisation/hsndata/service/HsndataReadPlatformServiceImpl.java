package com.gst.organisation.hsndata.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.gst.infrastructure.core.service.RoutingDataSource;
import com.gst.organisation.hsndata.data.HsndataData;

@Service
public class HsndataReadPlatformServiceImpl implements HsndataReadPlatformService{
	
	private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public HsndataReadPlatformServiceImpl(final RoutingDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
   
    

	@Override
	public Collection<HsndataData> retrieveAllHsndata() {
		
		final HsndataMapper rm = new HsndataMapper();
		String sql = "select " + rm.schema();
		return this.jdbcTemplate.query(sql, rm, new Object[] {});
	}

	
    
    public HsndataData retrieveHsndata() {
            
    	final HsndataMapper rm = new HsndataMapper();
        final String sql = "select " + rm.schema() + " where s.hsnCode = ?" ;
        return this.jdbcTemplate.queryForObject(sql, rm, new Object[] {  });
    }
    
	
    private static final class HsndataMapper implements RowMapper<HsndataData> {

        public String schema() {
            return " s.id as id, s.hsn_code as hsnCode, s.description as description from cfg_hsn_data s ";
        }
	
        @Override
        public HsndataData mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        	
        	final Long id=rs.getLong("id");
            final String hsnCode = rs.getString("hsnCode");
            final String description = rs.getString("description");
            
            return new HsndataData(id,hsnCode,description);
        }
    }

}
    