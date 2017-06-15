package com.gst.organisation.supplier.api;

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
import com.gst.organisation.supplier.data.SupplierData;
import com.gst.organisation.supplier.service.SupplierReadPlatformService;


@Path("/supplier")
@Component
@Scope("singleton")
public class SupplierApiResource {
	
	private final Set<String> RESPONSE_DATA_PARAMETERS = new HashSet<>(Arrays.asList("id","gstin", "gstinComp", "supplierName", "contactName",
            "officePhone", "homePhone", "rmn", "fax", "rmail", "panNo", "etin", "addrLine1","addrLine2","city","state","country","pin"));
				
    private final String resourceNameForPermissions = "SUPPLIER";

    private final PlatformSecurityContext context;
    private final SupplierReadPlatformService readPlatformService;
    private final DefaultToApiJsonSerializer<SupplierData> toApiJsonSerializer;
    private final ApiRequestParameterHelper apiRequestParameterHelper;
    private final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService;
    
    @Autowired
    public SupplierApiResource(final PlatformSecurityContext context, final SupplierReadPlatformService readPlatformService,
             final DefaultToApiJsonSerializer<SupplierData> toApiJsonSerializer,
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
    public String retrieveSupplier(@Context final UriInfo uriInfo) {

    	 this.context.authenticatedUser().validateHasReadPermission(this.resourceNameForPermissions);
         final Collection<SupplierData> supplier = this.readPlatformService.retrieveAllSupplier();
         final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
         return this.toApiJsonSerializer.serialize(settings,supplier, this.RESPONSE_DATA_PARAMETERS);
    }
    @GET
    @Path("{supplierId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String retreiveHsndata(@PathParam("supplierId") final Long supplierId, @Context final UriInfo uriInfo) {
    	
        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        SupplierData supplierdata = this.readPlatformService.retrieveSupplierdata(supplierId);
        return this.toApiJsonSerializer.serialize(settings, supplierdata, this.RESPONSE_DATA_PARAMETERS);
    }
    
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String createSupplier(final String apiRequestBodyAsJson) {

        final CommandWrapper commandRequest = new CommandWrapperBuilder().createSupplier().withJson(apiRequestBodyAsJson).build();

        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

        return this.toApiJsonSerializer.serialize(result);
    }
    
    @PUT
    @Path("{supplierId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String updateStaff(@PathParam("supplierId") final Long supplierId, final String apiRequestBodyAsJson) {

        final CommandWrapper commandRequest = new CommandWrapperBuilder().updateSupplier(supplierId).withJson(apiRequestBodyAsJson).build();

        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

        return this.toApiJsonSerializer.serialize(result);
    }
}
