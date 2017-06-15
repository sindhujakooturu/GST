package com.gst.organisation.hsndata.api;


import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gst.commands.domain.CommandWrapper;
import com.gst.commands.service.CommandWrapperBuilder;
import com.gst.commands.service.PortfolioCommandSourceWritePlatformService;
import com.gst.infrastructure.core.api.ApiRequestParameterHelper;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.infrastructure.core.serialization.ApiRequestJsonSerializationSettings;
import com.gst.infrastructure.core.serialization.DefaultToApiJsonSerializer;
import com.gst.infrastructure.security.service.PlatformSecurityContext;
import com.gst.organisation.hsndata.data.HsndataData;
import com.gst.organisation.hsndata.service.HsndataReadPlatformService;


@Path("/hsndata")
@Component
@Scope("singleton")
public class HsndataApiResourse {

    
    private final Set<String> RESPONSE_DATA_PARAMETERS = new HashSet<>(Arrays.asList("id", "hsnCode","description"));
    private final String RESOURCENAMEFORPERMISSIONS = "HSNDATA";
    private final HsndataReadPlatformService hsndataReadPlatformService;
    private final PlatformSecurityContext context;	
    private final DefaultToApiJsonSerializer<HsndataData> toApiJsonSerializer;
    private final ApiRequestParameterHelper apiRequestParameterHelper;
    private final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService;

   
    @Autowired
    public HsndataApiResourse(final HsndataReadPlatformService readPlatformService,final PlatformSecurityContext context,
        final DefaultToApiJsonSerializer<HsndataData> toApiJsonSerializer,
        final ApiRequestParameterHelper apiRequestParameterHelper,
        final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService) {
    	
    	this.context = context;
        this.hsndataReadPlatformService = readPlatformService;
        this.toApiJsonSerializer = toApiJsonSerializer;
        this.apiRequestParameterHelper = apiRequestParameterHelper;
        this.commandsSourceWritePlatformService = commandsSourceWritePlatformService;
    }

    @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String retrieveAllHsndata(@Context final UriInfo uriInfo) {
    	
    	this.context.authenticatedUser().validateHasReadPermission(RESOURCENAMEFORPERMISSIONS);
        final Collection<HsndataData> hsnData = this.hsndataReadPlatformService.retrieveAllHsndata();
        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        return this.toApiJsonSerializer.serialize(settings, hsnData, this.RESPONSE_DATA_PARAMETERS);
    }
    
    @GET
    @Path("{hsnId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String retreiveHsndata(@PathParam("hsnId") final Long hsnId, @Context final UriInfo uriInfo) {
    	
        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        HsndataData hsndata = this.hsndataReadPlatformService.retrieveHsndata(hsnId);
        return this.toApiJsonSerializer.serialize(settings, hsndata, this.RESPONSE_DATA_PARAMETERS);
    }
    
    
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String createHsndata(final String apiRequestBodyAsJson) {
    	
        final CommandWrapper commandRequest = new CommandWrapperBuilder().createHsndata().withJson(apiRequestBodyAsJson).build();
        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);
        return this.toApiJsonSerializer.serialize(result);
    }

 
    @PUT
    @Path("{hsnId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String updateHsndata(@PathParam("hsnId") final Long hsnId, final String apiRequestBodyAsJson) {
    	
        final CommandWrapper commandRequest = new CommandWrapperBuilder().updateHsndata(hsnId).withJson(apiRequestBodyAsJson).build();
        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);
        return this.toApiJsonSerializer.serialize(result);
    }

    @DELETE
	@Path("{hsnId}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public String deleteHsndata(@PathParam("hsnId") final Long hsnId) {
    	
	    final CommandWrapper commandRequest = new CommandWrapperBuilder().deleteHsndata(hsnId).build();
        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);
        return this.toApiJsonSerializer.serialize(result);

	}
}