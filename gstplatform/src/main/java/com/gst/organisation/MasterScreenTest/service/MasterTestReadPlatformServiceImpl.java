package com.gst.organisation.MasterScreenTest.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.gst.infrastructure.core.service.RoutingDataSource;
import com.gst.organisation.MasterScreenTest.data.MasterTestData;

/**
 * @author Trigital
 * 
 */
@Service
public class MasterTestReadPlatformServiceImpl implements MasterTestReadPlatformService {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public MasterTestReadPlatformServiceImpl(final RoutingDataSource dataSource) {
		
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see #retrieveAllOutWardInvData()
	 */
	public List<MasterTestData> retrieveAllTestData() {

		final MasterTestMapper mapper = new MasterTestMapper();

		final String sql = "Select Distinct " + mapper.schema();

		return this.jdbcTemplate.query(sql, mapper, new Object[] {});
	}

	private static final class MasterTestMapper implements
			RowMapper<MasterTestData> {

		public String schema() {
			return " tn.id as id, tn.column1 as column1, tn.column2 as column2, tn.column3 as column3, tn.column4 as column4, tn.column5 as column5,"
					+ " tn.column6 as column6, tn.column7 as column7,tn.column8 as column8 , tn.column9 as column9, tn.column10 as column10, tn.column11 as column11,"
					+ " tn.column12 as column12,tn.status as status, tn.column13 as column13, tn.column14 as column14 "
					+ " from tablename tn";
		}
		

		@Override
		public MasterTestData mapRow(final ResultSet rs, final int rowNum)
				throws SQLException {

			final Long id = rs.getLong("id");
			final String column1 = rs.getString("column1");
			final String column2 = rs.getString("column2");
			final String column3 = rs.getString("column3");
			final String column4 = rs.getString("column4");
			final Date column5 = rs.getDate("column5");
			final BigDecimal column6 = rs.getBigDecimal("column6");
			final String column7 = rs.getString("column7");
			final String column8 = rs.getString("column8");
			final Date column9 = rs.getDate("column9");
			final String column10 = rs.getString("column10");
			final Long column11 = rs.getLong("column11");
			final String column12 = rs.getString("column12");
			final Long status = rs.getLong("status");
			final String column13 = rs.getString("column13");
			final String column14 = rs.getString("column14");
			
			return new MasterTestData(id, column1, column2, column3, column4, column5,
					column6, column7,column8 , column9, column10, column11, column12, 
					status, column13, column14);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 */
	public MasterTestData retrieveSingleTestDetails(final Long id) {

		try {

			final MasterTestMapper mapper = new MasterTestMapper();

			final String sql = "select " + mapper.schema() + " where tn.id = ?";

			return jdbcTemplate.queryForObject(sql, mapper, new Object[] { id });
		} catch (EmptyResultDataAccessException accessException) {
			return null;
		}
	}

}
