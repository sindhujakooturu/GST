package com.gst.organisation.b2binvoice.api;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.gst.commands.domain.CommandWrapper;
import com.gst.commands.service.CommandWrapperBuilder;
import com.gst.commands.service.PortfolioCommandSourceWritePlatformService;
import com.gst.infrastructure.core.api.ApiRequestParameterHelper;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.infrastructure.core.serialization.ApiRequestJsonSerializationSettings;
import com.gst.infrastructure.core.serialization.DefaultToApiJsonSerializer;
import com.gst.infrastructure.security.service.PlatformSecurityContext;
import com.gst.organisation.b2binvoice.data.B2BInvoiceData;
import com.gst.organisation.b2binvoice.service.B2BInvoiceReadPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/b2binvoice")
@Component
@Scope("singleton")
public class B2BInvoiceApiResource {
	private final String B2BINVOICE_RESOURCE_NAME = "b2binvoice";
	private final Set<String> RESPONSE_DATA_PARAMETERS = new HashSet<>(Arrays.asList("id"));
	
	private final DefaultToApiJsonSerializer<B2BInvoiceData> toApiJsonSerializer;
    private final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService;
    private final PlatformSecurityContext context;
    private final ApiRequestParameterHelper apiRequestParameterHelper;
    private final B2BInvoiceReadPlatformService b2bInvoiceReadPlatformService;
	
    
    @Autowired
    public B2BInvoiceApiResource(final DefaultToApiJsonSerializer<B2BInvoiceData> toApiJsonSerializer,
			final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService,
			final PlatformSecurityContext context, 
			final ApiRequestParameterHelper apiRequestParameterHelper,
			final B2BInvoiceReadPlatformService b2bInvoiceReadPlatformService) {

    	this.toApiJsonSerializer = toApiJsonSerializer;
		this.commandsSourceWritePlatformService = commandsSourceWritePlatformService;
		this.context = context;
		this.apiRequestParameterHelper = apiRequestParameterHelper;
		this.b2bInvoiceReadPlatformService = b2bInvoiceReadPlatformService;
	}

    @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String retrieveAllHolidays(@Context final UriInfo uriInfo) {

        this.context.authenticatedUser().validateHasReadPermission(B2BINVOICE_RESOURCE_NAME);
        final Collection<B2BInvoiceData> b2bInvoiceDatas = this.b2bInvoiceReadPlatformService.retriveB2BInvoiceData();
        for(B2BInvoiceData b2BInvoiceData:b2bInvoiceDatas){
        	b2BInvoiceData.setB2BInvoiceDetailsData(this.b2bInvoiceReadPlatformService.retriveB2BInvoiceDetailsData(b2BInvoiceData.getId()));
        }
       
        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        return this.toApiJsonSerializer.serialize(settings, b2bInvoiceDatas, RESPONSE_DATA_PARAMETERS);
    }
    
    @PUT
    @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String update(@PathParam("id") final Long id, @QueryParam("updateStatus") final Boolean isUpdateStatus,final String apiRequestBodyAsJson) {

    	CommandWrapper commandRequest;
    	if(isUpdateStatus)
    		commandRequest = new CommandWrapperBuilder().updateB2BInvoiceStatus(id).withJson(apiRequestBodyAsJson).build();
    	else
    		commandRequest = new CommandWrapperBuilder().updateB2BInvoice(id).withJson(apiRequestBodyAsJson).build();

        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

        return this.toApiJsonSerializer.serialize(result);
    }
}
