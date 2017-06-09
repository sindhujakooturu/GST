package com.gst.organisation.gstr1fileinvoice.api;


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
import com.gst.organisation.gstr1fileinvoice.data.Gstr1FileB2BItemData;
import com.gst.organisation.gstr1fileinvoice.service.Gstr1FileB2BItemReadPlatformService;

@Path("/gstr1fileb2bitem")
@Component
@Scope("singleton")
public class Gstr1FileB2BItemApiResource {
	
	private final Set<String> RESPONSE_PARAMETERS = new HashSet<String>(Arrays.asList("id", "invoiceId", "fileNo","itemType",
			"itemCode", "taxValue","igstRate", "igstAmount","cgstRate","cgstAmount","sgstRate","sgstAmount","cessRate",
			"cessAmount","status","errorCode","errorDescr"));
	
	private final String resourceNameForPermissions = "GSTR1FILEB2BITEM";
	
	private final DefaultToApiJsonSerializer<Gstr1FileB2BItemData> toApiJsonSerializer;
    private final PortfolioCommandSourceWritePlatformService commandSourceWritePlatformService;
    private final PlatformSecurityContext context;
    private final ApiRequestParameterHelper apiRequestParameterHelper;
	private final Gstr1FileB2BItemReadPlatformService gstr1FileB2BItemReadPlatformService;
	
	@Autowired
    public Gstr1FileB2BItemApiResource(final DefaultToApiJsonSerializer<Gstr1FileB2BItemData> toApiJsonSerializer,
			final PortfolioCommandSourceWritePlatformService commandSourceWritePlatformService,
			final PlatformSecurityContext context, final ApiRequestParameterHelper apiRequestParameterHelper,
			final Gstr1FileB2BItemReadPlatformService gstr1FileB2BItemReadPlatformService) {
 
    	this.toApiJsonSerializer = toApiJsonSerializer;
		this.commandSourceWritePlatformService = commandSourceWritePlatformService;
		this.context = context;
		this.apiRequestParameterHelper = apiRequestParameterHelper;
		this.gstr1FileB2BItemReadPlatformService = gstr1FileB2BItemReadPlatformService;
	}
	
	
	@GET
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String retrieveGstr1Fileb2bItemDatas(@Context final UriInfo uriInfo) {
		
	    this.context.authenticatedUser().validateHasReadPermission(resourceNameForPermissions);
	    final Collection<Gstr1FileB2BItemData> gstr1FileB2BItemData = this.gstr1FileB2BItemReadPlatformService.retriveGstr1FileB2BItemData();
		final ApiRequestJsonSerializationSettings settings = apiRequestParameterHelper.process(uriInfo.getQueryParameters());
		return this.toApiJsonSerializer.serialize(settings, gstr1FileB2BItemData,RESPONSE_PARAMETERS);

	}
	
	/**
	 * @param uriInfo
	 * @param apiRequestBodyAsJson
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })

	public String createGstr1Fileb2bItem(final String apiRequestBodyAsJson,@Context final UriInfo uriInfo) {
		
		this.context.authenticatedUser().validateHasReadPermission(resourceNameForPermissions);
		final CommandWrapper commandRequest = new CommandWrapperBuilder().createGstr1Fileb2b2Item().withJson(apiRequestBodyAsJson).build();
		final CommandProcessingResult result = this.commandSourceWritePlatformService.logCommandSource(commandRequest);
		return this.toApiJsonSerializer.serialize(result);
	
	}
	
	/**
	 * @param outWardInvId
	 * @param apiRequestBodyAsJson
	 * @return update Gstr1Fileb2bItemId here
	 */
	@PUT
	@Path("{gstr1Fileb2bItmId}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })

	public String updateGstr1Fileb2bItem(@PathParam("gstr1Fileb2bItmId") final Long gstr1Fileb2bItmId,final String apiRequestBodyAsJson) {
	   
		context.authenticatedUser().validateHasReadPermission(resourceNameForPermissions);
		final CommandWrapper commandRequest = new CommandWrapperBuilder().updateGstr1Fileb2bItem(gstr1Fileb2bItmId).withJson(apiRequestBodyAsJson).build();
		final CommandProcessingResult result = this.commandSourceWritePlatformService.logCommandSource(commandRequest);
		return this.toApiJsonSerializer.serialize(result);
	}

}
