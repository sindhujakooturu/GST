package com.gst.organisation.gstr1fileinvoice.api;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
import com.gst.organisation.company.data.CompanyData;
import com.gst.organisation.gstr1fileinvoice.data.Gstr1FileB2BInvoiceData;
import com.gst.organisation.gstr1fileinvoice.data.Gstr1FileInvoiceData;
import com.gst.organisation.gstr1fileinvoice.service.Gstr1FileB2BInvoiceReadPlatformService;
import com.gst.organisation.gstr1fileinvoice.service.Gstr1FileInvoiceReadPlatformService;

@Path("/gstr1fileinvoicedata")
@Component
@Scope("singleton")
public class Gstr1FileInvoiceApiResource {
	
	private final String GSTR1FILEINVOICEDATA_RESOURCE_NAME = "GSTR1FILEINVOICEDATA";
	private final Set<String> RESPONSE_DATA_PARAMETERS = new HashSet<>(Arrays.asList("id"));
	private final DefaultToApiJsonSerializer<Gstr1FileInvoiceData> toApiJsonSerializer;
    private final PortfolioCommandSourceWritePlatformService commandSourceWritePlatformService;
    private final PlatformSecurityContext context;
    private final ApiRequestParameterHelper apiRequestParameterHelper;
	private final Gstr1FileInvoiceReadPlatformService gstr1FileInvoiceReadPlatformService;
	private final Gstr1FileB2BInvoiceReadPlatformService gstr1FileB2BInvoiceReadPlatformService;    
	
	@Autowired
    public Gstr1FileInvoiceApiResource(final DefaultToApiJsonSerializer<Gstr1FileInvoiceData> toApiJsonSerializer,
			final PortfolioCommandSourceWritePlatformService commandSourceWritePlatformService,
			final PlatformSecurityContext context, final ApiRequestParameterHelper apiRequestParameterHelper,
			final Gstr1FileInvoiceReadPlatformService gstr1FileInvoiceReadPlatformService,
			final Gstr1FileB2BInvoiceReadPlatformService gstr1FileB2BInvoiceReadPlatformService) {
 
    	this.toApiJsonSerializer = toApiJsonSerializer;
		this.commandSourceWritePlatformService = commandSourceWritePlatformService;
		this.context = context;
		this.apiRequestParameterHelper = apiRequestParameterHelper;
		this.gstr1FileInvoiceReadPlatformService = gstr1FileInvoiceReadPlatformService;
		this.gstr1FileB2BInvoiceReadPlatformService = gstr1FileB2BInvoiceReadPlatformService;
	}
	
	@GET
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String retrieveGstr1FileInvoiceDatas(@Context final UriInfo uriInfo,@DefaultValue("false") @QueryParam("isDetails") final boolean isDetails) {

        this.context.authenticatedUser().validateHasReadPermission(GSTR1FILEINVOICEDATA_RESOURCE_NAME);
        Collection<Gstr1FileInvoiceData> gstr1FileInvoiceDatas = this.gstr1FileInvoiceReadPlatformService.retriveGstr1FileInvoiceData();
        if(isDetails){
        	for(Gstr1FileInvoiceData gstr1FileInvoiceData:gstr1FileInvoiceDatas){
        		Collection<Gstr1FileB2BInvoiceData> gstr1FileB2BInvoiceDatas = this.gstr1FileB2BInvoiceReadPlatformService.retriveGstr1FileB2BInvoiceData(gstr1FileInvoiceData.getFileNo());
        		for(Gstr1FileB2BInvoiceData gstr1FileB2BInvoiceData:gstr1FileB2BInvoiceDatas){
        			gstr1FileB2BInvoiceData.setGstr1FileB2BItemData(this.gstr1FileB2BInvoiceReadPlatformService.retriveB2BinvoiceItems(gstr1FileB2BInvoiceData.getInvoiceId()));
        		}
        		gstr1FileInvoiceData.setGstr1FileB2BInvoiceData(gstr1FileB2BInvoiceDatas);
        	}
        }
        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        return this.toApiJsonSerializer.serialize(settings, gstr1FileInvoiceDatas, RESPONSE_DATA_PARAMETERS);
    }
	
	/**
	 * @param uriInfo
	 * @param apiRequestBodyAsJson
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })

	public String createGstr1FileInvoice(final String apiRequestBodyAsJson,@Context final UriInfo uriInfo) {
		
		final CommandWrapper commandRequest = new CommandWrapperBuilder().createGstr1FileInvoice().withJson(apiRequestBodyAsJson).build();
		final CommandProcessingResult result = this.commandSourceWritePlatformService.logCommandSource(commandRequest);
		return this.toApiJsonSerializer.serialize(result);
	
	}
	
	@GET
	@Path("{gstr1InvId}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })

	public String retrieveSingleCompanyDetails(@PathParam("gstr1InvId") final Long gstr1InvId,@Context final UriInfo uriInfo) {
	   
		context.authenticatedUser().validateHasReadPermission(GSTR1FILEINVOICEDATA_RESOURCE_NAME);
		Gstr1FileInvoiceData gstr1FileInvoiceData = this.gstr1FileInvoiceReadPlatformService.retrieveSingleGstr1Details(gstr1InvId);
		final ApiRequestJsonSerializationSettings settings = apiRequestParameterHelper.process(uriInfo.getQueryParameters());
		return this.toApiJsonSerializer.serialize(settings,gstr1FileInvoiceData,RESPONSE_DATA_PARAMETERS);
	}
	
	
	/**
	 * @param outWardInvId
	 * @param apiRequestBodyAsJson
	 * @return update Gstr1FileInvoiceId here
	 */
	@PUT
	@Path("{gstr1FileInvId}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })

	public String updateGstr1FileInvoice(@PathParam("gstr1FileInvId") final Long gstr1FileInvId,final String apiRequestBodyAsJson) {
	   
		final CommandWrapper commandRequest = new CommandWrapperBuilder().updateGstr1FileInvoice(gstr1FileInvId).withJson(apiRequestBodyAsJson).build();
		final CommandProcessingResult result = this.commandSourceWritePlatformService.logCommandSource(commandRequest);
		return this.toApiJsonSerializer.serialize(result);
	}
	
	@PUT
	@Path("updatestatus/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })

	public String updateGstr1FileInvoiceStatus(@PathParam("id") final Long id,final String apiRequestBodyAsJson) {
	   
		final CommandWrapper commandRequest = new CommandWrapperBuilder().updateGstr1FileInvoiceStatus(id).withJson(apiRequestBodyAsJson).build();
		final CommandProcessingResult result = this.commandSourceWritePlatformService.logCommandSource(commandRequest);
		return this.toApiJsonSerializer.serialize(result);
	}

}
