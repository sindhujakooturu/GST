package com.gst.organisation.supplier.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.gst.infrastructure.core.service.RoutingDataSource;
import com.gst.infrastructure.security.service.PlatformSecurityContext;
import com.gst.organisation.supplier.data.SupplierData;

@Service
public class SupplierReadPlatformServiceImpl implements SupplierReadPlatformService {
	private final JdbcTemplate jdbcTemplate;
    private final PlatformSecurityContext context;
    
    @Autowired
    public SupplierReadPlatformServiceImpl(final PlatformSecurityContext context, final RoutingDataSource dataSource) {
        this.context = context;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	@Override
	public List<SupplierData> retrieveAllSupplier() {
		       
		 final SupplierMapper mapper = new SupplierMapper();
		 String sql = "SELECT " + mapper.schema();

		 return this.jdbcTemplate.query(sql, mapper, new Object[] {});
	 
	 }
	
	private static final class SupplierMapper implements RowMapper<SupplierData> {

        public String schema() {
            return "s.id as id, s.gstin as gstin,s.gstin_comp as gstinComp, s.supplier_name as supplierName, s.contact_name as contactName, s.office_phone as officePhone,"
                    + " s.home_phone as homePhone, s.rmn as rmn, s.fax as fax, s.rmail as rmail,"
            		+ " s.pan_no as panNo, s.etin as etin,s.addr_line1 as addrLine1,s.addr_line2 as addrLine2,s.city as city,s.state as state,s.country as country,s.pin as pin from supplier_t s ";
        }

        @Override
        public SupplierData mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        	
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

            return new SupplierData(id,gstin, gstinComp, supplierName, contactName, officePhone, homePhone, rmn, fax, rmail,
                    panNo, etin,addrLine1,addrLine2,city,state,country,pin);
        }
    }
}
