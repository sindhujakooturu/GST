package org.apache.fineract.organisation.b2binvoice.api;

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

import org.apache.fineract.commands.service.PortfolioCommandSourceWritePlatformService;
import org.apache.fineract.infrastructure.core.api.ApiRequestParameterHelper;
import org.apache.fineract.infrastructure.core.serialization.ApiRequestJsonSerializationSettings;
import org.apache.fineract.infrastructure.core.serialization.DefaultToApiJsonSerializer;
import org.apache.fineract.infrastructure.security.service.PlatformSecurityContext;
import org.apache.fineract.organisation.b2binvoice.data.B2BInvoiceDetailsData;
import org.apache.fineract.organisation.b2binvoice.service.B2BInvoiceReadPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/b2binvoice")
@Component
@Scope("singleton")
public class B2BInvoiceApiResource {
	private final String B2BINVOICE_RESOURCE_NAME = "b2binvoice";
	private final Set<String> RESPONSE_DATA_PARAMETERS = new HashSet<>(Arrays.asList("id"));
	
	private final DefaultToApiJsonSerializer<B2BInvoiceDetailsData> toApiJsonSerializer;
    private final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService;
    private final PlatformSecurityContext context;
    private final ApiRequestParameterHelper apiRequestParameterHelper;
    private final B2BInvoiceReadPlatformService b2bInvoiceReadPlatformService;
	
    
    @Autowired
    public B2BInvoiceApiResource(final DefaultToApiJsonSerializer<B2BInvoiceDetailsData> toApiJsonSerializer,
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
        final Collection<B2BInvoiceDetailsData> b2bInvoiceDetailsDatas = this.b2bInvoiceReadPlatformService.retriveB2BInvoiceDetailsData();
        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        return this.toApiJsonSerializer.serialize(settings, b2bInvoiceDetailsDatas, RESPONSE_DATA_PARAMETERS);
    }
}
