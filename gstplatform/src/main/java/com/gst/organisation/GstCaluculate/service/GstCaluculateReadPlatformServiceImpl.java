/*package com.gst.organisation.GstCaluculate.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.gst.infrastructure.core.service.RoutingDataSource;
import com.gst.organisation.GstCaluculate.data.GstCaluculateData;

public class GstCaluculateReadPlatformServiceImpl implements GstCaluculateReadPlatformService {
	
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public GstCaluculateReadPlatformServiceImpl(final RoutingDataSource dataSource) {
		
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	 * (non-Javadoc)
	 * 
	 * @see #retrieveAllOutWardInvData()
	 
	public List<GstCaluculateData> retrieveAllGstCaluculationData() {

		final OutWardInvDataMapper mapper = new OutWardInvDataMapper();

		final String sql = "Select " + mapper.schema();

		return this.jdbcTemplate.query(sql, mapper, new Object[] {});
	}

	private static final class OutWardInvDataMapper implements
			RowMapper<GstCaluculateData> {

		public String schema() {
			return " g.id as id, g.item_code as itemCode, g.item_desc as itemDesc, g.cgst_rate as cgstRate, g.igst_rate as igstRate, g.cess_rate as cessRate, "
					+ " g.sgst_rate as sgstRate"
					+ " from g_gst_rate g";
		}

		@Override
		public GstCaluculateData mapRow(final ResultSet rs, final int rowNum)
				throws SQLException {

			final Long id = rs.getLong("id");
			final String itemCode = rs.getString("itemCode");
			final BigDecimal sgstRate = rs.getBigDecimal("sgstRate");
			final BigDecimal cgstRate = rs.getBigDecimal("cgstRate");
			final BigDecimal igstRate = rs.getBigDecimal("igstRate");
			final BigDecimal cessRate = rs.getBigDecimal("cessRate");
			final BigDecimal itemAmount = rs.getBigDecimal("itemAmount");
			final String itemName = rs.getString("itemName");
			final String itemDesc = rs.getString("itemDesc");
			final BigDecimal sgstAmount = rs.getBigDecimal("sgstAmount");
			final BigDecimal cgstAmount = rs.getBigDecimal("cgstAmount");
			final BigDecimal igstAmount = rs.getBigDecimal("igstAmount");
			final BigDecimal cessAmount = rs.getBigDecimal("cessAmount");
			
			

			return new GstCaluculateData(id, itemCode, itemName, cgstRate, igstRate, cessRate, itemAmount,
					sgstRate, itemDesc,sgstAmount,cgstAmount , igstAmount, cessAmount);
		}
	}

	
	 * (non-Javadoc)
	 * 
	 
	public GstCaluculateData retrieveSingleGstRateDetails(final Long Id) {

		try {

			final OutWardInvDataMapper mapper = new OutWardInvDataMapper();

			final String sql = "select " + mapper.schema() + " where g.id = ?";

			return jdbcTemplate.queryForObject(sql, mapper, new Object[] { Id });
		} catch (EmptyResultDataAccessException accessException) {
			return null;
		}
	}
	
    private final GstCaluculateData gstcaluculatedata;
    private final GstCaluculateRepository repository;
    
    @Autowired
    public GstCaluculateReadPlatformServiceImpl(final GstCaluculateRepository repository,final GstCaluculateData gstcaluculatedata) {
    	this.repository=repository;
    	this.gstcaluculatedata=gstcaluculatedata;
    }
	
	@Override
	public Collection<GstCaluculateData> retrieveAllCaluculations(final Double sgstrate,final Double igstrate) {
		 Double caluculateSgst = this.repository.findBySgstrate(sgstrate);
	        Double caluculateIgst = this.repository.findBySgstrate(igstrate);
	        Double sgstrate1 = (Double)(gstcaluculatedata.getItemamount()*caluculateSgst/100.0);
	        Double igstrate1 = (Double)(gstcaluculatedata.getItemamount()*caluculateIgst/100.0);
	        try{
	        JSONArray array = new JSONArray();
	        JSONObject object = null;
	        for(int i=0;i<array.length();i++){
	        	object = array.getJSONObject(i);
	        	JSONArray itemArray =  object.getJSONArray("items");
	        	
	        		for(int j=0;j<itemArray.length();j++){
	        			JSONObject cObject = itemArray.getJSONObject(j);
	        			cObject.put("item_name", 3);
	        			cObject.put("Item_code", 3);
	        			cObject.put("Item_amount", gstcaluculatedata.getItemamount());
	        			if((object.getString("ssc")).equalsIgnoreCase(object.getString("csc"))){
		        				cObject.put("sgst_rate",caluculateSgst);
		        				cObject.put("item_name", 1);
		        				cObject.put("Item_code", 1);
		        				cObject.put("Item_amount", 2);
		        				cObject.put("sgst_rate", sgstrate1);
		        				cObject.put("cgst_amount", 1);
		        				cObject.put("cgst_rate", 1);
		        				cObject.put("cess_amount", 1);
		        				cObject.put("cess_rate", 1);
	        			}else{
	        				cObject.put("item_name", 1);
	        				cObject.put("Item_code", 1);
	        				cObject.put("Item_amount", 2);
	        				cObject.put("igst_rate",igstrate1);
	        				cObject.put("cgst_amount", 1);
	        				cObject.put("cgst_rate", 1);
	        				cObject.put("cess_amount", 1);
	        				cObject.put("cess_rate", 1);
	        			}
	        
	        		}
	        		}
	        }catch(Exception e){}
	        
	        
	        return  retrieveAllCaluculations(sgstrate,igstrate);
	}
}
*/