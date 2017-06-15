package com.gst.organisation.company.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.gst.infrastructure.core.service.RoutingDataSource;
import com.gst.organisation.company.data.CompanyData;

/**
 * @author Trigital
 * 
 */
@Service
public class CompanyReadPlatformServiceImpl implements CompanyReadPlatformService {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public CompanyReadPlatformServiceImpl(final RoutingDataSource dataSource) {
		
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see #retrieveAllOutWardInvData()
	 */
	public List<CompanyData> retrieveAllCompanyData() {

		final CompanyDataMapper mapper = new CompanyDataMapper();

		final String sql = "Select " + mapper.schema();

		return this.jdbcTemplate.query(sql, mapper, new Object[] {});
	}

	private static final class CompanyDataMapper implements
			RowMapper<CompanyData> {

		public String schema() {
			return " c.id as id, c.gstin as gstin, c.company_name as companyName, c.contact_name as contactName, c.office_phone as officePhone, c.home_phone as homePhone, c.mobile as mobile,"
					+ " c.fax as fax, c.email as email,c.gstn_reg_no as gstnRegNo , c.pan_no as panNo, c.address_line1 as addressLine1, c.address_line2 as addressLine2,"
					+ " c.city as city,c.state as state, c.country as country, c.pin as pin,c.office_id as officeId "
					+ " from company_t c";
		}

		@Override
		public CompanyData mapRow(final ResultSet rs, final int rowNum)
				throws SQLException {

			final Long id = rs.getLong("id");
			final String gstin = rs.getString("gstin");
			final String companyName = rs.getString("companyName");
			final String contactName = rs.getString("contactName");
			final String officePhone = rs.getString("officePhone");
			final String homePhone = rs.getString("homePhone");
			final String mobile = rs.getString("mobile");
			final String fax = rs.getString("fax");
			final String email = rs.getString("email");
			final String gstnRegNo = rs.getString("gstnRegNo");
			final String panNo = rs.getString("panNo");
			final String addressLine1 = rs.getString("addressLine1");
			final String addressLine2 = rs.getString("addressLine2");
			final String city = rs.getString("city");
			final String state = rs.getString("state");
			final String country = rs.getString("country");
			final String pin = rs.getString("pin");
			final Long officeId = rs.getLong("officeId");
			

			return new CompanyData(id, gstin, companyName, contactName, officePhone, homePhone, mobile,
					fax, email,gstnRegNo , panNo, addressLine1, addressLine2, city, 
					state, country, pin,officeId);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	public CompanyData retrieveSingleCompanyDetails(final Long companyId) {

		try {

			final CompanyDataMapper mapper = new CompanyDataMapper();

			final String sql = "select " + mapper.schema() + " where c.id = ?";

			return jdbcTemplate.queryForObject(sql, mapper, new Object[] { companyId });
		} catch (EmptyResultDataAccessException accessException) {
			return null;
		}
	}

	@Override
	public List<CompanyData> retrieveAllCompanyDetailsByUser(Long companyId) {
		
		try {

			final CompanyDataMapper mapper = new CompanyDataMapper();

			final String sql = "select " + mapper.schema() + " join m_office o on o.id = c.office_id where c.office_id = ? ";

			return jdbcTemplate.query(sql, mapper, new Object[] { companyId });
		} catch (EmptyResultDataAccessException accessException) {
			return null;
		}
	}

}
