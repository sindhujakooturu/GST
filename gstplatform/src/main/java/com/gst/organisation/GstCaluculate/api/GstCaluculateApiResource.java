package com.gst.organisation.GstCaluculate.api;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gst.infrastructure.core.exception.PlatformApiDataValidationException;
import com.gst.infrastructure.core.serialization.DefaultToApiJsonSerializer;
import com.gst.infrastructure.core.serialization.FromJsonHelper;
import com.gst.infrastructure.security.service.PlatformSecurityContext;
import com.gst.organisation.GstCaluculate.data.GstCaluculateData;
import com.gst.organisation.GstCaluculate.domain.GstCaluculate;
import com.gst.organisation.GstCaluculate.domain.GstCaluculateRepository;


@Path("/gstcaluculate")
@Component
@Scope("singleton")
public class GstCaluculateApiResource {
	
	private final Set<String> RESPONSE_DATA_PARAMETERS = new HashSet<>(Arrays.asList("id","itemCode","itemName","itemAmount","sgstRate","sgstAmount","cgstRate",
			"cgstAmount","igstRate","igstAmount","cessRate","cessAmount"));
				
    //private final String resourceNameForPermissions = "GSTCALUCULATE";

    private final PlatformSecurityContext context;
    private final DefaultToApiJsonSerializer<GstCaluculateData> toApiJsonSerializer;
    
    private final GstCaluculateRepository gstCaluculateRepository;
    private final FromJsonHelper fromApiJsonHelper;
    
    @Autowired
    public GstCaluculateApiResource(final PlatformSecurityContext context,
             final DefaultToApiJsonSerializer<GstCaluculateData> toApiJsonSerializer,final GstCaluculateRepository gstCaluculateRepository,
              final FromJsonHelper fromApiJsonHelper) {
    	
        this.context = context;
        this.toApiJsonSerializer = toApiJsonSerializer;
        this.gstCaluculateRepository = gstCaluculateRepository;
        this.fromApiJsonHelper=fromApiJsonHelper;
    }
    
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @ResponseBody
    public String createGstCaluculate(final String apiRequestBodyAsJson) throws Exception {
    	JSONObject basicObject = new JSONObject(apiRequestBodyAsJson);
    	JSONArray array = basicObject.getJSONArray("invoice");
    	JSONObject object = null;
    	JSONArray cArray = null;
    	for(int i=0;i<array.length();i++){
    		JSONObject cObject = null;
    		GstCaluculate gstCaluculate = null;
    		object = array.getJSONObject(i);
    		cArray = object.getJSONArray("items");
    		for(int j=0;j<cArray.length();j++){
    			cObject = cArray.getJSONObject(j);
    			gstCaluculate = this.retriveGstCaluculate(cObject.getLong("itemCode"));
    			final BigDecimal itemAmount = new BigDecimal(cObject.getString("Item_amount"));
				BigDecimal sgstRate = BigDecimal.ZERO;
				BigDecimal cgstRate = BigDecimal.ZERO;
				BigDecimal igstRate = BigDecimal.ZERO;
				BigDecimal cessRate = BigDecimal.ZERO;
				BigDecimal sgstAmount = BigDecimal.ZERO;
				BigDecimal cgstAmount = BigDecimal.ZERO;
				BigDecimal igstAmount = BigDecimal.ZERO;
				BigDecimal cessAmount = BigDecimal.ZERO;
				String itemName = "Shoe";
    			if(object.getString("ssc").equalsIgnoreCase(object.getString("csc"))){
    				sgstRate = (gstCaluculate.getSgstRate().multiply(itemAmount)).divide(new BigDecimal(100));
    				cgstRate = gstCaluculate.getCgstRate().multiply(itemAmount).divide(new BigDecimal(100));
    				gstCaluculate.setSgstRate(sgstRate);
    				gstCaluculate.setCgstRate(cgstRate);
    			}else{
    				igstRate = gstCaluculate.getIgstRate().multiply(itemAmount).divide(new BigDecimal(100));
    				cessRate = gstCaluculate.getCessRate().multiply(itemAmount).divide(new BigDecimal(100));
    				gstCaluculate.setIgstRate(igstRate);
    				gstCaluculate.setCessRate(cessRate);
    			}
    			//this.gstCaluculateRepository.saveAndFlush(gstCaluculate);
    			cObject.put("sgstRate", sgstRate);cObject.put("cgstRate", cgstRate);cObject.put("igstRate", igstRate);cObject.put("cessRate", cessRate);
    			cObject.put("cessAmount", cessAmount);cObject.put("itemName", itemName);
    			cObject.put("sgstAmount", sgstAmount);
    			cObject.put("cgstAmount", cgstAmount);
    			cObject.put("igstAmount", igstAmount);
    		}
    	}
    	return array.toString();
    }

	private GstCaluculate retriveGstCaluculate(Long itemCode) {
		GstCaluculate gstCaluculate = this.gstCaluculateRepository.findOneByItemCode(itemCode);
		if(gstCaluculate == null){
			throw new NullPointerException("Item code not found exception");
		}else
			return gstCaluculate;
	}
}


