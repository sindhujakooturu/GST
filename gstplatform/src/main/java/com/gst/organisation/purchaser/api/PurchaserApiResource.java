package com.gst.organisation.purchaser.api;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
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
import com.gst.organisation.purchaser.data.PurchaserData;
import com.gst.organisation.purchaser.service.PurchaserReadPlatformService;


@Path("/purchaser")
@Component
@Scope("singleton")
public class PurchaserApiResource {
	
	private final Set<String> RESPONSE_DATA_PARAMETERS = new HashSet<>(Arrays.asList("id","gstin", "gstinComp", "purchaserName", "contactName",
            "officePhone", "homePhone", "rmn", "fax", "rmail", "panNo", "etin", "addrLine1","addrLine2","city","state","country","pin"));
				
    private final String resourceNameForPermissions = "PURCHASER";

    private final PlatformSecurityContext context;
    private final PurchaserReadPlatformService readPlatformService;
    private final DefaultToApiJsonSerializer<PurchaserData> toApiJsonSerializer;
    private final ApiRequestParameterHelper apiRequestParameterHelper;
    private final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService;
    
    @Autowired
    public PurchaserApiResource(final PlatformSecurityContext context, final PurchaserReadPlatformService readPlatformService,
             final DefaultToApiJsonSerializer<PurchaserData> toApiJsonSerializer,
             final ApiRequestParameterHelper apiRequestParameterHelper,
             final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService) {
    	
        this.context = context;
        this.readPlatformService = readPlatformService;
        this.toApiJsonSerializer = toApiJsonSerializer;
        this.apiRequestParameterHelper = apiRequestParameterHelper;
        this.commandsSourceWritePlatformService = commandsSourceWritePlatformService;
    }
    
    @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String retrievePurchaser(@Context final UriInfo uriInfo) {

    	 this.context.authenticatedUser().validateHasReadPermission(this.resourceNameForPermissions);
         final Collection<PurchaserData> purchaser = this.readPlatformService.retrieveAllPurchaser();
         final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
         return this.toApiJsonSerializer.serialize(settings,purchaser, this.RESPONSE_DATA_PARAMETERS);
    }
    
    @GET
    @Path("{purchaserId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String retreiveHsndata(@PathParam("purchaserId") final Long purchaserId, @Context final UriInfo uriInfo) {
    	
        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        PurchaserData purchaserdata = this.readPlatformService.retrievePurchaserdata(purchaserId);
        return this.toApiJsonSerializer.serialize(settings, purchaserdata, this.RESPONSE_DATA_PARAMETERS);
    }
    
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String createPurchaser(final String apiRequestBodyAsJson) {

        final CommandWrapper commandRequest = new CommandWrapperBuilder().createPurchaser().withJson(apiRequestBodyAsJson).build();

        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

        return this.toApiJsonSerializer.serialize(result);
    }
    @PUT
    @Path("{purchaserId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String updateStaff(@PathParam("purchaserId") final Long purchaserId, final String apiRequestBodyAsJson) {

        final CommandWrapper commandRequest = new CommandWrapperBuilder().updatePurchaser(purchaserId).withJson(apiRequestBodyAsJson).build();

        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

        return this.toApiJsonSerializer.serialize(result);
    }
}
