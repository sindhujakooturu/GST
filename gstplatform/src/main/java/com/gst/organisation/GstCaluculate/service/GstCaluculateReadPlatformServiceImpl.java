package com.gst.organisation.GstCaluculate.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.util.json.JSONArray;
import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
import com.gst.organisation.GstCaluculate.data.GstCaluculateData;
import com.gst.organisation.GstCaluculate.domain.GstCaluculateRepository;

@Service
public class GstCaluculateReadPlatformServiceImpl implements GstCaluculateReadPlatformService {
    private final GstCaluculateData gstcaluculatedata;
    private final GstCaluculateRepository repository;
    
    @Autowired
    public GstCaluculateReadPlatformServiceImpl(final GstCaluculateRepository repository,final GstCaluculateData gstcaluculatedata) {
        this.gstcaluculatedata=gstcaluculatedata;
        this.repository=repository;
    }
	
	

	@SuppressWarnings("static-access")
	@Override
	public Collection<GstCaluculateData> retrieveAllCaluculations(final Double sgstrate,final Double igstrate) throws JSONException {
		 Double caluculateSgst = this.repository.findBySgstrate(sgstrate);
	        Double caluculateIgst = this.repository.findBySgstrate(igstrate);
	        
	        
	        JSONArray array=new JSONArray();
	    	JSONObject obj=new JSONObject();
			JSONObject ssc ;
			JSONObject csc ;
			 ssc = obj.put("ssc",array);
			 csc=obj.put("csc",array);
			if(ssc.equals(csc)){
				
				JSONArray array1=new JSONArray(gstcaluculatedata);
					for(int i=0;i<array1.length();i++)
							{
							JSONObject productJObject = new JSONObject();
							productJObject.put("item name", array1.get(i));
							productJObject.put("item amount", gstcaluculatedata.getItemamount());
							productJObject.put("item code", array1.get(i));
							array1.put(productJObject);
							}
							JSONObject obj1 = new JSONObject();
							obj1.put("items",array1);
							Double sgstrate1 = (Double)(gstcaluculatedata.getItemamount()*caluculateSgst/100.0);
						    
							return  GstCaluculateData.sameCodeResponse(gstcaluculatedata.getItemcode(),gstcaluculatedata.getItemname(),
									gstcaluculatedata.getItemamount().valueOf(sgstrate1),gstcaluculatedata.getSgstrate(),gstcaluculatedata.getSgstamount()
									,gstcaluculatedata.getCgstrate(),gstcaluculatedata.getCgstamount(),gstcaluculatedata.getIgstrate(),
									gstcaluculatedata.getIgstamount(),gstcaluculatedata.getCessrate(),gstcaluculatedata.getCessamount());
			}
			else if(!ssc.equals(csc)){
				JSONArray array1=new JSONArray(gstcaluculatedata);
				//List<GstCaluculateData> product=new ArrayList<>();
				for(int i=0;i<array1.length();i++)
						{
						JSONObject productJObject = new JSONObject();
						productJObject.put("item name", array1.get(i));
						productJObject.put("item amount",gstcaluculatedata.getItemamount());
						productJObject.put("item code", array1.get(i));
						array1.put(productJObject);
						}
						JSONObject obj1 = new JSONObject();
						obj1.put("items",array1);
						Double igstrate1 = (Double)(gstcaluculatedata.getItemamount()*caluculateIgst/100.0);
						return  GstCaluculateData.response(gstcaluculatedata.getItemcode(),gstcaluculatedata.getItemname(),
								gstcaluculatedata.getItemamount().valueOf(igstrate1),gstcaluculatedata.getSgstrate(),gstcaluculatedata.getSgstamount()
								,gstcaluculatedata.getCgstrate(),gstcaluculatedata.getCgstamount(),gstcaluculatedata.getIgstrate(),
								gstcaluculatedata.getIgstamount(),gstcaluculatedata.getCessrate(),gstcaluculatedata.getCessamount());
			}
				return  retrieveAllCaluculations(sgstrate,igstrate);
			}
	}
