package com.gst.organisation.outwardstaginginv.api;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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
import com.gst.organisation.outwardstaginginv.data.OutWardStagingItemData;
import com.gst.organisation.outwardstaginginv.service.OutWardStagingItemReadPlatformService;

@Path("/outwarditem/{outWardInvoiceId}")
@Component
@Scope("singleton")
public class OutWardStagingItemApiResource {
	
	private final Set<String> RESPONSE_PARAMETERS = new HashSet<String>(Arrays.asList("id"));
	private final String resourceNameForPermissions = "OUTWARDITEM";
	
	
	private final PlatformSecurityContext context;
	private final PortfolioCommandSourceWritePlatformService commandSourceWritePlatformService;
	private final DefaultToApiJsonSerializer<OutWardStagingItemData> toApiJsonSerializer;
	private final OutWardStagingItemReadPlatformService outWardStagingItemReadPlatformService;
	private final ApiRequestParameterHelper apiRequestParameterHelper;
	
	
	@Autowired
	public OutWardStagingItemApiResource(final PlatformSecurityContext context,
			final PortfolioCommandSourceWritePlatformService commandSourceWritePlatformService,
			final DefaultToApiJsonSerializer<OutWardStagingItemData> toApiJsonSerializer,
			final OutWardStagingItemReadPlatformService outWardStagingItemReadPlatformService,
			final ApiRequestParameterHelper apiRequestParameterHelper) {
		
		this.context = context;
		this.commandSourceWritePlatformService = commandSourceWritePlatformService;
		this.toApiJsonSerializer = toApiJsonSerializer;
		this.outWardStagingItemReadPlatformService = outWardStagingItemReadPlatformService;
		this.apiRequestParameterHelper = apiRequestParameterHelper;
	}

	/**
	 * @param uriInfo
	 * @return retrieved list of all outwarditem details
	 */
	@GET
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String retrieveAllOutWardItemData(@Context final UriInfo uriInfo) {
		
	    context.authenticatedUser().validateHasReadPermission(resourceNameForPermissions);
		final List<OutWardStagingItemData> outWardStagingItemData = this.outWardStagingItemReadPlatformService.retrieveAllOutWardItemData();
		final ApiRequestJsonSerializationSettings settings = apiRequestParameterHelper.process(uriInfo.getQueryParameters());
		return this.toApiJsonSerializer.serialize(settings, outWardStagingItemData,RESPONSE_PARAMETERS);

	}


	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })

	public String createOutWardItemData(final String apiRequestBodyAsJson,@Context final UriInfo uriInfo,@PathParam("outWardInvoiceId") final Long outWardInvoiceId) {
		
		this.context.authenticatedUser().validateHasReadPermission(resourceNameForPermissions);
		final CommandWrapper commandRequest = new CommandWrapperBuilder().createOutWardItem(outWardInvoiceId).withJson(apiRequestBodyAsJson).build();
		final CommandProcessingResult result = this.commandSourceWritePlatformService.logCommandSource(commandRequest);
		return this.toApiJsonSerializer.serialize(result);
	
	}
	
	@GET
	@Path("{invoiceId}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })

	public String retrieveSingleOutWardItemDataDetails(@PathParam("invoiceId") final Long invoiceId,@Context final UriInfo uriInfo) {
	   
		context.authenticatedUser().validateHasReadPermission(resourceNameForPermissions);
		List<OutWardStagingItemData> outWardStagingItemData = this.outWardStagingItemReadPlatformService.retriveOutwardStagingInvItems(invoiceId);
		final ApiRequestJsonSerializationSettings settings = apiRequestParameterHelper.process(uriInfo.getQueryParameters());
		return this.toApiJsonSerializer.serialize(settings,outWardStagingItemData,RESPONSE_PARAMETERS);
	}
	
	/**
	 * @param outWardInvId
	 * @param apiRequestBodyAsJson
	 * @return update outWardInvId here
	 */
	@PUT
	@Path("{outWardInvoiceId}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })

	public String updateSingleOutWardItemData(@PathParam("outWardInvoiceId") final Long outWardInvoiceId,final String apiRequestBodyAsJson) {
	   
		context.authenticatedUser().validateHasReadPermission(resourceNameForPermissions);
		final CommandWrapper commandRequest = new CommandWrapperBuilder().updateOutWardItem(outWardInvoiceId).withJson(apiRequestBodyAsJson)	.build();
		final CommandProcessingResult result = this.commandSourceWritePlatformService.logCommandSource(commandRequest);
		return this.toApiJsonSerializer.serialize(result);
	}
}
