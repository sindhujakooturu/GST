package com.gst.organisation.GstCaluculate.api;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import net.minidev.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amazonaws.util.json.JSONArray;
import com.amazonaws.util.json.JSONException;
import com.gst.commands.domain.CommandWrapper;
import com.gst.commands.service.CommandWrapperBuilder;
import com.gst.commands.service.PortfolioCommandSourceWritePlatformService;
import com.gst.infrastructure.core.api.ApiRequestParameterHelper;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.infrastructure.core.serialization.ApiRequestJsonSerializationSettings;
import com.gst.infrastructure.core.serialization.DefaultToApiJsonSerializer;
import com.gst.infrastructure.security.service.PlatformSecurityContext;
import com.gst.organisation.GstCaluculate.data.GstCaluculateData;
import com.gst.organisation.GstCaluculate.service.GstCaluculateReadPlatformService;
import com.gst.organisation.purchaser.data.PurchaserData;
import com.gst.organisation.purchaser.service.PurchaserReadPlatformService;


@Path("/gstcaluculate")
@Component
@Scope("singleton")
public class GstCaluculateApiResource {
	
	private final Set<String> RESPONSE_DATA_PARAMETERS = new HashSet<>(Arrays.asList("id","itemcode","itemname","itemamount","sgstrate","sgstamount","cgstrate",
			"cgstamount","igstrate","igstamount","cessrate","cessamount"));
				
    private final String resourceNameForPermissions = "GSTCALUCULATER";

    private final PlatformSecurityContext context;
    private final GstCaluculateReadPlatformService readPlatformService;
    private final DefaultToApiJsonSerializer<GstCaluculateData> toApiJsonSerializer;
    private final ApiRequestParameterHelper apiRequestParameterHelper;
    private final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService;
    
    @Autowired
    public GstCaluculateApiResource(final PlatformSecurityContext context, final GstCaluculateReadPlatformService readPlatformService,
             final DefaultToApiJsonSerializer<GstCaluculateData> toApiJsonSerializer,
             final ApiRequestParameterHelper apiRequestParameterHelper,
             final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService) {
    	
        this.context = context;
        this.readPlatformService = readPlatformService;
        this.toApiJsonSerializer = toApiJsonSerializer;
        this.apiRequestParameterHelper = apiRequestParameterHelper;
        this.commandsSourceWritePlatformService = commandsSourceWritePlatformService;
    }
    
  /* @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @ResponseBody
    public String retrievePurchaser(@Context final UriInfo uriInfo) throws JSONException {
    	
			 this.context.authenticatedUser().validateHasReadPermission(this.resourceNameForPermissions);
			 //for now we can comment this authentication as we are planing to give it in public, so there wont be any user ...
			 //..it will b anonymous..or we can ask them to register before they use it
	         final Collection<GstCaluculateData> caluculate = this.readPlatformService.retrieveAllCaluculations(null, null);
	         final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
	         return this.toApiJsonSerializer.serialize(settings,caluculate, this.RESPONSE_DATA_PARAMETERS);	
		}*/
		
		 
    
    
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    @ResponseBody
    public String createGstCaluculater(@Context final UriInfo uriInfo,final String apiRequestBodyAsJson,
    		@QueryParam ("sgstrate")final Double sgstrate,@QueryParam ("igstrate") final Double igstrate) throws JSONException {
        final CommandWrapper commandRequest = new CommandWrapperBuilder().createGstCaluculater().withJson(apiRequestBodyAsJson).build();

        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);
        final Collection<GstCaluculateData>caluculate = this.readPlatformService.retrieveAllCaluculations(sgstrate,igstrate);
        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());

        return this.toApiJsonSerializer.serialize(settings,caluculate, this.RESPONSE_DATA_PARAMETERS);
        }
    }

