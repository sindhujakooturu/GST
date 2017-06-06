package com.gst.organisation.gstr1fileinvoice.api;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gst.commands.service.PortfolioCommandSourceWritePlatformService;
import com.gst.infrastructure.core.api.ApiRequestParameterHelper;
import com.gst.infrastructure.core.serialization.ApiRequestJsonSerializationSettings;
import com.gst.infrastructure.core.serialization.DefaultToApiJsonSerializer;
import com.gst.infrastructure.security.service.PlatformSecurityContext;
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
    private final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService;
    private final PlatformSecurityContext context;
    private final ApiRequestParameterHelper apiRequestParameterHelper;
	private final Gstr1FileInvoiceReadPlatformService gstr1FileInvoiceReadPlatformService;
	private final Gstr1FileB2BInvoiceReadPlatformService gstr1FileB2BInvoiceReadPlatformService;    
	
	@Autowired
    public Gstr1FileInvoiceApiResource(final DefaultToApiJsonSerializer<Gstr1FileInvoiceData> toApiJsonSerializer,
			final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService,
			final PlatformSecurityContext context, final ApiRequestParameterHelper apiRequestParameterHelper,
			final Gstr1FileInvoiceReadPlatformService gstr1FileInvoiceReadPlatformService,
			final Gstr1FileB2BInvoiceReadPlatformService gstr1FileB2BInvoiceReadPlatformService) {
 
    	this.toApiJsonSerializer = toApiJsonSerializer;
		this.commandsSourceWritePlatformService = commandsSourceWritePlatformService;
		this.context = context;
		this.apiRequestParameterHelper = apiRequestParameterHelper;
		this.gstr1FileInvoiceReadPlatformService = gstr1FileInvoiceReadPlatformService;
		this.gstr1FileB2BInvoiceReadPlatformService = gstr1FileB2BInvoiceReadPlatformService;
	}
	
	@GET
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String retrieveGstr1FileInvoiceDatas(@Context final UriInfo uriInfo) {

        this.context.authenticatedUser().validateHasReadPermission(GSTR1FILEINVOICEDATA_RESOURCE_NAME);
        Collection<Gstr1FileInvoiceData> gstr1FileInvoiceDatas = this.gstr1FileInvoiceReadPlatformService.retriveGstr1FileInvoiceData();
        for(Gstr1FileInvoiceData gstr1FileInvoiceData:gstr1FileInvoiceDatas){
        	Collection<Gstr1FileB2BInvoiceData> gstr1FileB2BInvoiceDatas = this.gstr1FileB2BInvoiceReadPlatformService.retriveGstr1FileB2BInvoiceData(gstr1FileInvoiceData.getFileNo());
        	for(Gstr1FileB2BInvoiceData gstr1FileB2BInvoiceData:gstr1FileB2BInvoiceDatas){
        		this.gstr1FileB2BInvoiceReadPlatformService.retriveB2BinvoiceItems(gstr1FileB2BInvoiceData.getInvoiceId());
        	}
        	gstr1FileInvoiceData.setGstr1FileB2BInvoiceData(gstr1FileB2BInvoiceDatas);
        }
       
        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        return this.toApiJsonSerializer.serialize(settings, gstr1FileInvoiceDatas, RESPONSE_DATA_PARAMETERS);
    }

}
