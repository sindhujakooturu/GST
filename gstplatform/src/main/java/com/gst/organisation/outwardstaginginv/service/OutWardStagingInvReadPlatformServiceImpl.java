package com.gst.organisation.outwardstaginginv.service;

<<<<<<< HEAD
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
=======
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
>>>>>>> upstream/master
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.gst.infrastructure.core.service.RoutingDataSource;
import com.gst.organisation.outwardstaginginv.data.OutWardStagingInvData;

/**
<<<<<<< HEAD
 * @author hugo
 * 
 */
@Service
public class OutWardStagingInvReadPlatformServiceImpl implements
             OutWardStagingInvReadPlatformService {
=======
 * @author Trigital
 * 
 */
@Service
public class OutWardStagingInvReadPlatformServiceImpl implements OutWardStagingInvReadPlatformService {
>>>>>>> upstream/master

	private final JdbcTemplate jdbcTemplate;

	@Autowired
<<<<<<< HEAD
	public OutWardStagingInvReadPlatformServiceImpl(
			final RoutingDataSource dataSource) {
=======
	public OutWardStagingInvReadPlatformServiceImpl(final RoutingDataSource dataSource) {
		
>>>>>>> upstream/master
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see #retrieveAllOutWardInvData()
	 */
	public List<OutWardStagingInvData> retrieveAllOutWardInvData() {

		final OutWardInvDataMapper mapper = new OutWardInvDataMapper();

		final String sql = "Select " + mapper.schema();

		return this.jdbcTemplate.query(sql, mapper, new Object[] {});
	}

	private static final class OutWardInvDataMapper implements
			RowMapper<OutWardStagingInvData> {

		public String schema() {
<<<<<<< HEAD
			return "id as id, charge_code as chargeCode, charge_description as chargeDescription, charge_type as chargeType,"
					+ "charge_duration as chargeDuration, duration_type as durationType, tax_inclusive as taxInclusive,"
					+ "billfrequency_code as billFrequencyCode from b_charge_codes";
=======
			return " osi.id as id, osi.gstin as gstin, osi.gstin_purchaser as gstinPurchaser, osi.c_name as cName, osi.supplier_inv_no as supplierInvNo, osi.supplier_inv_date as supplierInvDate,"
					+ " osi.supplier_inv_value as supplierIinvValue, osi.supply_state_code as supplyStateCode,osi.order_no as orderNo , osi.order_date as orderDate, osi.etin as etin, osi.invoice_id as invoiceId,"
					+ " osi.receipt_state_code as receiptStateCode,osi.status as status, osi.error_code as errorCode, osi.error_descripter as errorDescripter "
					+ " from m_ow_stg_invoice osi";
>>>>>>> upstream/master
		}

		@Override
		public OutWardStagingInvData mapRow(final ResultSet rs, final int rowNum)
				throws SQLException {

			final Long id = rs.getLong("id");
			final String gstin = rs.getString("gstin");
			final String gstinPurchaser = rs.getString("gstinPurchaser");
			final String cName = rs.getString("cName");
			final String supplierInvNo = rs.getString("supplierInvNo");
<<<<<<< HEAD
			final String supplierInvDate = rs.getString("supplierInvDate");
			final Long supplierInvValue = rs.getLong("supplierInvValue");
			final String supplyStateCode = rs.getString("supplyStateCode");
			final String orderNo = rs.getString("orderNo");
			final String orderDate = rs.getString("orderDate");
			final String etin = rs.getString("etin");
			final Long invoiceId = rs.getLong("invoiceId");
			final String receiptStateCode = rs.getString("receiptStateCode");
			final Integer status = rs.getInt("status");
			final String errorCode = rs.getString("errorCode");
			final String errorDescripter = rs.getString("errorDescripter");
			final Integer modeNo = rs.getInt("modeNo");
			
			 /*Long id, String gstin, String gstinPurchaser,
			 String cName, String supplierInvNo,  String supplierInvDate,
			 Long supplierInvValue,  String supplyStateCode, String orderNo , String orderDate,
			 String etin, Long invoiceId, String receiptStateCode,Integer status, String errorCode, String errorDescripter,
			 Integer modeNo*/

			return new OutWardStagingInvData(id, gstin, gstinPurchaser,
					 cName, supplierInvNo, supplierInvDate,
					 supplierInvValue, supplyStateCode,orderNo , orderDate,
					 etin, invoiceId, receiptStateCode, status, errorCode, errorDescripter,
					  modeNo);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see #getChargeType() like RC ,NRC
	 */
	/*public List<ChargeTypeData> getChargeType() {

		final ChargeTypeDataMapper typeMapper = new ChargeTypeDataMapper();

		final String sql = "select mcv.id as id,mcv.code_value as chargeType from m_code_value mcv,m_code mc "
				+ "where mcv.code_id=mc.id and mc.code_name='Charge Type' order by mcv.id";

		return jdbcTemplate.query(sql, typeMapper);
	}*/

	/*private static final class ChargeTypeDataMapper implements
			RowMapper<ChargeTypeData> {

		public ChargeTypeData mapRow(final ResultSet rs, final int rowNum)
				throws SQLException {

			final Long id = rs.getLong("id");
			final String chargeType = rs.getString("chargeType");

			return new ChargeTypeData(id, chargeType);
		}
	}*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see #getDurationType() like month(s),week(s),day(s)
	 */
	/*public List<DurationTypeData> getDurationType() {

		final DurationTypeDataMapper durationMapper = new DurationTypeDataMapper();

		final String sql = "select mcv.id as id,mcv.code_value as durationType from m_code_value mcv,m_code mc "
				+ "where mcv.code_id=mc.id and mc.code_name='Duration Type' order by mcv.id";

		return jdbcTemplate.query(sql, durationMapper);
	}*/

	/*private static final class DurationTypeDataMapper implements
			RowMapper<DurationTypeData> {

		public DurationTypeData mapRow(final ResultSet rs, final int rowNum)
				throws SQLException {

			final Long id = rs.getLong("id");
			final String durationTypeCode = rs.getString("durationType");
			return new DurationTypeData(id, durationTypeCode);
		}
	}*/

	/*
	 * (non-Javadoc)
	 * 
	 * @see #getBillFrequency() like monthly,weekly,quaterly etc
	 */
	/*public List<BillFrequencyCodeData> getBillFrequency() {

		final BillFrequencyMapper frequencyMapper = new BillFrequencyMapper();

		final String sql = "select mcv.id as id,mcv.code_value as billFrequency from m_code_value mcv,m_code mc "
				+ "where mcv.code_id=mc.id and mc.code_name='Bill Frequency' order by mcv.id";

		return jdbcTemplate.query(sql, frequencyMapper);

	}*/

	/*private static final class BillFrequencyMapper implements
			RowMapper<BillFrequencyCodeData> {

		public BillFrequencyCodeData mapRow(final ResultSet rs, int rowNum)
				throws SQLException {

			final Long id = rs.getLong("id");
			final String billFrequencyCode = rs.getString("billFrequency");

			return new BillFrequencyCodeData(id, billFrequencyCode);
		}
	}*/
=======
			final Date supplierInvDate = rs.getDate("supplierInvDate");
			final Long supplierInvValue = rs.getLong("supplierInvValue");
			final String supplyStateCode = rs.getString("supplyStateCode");
			final String orderNo = rs.getString("orderNo");
			final Date orderDate = rs.getDate("orderDate");
			final String etin = rs.getString("etin");
			final Long invoiceId = rs.getLong("invoiceId");
			final String receiptStateCode = rs.getString("receiptStateCode");
			final Long status = rs.getLong("status");
			final String errorCode = rs.getString("errorCode");
			final String errorDescripter = rs.getString("errorDescripter");
			

			return new OutWardStagingInvData(id, gstin, gstinPurchaser, cName, supplierInvNo, supplierInvDate,
					               supplierInvValue, supplyStateCode,orderNo , orderDate, etin, invoiceId, receiptStateCode, 
					               status, errorCode, errorDescripter);
		}
	}
>>>>>>> upstream/master

	/*
	 * (non-Javadoc)
	 * 
<<<<<<< HEAD
	 * @see #retrieveSingleChargeCodeDetails(java.lang.Long)
	 */
	public OutWardStagingInvData retrieveSingleChargeCodeDetails(
			final Long chargeCodeId) {

		try {

			final ChargeCodeMapper mapper = new ChargeCodeMapper();

			final String sql = "select " + mapper.schema() + " where id = ?";

			return jdbcTemplate.queryForObject(sql, mapper,
					new Object[] { chargeCodeId });
		} catch (EmptyResultDataAccessException accessException) {
			return null;
		}
	}

	private static final class ChargeCodeRecurringMapper implements RowMapper<ChargeCodeData> {

		public String schema() {

			return "  cc.id AS id,c.contract_duration AS contractDuration,c.contract_type AS contractType,cc.duration_type AS chargeType," +
					" cc.charge_duration AS chargeDuration,p.price AS price FROM b_charge_codes cc, b_plan_pricing p " +
					" left join b_contract_period c on c.contract_period = p.duration WHERE  p.id = ? AND p.charge_code = cc.charge_code";
  
		}

		@Override
		public OutWardStagingInvData mapRow(final ResultSet rs, final int rowNum)
				throws SQLException {

			final Long id = rs.getLong("id");
			final String contractType = rs.getString("contractType");
			final BigDecimal price = rs.getBigDecimal("price");
			final String chargeType = rs.getString("chargeType");
			final Integer chargeDuration = rs.getInt("chargeDuration");
			final Integer contractDuration = rs.getInt("contractDuration");
			OutWardStagingInvData chargeCodeData = new OutWardStagingInvData(id,contractType,contractDuration,chargeType,chargeDuration,price);
			chargeCodeData.setPrice(price);
			return chargeCodeData;	
		}
	}
	
	@Override
	public OutWardStagingInvData retrieveChargeCodeForRecurring(Long priceId) {
		try {

			final ChargeCodeRecurringMapper mapper = new ChargeCodeRecurringMapper();
			final String sql = "select " + mapper.schema();

			System.out.println(sql);
			
			return jdbcTemplate.queryForObject(sql, mapper, new Object[] { priceId});


=======
	 * @see #retrieveSingleOutWardStagingInvDetails(java.lang.Long)
	 */
	public OutWardStagingInvData retrieveSingleOutWardStagingInvDetails(final Long outWardInvId) {

		try {

			final OutWardInvDataMapper mapper = new OutWardInvDataMapper();

			final String sql = "select " + mapper.schema() + " where osi.id = ?";

			return jdbcTemplate.queryForObject(sql, mapper, new Object[] { outWardInvId });
>>>>>>> upstream/master
		} catch (EmptyResultDataAccessException accessException) {
			return null;
		}
	}

}
