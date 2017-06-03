package com.gst.organisation.purchaser.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.gst.infrastructure.core.service.RoutingDataSource;
import com.gst.infrastructure.security.service.PlatformSecurityContext;
import com.gst.organisation.purchaser.data.PurchaserData;

@Service
public class PurchaserReadPlatformServiceImpl implements PurchaserReadPlatformService {
	private final JdbcTemplate jdbcTemplate;
    private final PlatformSecurityContext context;
    
    @Autowired
    public PurchaserReadPlatformServiceImpl(final PlatformSecurityContext context, final RoutingDataSource dataSource) {
        this.context = context;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	@Override
	public List<PurchaserData> retrieveAllPurchaser() {
		       
	 final PurchaserMapper mapper = new PurchaserMapper();
	 String sql = "SELECT " + mapper.schema();

	 return this.jdbcTemplate.query(sql, mapper, new Object[] {});
	 
	 }
	
	private static final class PurchaserMapper implements RowMapper<PurchaserData> {

        public String schema() {
            return "p.id as id, p.gstin as gstin,p.gstin_comp as gstinComp, p.supplier_name as supplierName, p.contact_name as contactName, p.office_phone as officePhone,"
                    + " p.home_phone as homePhone, p.rmn as rmn, p.fax as fax, p.rmail as rmail,"
            		+ " p.pan_no as panNo, p.etin as etin,p.addr_line1 as addrLine1,p.addr_line2 as addrLine2,p.city as city,p.state as state,p.country as country,p.pin as pin from purchaser_t p ";
        }

        @Override
        public PurchaserData mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        	
        	final Long id = rs.getLong("id");
            final String gstin = rs.getString("gstin");
            final String gstinComp = rs.getString("gstinComp");
            final String supplierName = rs.getString("supplierName");
            final String contactName = rs.getString("contactName");
            final String officePhone = rs.getString("officePhone");
            final String homePhone = rs.getString("homePhone");
            final String rmn = rs.getString("rmn");
            final String fax = rs.getString("fax");
            final String rmail = rs.getString("rmail");
            final String panNo = rs.getString("panNo");
            final String etin = rs.getString("etin");
            final String addrLine1 = rs.getString("addrLine1");
            final String addrLine2 = rs.getString("addrLine2");
            final String city = rs.getString("city");
            final String state = rs.getString("state");
            final String country = rs.getString("country");
            final String pin = rs.getString("pin");

            return new PurchaserData(id,gstin, gstinComp, supplierName, contactName, officePhone, homePhone, rmn, fax, rmail,
                    panNo, etin,addrLine1,addrLine2,city,state,country,pin);
        }
    }
}
