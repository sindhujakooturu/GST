package com.gst.organisation.outwardstaginginv.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import com.gst.infrastructure.core.service.RoutingDataSource;
import com.gst.organisation.outwardstaginginv.data.OutWardStagingItemData;

/**
 * @author Trigital
 * 
 */
@Service
public class OutWardStagingItemReadPlatformServiceImpl implements OutWardStagingItemReadPlatformService {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public OutWardStagingItemReadPlatformServiceImpl(final RoutingDataSource dataSource) {
		
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see #retrieveAllOutWardInvData()
	 */
	public List<OutWardStagingItemData> retrieveAllOutWardItemData() {

		final OutWardInvDataMapper mapper = new OutWardInvDataMapper();

		final String sql = "Select Distinct " + mapper.schema();

		return this.jdbcTemplate.query(sql, mapper, new Object[] {});
	}

	private static final class OutWardInvDataMapper implements
			RowMapper<OutWardStagingItemData> {

		public String schema() {
			return " ost.id as id, ost.invoice_id as invoiceId, ost.item_type as itemType, ost.item_code as itemCode, ost.tax_value as taxValue, ost.igst_rate as igstRate,"
					+ " ost.igst_amount as igstAmount, ost.cgst_rate as cgstRate,ost.cgst_amount as cgstAmount , ost.sgst_rate as sgstRate, ost.sgst_amount as sgstAmount, ost.cess_rate as cessRate,"
					+ " ost.cess_amount as cessAmount,ost.status as status, ost.error_code as errorCode, ost.error_descriptor as errorDescripter "
					+ " from g_ow_stg_items ost";
		}

		@Override
		public OutWardStagingItemData mapRow(final ResultSet rs, final int rowNum)
				throws SQLException {

			final Long id = rs.getLong("id");
			final Long invoiceId = rs.getLong("invoiceId");
			final String itemType = rs.getString("itemType");
			final String itemCode = rs.getString("itemCode");
			final BigDecimal taxValue = rs.getBigDecimal("taxValue");
			final BigDecimal igstRate = rs.getBigDecimal("igstRate");
			final BigDecimal igstAmount = rs.getBigDecimal("igstAmount");
			final BigDecimal cgstRate = rs.getBigDecimal("cgstRate");
			final BigDecimal cgstAmount = rs.getBigDecimal("cgstAmount");
			final BigDecimal sgstRate = rs.getBigDecimal("sgstRate");
			final BigDecimal sgstAmount = rs.getBigDecimal("sgstAmount");
			final BigDecimal cessRate = rs.getBigDecimal("cessRate");
			final BigDecimal cessAmount = rs.getBigDecimal("cessAmount");
			final int status = rs.getInt("status");
			final String errorCode = rs.getString("errorCode");
			final String errorDescripter = rs.getString("errorDescripter");
			

			return new OutWardStagingItemData(id, invoiceId, itemType, itemCode, taxValue, igstRate,
					 igstAmount, cgstRate,cgstAmount , sgstRate, sgstAmount, cessRate, cessAmount, 
					 status, errorCode, errorDescripter);
			
		}
	}

	/*
	 * (non-Javadoc)
	 * */
	 
	public List<OutWardStagingItemData> retriveOutwardStagingInvItems(final Long invoiceId) {

		try {

			final OutWardInvDataMapper mapper = new OutWardInvDataMapper();

			final String sql = "select " + mapper.schema() + " join g_ow_stg_invoice osi on ost.invoice_id = osi.id where ost.invoice_id = ? ";

			return jdbcTemplate.query(sql, mapper, new Object[] { invoiceId });
		} catch (EmptyResultDataAccessException accessException) {
			return null;
		}
	}

}
