package com.gst.organisation.outwardstaginginv.service;

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
import com.gst.organisation.outwardstaginginv.data.OutWardStagingInvData;

/**
 * @author Trigital
 * 
 */
@Service
public class OutWardStagingInvReadPlatformServiceImpl implements OutWardStagingInvReadPlatformService {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public OutWardStagingInvReadPlatformServiceImpl(final RoutingDataSource dataSource) {
		
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
			return " osi.id as id, osi.gstin as gstin, osi.gstin_purchaser as gstinPurchaser, osi.c_name as cName, osi.supplier_inv_no as supplierInvNo, osi.supplier_inv_date as supplierInvDate,"
					+ " osi.supplier_inv_value as supplierIinvValue, osi.supply_state_code as supplyStateCode,osi.order_no as orderNo , osi.order_date as orderDate, osi.etin as etin, osi.invoice_id as invoiceId,"
					+ " osi.receipt_state_code as receiptStateCode,osi.status as status, osi.error_code as errorCode, osi.error_descripter as errorDescripter "
					+ " from m_ow_stg_invoice osi";
		}

		@Override
		public OutWardStagingInvData mapRow(final ResultSet rs, final int rowNum)
				throws SQLException {

			final Long id = rs.getLong("id");
			final String gstin = rs.getString("gstin");
			final String gstinPurchaser = rs.getString("gstinPurchaser");
			final String cName = rs.getString("cName");
			final String supplierInvNo = rs.getString("supplierInvNo");
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

	/*
	 * (non-Javadoc)
	 * 
	 */
	public OutWardStagingInvData retrieveSingleOutWardStagingInvDetails(final Long outWardInvId) {

		try {

			final OutWardInvDataMapper mapper = new OutWardInvDataMapper();

			final String sql = "select " + mapper.schema() + " where osi.id = ?";

			return jdbcTemplate.queryForObject(sql, mapper, new Object[] { outWardInvId });
		} catch (EmptyResultDataAccessException accessException) {
			return null;
		}
	}

}
