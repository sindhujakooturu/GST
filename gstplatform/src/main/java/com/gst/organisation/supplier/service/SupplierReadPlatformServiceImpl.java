package com.gst.organisation.supplier.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.gst.infrastructure.core.service.RoutingDataSource;
import com.gst.infrastructure.security.service.PlatformSecurityContext;
import com.gst.organisation.supplier.data.SupplierData;
import com.gst.organisation.supplier.exception.SupplierNotFoundException;

@Service
public class SupplierReadPlatformServiceImpl implements SupplierReadPlatformService {
	private final JdbcTemplate jdbcTemplate;
    private final PlatformSecurityContext context;
    private final SupplierLookupMapper lookupMapper = new SupplierLookupMapper();
    private final SupplierInHierarchyMapper supplierInHierarchyMapper = new SupplierInHierarchyMapper();
    
    @Autowired
    public SupplierReadPlatformServiceImpl(final PlatformSecurityContext context, final RoutingDataSource dataSource) {
        this.context = context;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    private static final class SupplierLookupMapper implements RowMapper<SupplierData> {

        private final String schemaSql;

        public SupplierLookupMapper() {

            final StringBuilder sqlBuilder = new StringBuilder(100);
            sqlBuilder.append("s.gstin as gstin, s.gstin_comp as gstinComp ");
            sqlBuilder.append("from supplier_t s ");
            this.schemaSql = sqlBuilder.toString();
        }

        public String schema() {
            return this.schemaSql;
        }

		@Override
		public SupplierData mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			final Long id= rs.getLong("id");
            final String gstin = rs.getString("gstin");
            return  SupplierData.lookup(id, gstin);
		}

    }
    
    /*@Override
    public Collection<SupplierData> retrieveAllSupplierForDropdown(final Long id) {
    	

        final Long defaultId = defaultToUsersOfficeIfNull(id);

        final String sql = "select " + this.lookupMapper.schema() + " where s.id = ?%'";

        return this.jdbcTemplate.query(sql, this.lookupMapper, new Object[] { defaultId });
    }*/

   /* private Long defaultToUsersOfficeIfNull(final Long id) {
        Long defaultOfficeId = id;
        if (defaultOfficeId == null) {
            defaultOfficeId = this.context.authenticatedUser().getOffice().getId();
        }
        return defaultOfficeId;
    }*/
    /*@Override
    public SupplierData retrieveSupplier(final Long id) {
    	
      this.context.authenticatedUser().getOffice().getHierarchy();
         

        try {
            final SupplierMapper rm = new SupplierMapper();
            final String sql = "select " + rm.schema();

            return this.jdbcTemplate.queryForObject(sql, rm, new Object[] { id });
        } catch (final EmptyResultDataAccessException e) {
            throw new SupplierNotFoundException(id);
        }
    }*/
    private static final class SupplierInHierarchyMapper implements RowMapper<SupplierData> {

        public String schema(final Long id) {

            final StringBuilder sqlBuilder = new StringBuilder(200);

            sqlBuilder.append("s.id as id, s.gstin as gstin");
            sqlBuilder.append("s.supplier_name as supplierName, s.contact_name as contactName,");
            sqlBuilder.append("s.office_phone as officePhone, s.home_phone as homePhone, s.rmn as rmn, ");
            sqlBuilder.append("s.fax as fax, s.rmail as rmail, s.pan_no as panNo ");
            sqlBuilder.append("s.etin as etin, s.addr_line1 as addrLine1, s.addr_line2 as addrLine2 ");
            sqlBuilder.append("s.city as city, s.state as state, s.country as country,s.pin as pin ");
            sqlBuilder.append("from supplier_t s");
            return sqlBuilder.toString();
        }

        @Override
        public SupplierData mapRow(final ResultSet rs,final int rowNum) throws SQLException {

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

            return new SupplierData(id, gstin, gstinComp, supplierName, contactName, officePhone, homePhone, rmn, fax,
                    rmail, panNo,etin,addrLine1,addrLine2,city,state,country,pin);
        }
    }
    /*@Override
    public Collection<SupplierData> retrieveAllSupplier() {
    	
        final String extraCriteria = getSupplierCriteria(id);
        return retrieveAllSupplier(extraCriteria);
    }*/

    /*private String getSupplierCriteria(Long id){
    	final StringBuffer extraCriteria = new StringBuffer(200);
        if (id != null) {
            extraCriteria.append(" and id = ").append(id).append(" ");
        }
        
        final String hierarchy = this.context.authenticatedUser().getOffice().getHierarchy();

        if (StringUtils.isNotBlank(extraCriteria.toString())) {
            extraCriteria.delete(0, 4);
        }

        return extraCriteria.toString();
	}*/

	/*private Collection<SupplierData> retrieveAllSupplier(final String extraCriteria) {

        final SupplierMapper rm = new SupplierMapper();
        String sql = "select " + rm.schema();
        if (StringUtils.isNotBlank(extraCriteria)) {
            sql += " where " + extraCriteria;
        }
        sql = sql + " order by s.gstin";
        return this.jdbcTemplate.query(sql, rm, new Object[] {});
    }*/
    /*@Override
    public Collection<SupplierData> retrieveAllSupplierAndItsParentHierarchy(final Long id) {

        String sql = "select " + this.supplierInHierarchyMapper.schema(id);
        sql = sql + " order by s.gstin";
        return this.jdbcTemplate.query(sql, this.supplierInHierarchyMapper, new Object[] { id });
    }*/
/*
	@Override
	public Collection<SupplierData> retrieveAllSuppliersById(Long id) {
		// TODO Auto-generated method stub
		return retrieveAllSupplier();
	}*/
	
			
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
