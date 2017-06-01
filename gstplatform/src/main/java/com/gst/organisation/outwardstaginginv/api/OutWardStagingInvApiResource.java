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
import com.gst.organisation.outwardstaginginv.data.OutWardStagingInvData;
import com.gst.organisation.outwardstaginginv.service.OutWardStagingInvReadPlatformService;
<<<<<<< HEAD
import com.gst.organisation.outwardstaginginv.service.OutWardStagingInvWritePlatformService;
=======
>>>>>>> upstream/master

/**
 * @author 
 * 
 */
@Path("/outwardinv")
@Component
@Scope("singleton")
public class OutWardStagingInvApiResource {


	private final Set<String> RESPONSE_PARAMETERS = new HashSet<String>(Arrays.asList("id", "gstin", "gstinPurchaser","cName",
			"supplierInvNo", "supplierInvDate","supplierInvValue", "supplyStateCode","orderNo","orderDate","etin","invoiceId","receiptStateCode",
<<<<<<< HEAD
			"status","errorCode","errorDescripter","modeNo"));
=======
			"status","errorCode","errorDescripter"));
>>>>>>> upstream/master
	
	private final String resourceNameForPermissions = "OUTWARDINV";
	private final PlatformSecurityContext context;
	private final PortfolioCommandSourceWritePlatformService commandSourceWritePlatformService;
	private final DefaultToApiJsonSerializer<OutWardStagingInvData> toApiJsonSerializer;
	private final ApiRequestParameterHelper apiRequestParameterHelper;
	private final OutWardStagingInvReadPlatformService outWardStagingInvReadPlatformService;
<<<<<<< HEAD
	private final OutWardStagingInvWritePlatformService outWardStagingInvWritePlatformService;
=======
>>>>>>> upstream/master

	@Autowired
	public OutWardStagingInvApiResource(final PlatformSecurityContext context,final PortfolioCommandSourceWritePlatformService commandSourceWritePlatformService,
			final DefaultToApiJsonSerializer<OutWardStagingInvData> toApiJsonSerializer,final ApiRequestParameterHelper apiRequestParameterHelper,
<<<<<<< HEAD
			final OutWardStagingInvReadPlatformService outWardStagingInvReadPlatformService,final OutWardStagingInvWritePlatformService outWardStagingInvWritePlatformService) {
=======
			final OutWardStagingInvReadPlatformService outWardStagingInvReadPlatformService) {
>>>>>>> upstream/master
		
		this.context = context;
		this.commandSourceWritePlatformService = commandSourceWritePlatformService;
		this.toApiJsonSerializer = toApiJsonSerializer;
		this.apiRequestParameterHelper = apiRequestParameterHelper;
		this.outWardStagingInvReadPlatformService = outWardStagingInvReadPlatformService;
<<<<<<< HEAD
		this.outWardStagingInvWritePlatformService = outWardStagingInvWritePlatformService;
=======
>>>>>>> upstream/master
	}

	/**
	 * @param uriInfo
	 * @return retrieved list of all outwardinv details
	 */
	@GET
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String retrieveAllOutWardInvData(@Context final UriInfo uriInfo) {
		
	    context.authenticatedUser().validateHasReadPermission(resourceNameForPermissions);
		final List<OutWardStagingInvData> outWardStagingInvData = this.outWardStagingInvReadPlatformService.retrieveAllOutWardInvData();
		final ApiRequestJsonSerializationSettings settings = apiRequestParameterHelper.process(uriInfo.getQueryParameters());
		return this.toApiJsonSerializer.serialize(settings, outWardStagingInvData,RESPONSE_PARAMETERS);

	}

	/**
	 * @param uriInfo
<<<<<<< HEAD
	 * @return retrieved template data for creating charge codes
	 *//*
	@GET
	@Path("template")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String retrieveChargeCodeTemplateData(@Context final UriInfo uriInfo) {

	   
		context.authenticatedUser().validateHasReadPermission(resourceNameForPermissions);
		final List<ChargeTypeData> chargeTypeData = this.chargeCodeReadPlatformService.getChargeType();
		final List<DurationTypeData> durationTypeData = this.chargeCodeReadPlatformService.getDurationType();
		final List<BillFrequencyCodeData> billFrequencyData = this.chargeCodeReadPlatformService.getBillFrequency();
		final OutWardStagingInvData chargeCodeData = new OutWardStagingInvData(null,chargeTypeData,durationTypeData,billFrequencyData);
		final ApiRequestJsonSerializationSettings settings = apiRequestParameterHelper.process(uriInfo.getQueryParameters());
		return this.toApiJsonSerializer.serialize(settings, chargeCodeData,RESPONSE_PARAMETERS);

	}*/

	/**
	 * @param uriInfo
=======
>>>>>>> upstream/master
	 * @param apiRequestBodyAsJson
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })

	public String createOutWardInvData(final String apiRequestBodyAsJson,@Context final UriInfo uriInfo) {
		
		context.authenticatedUser().validateHasReadPermission(resourceNameForPermissions);
<<<<<<< HEAD
		final CommandWrapper commandRequest = new CommandWrapperBuilder().createChargeCode().withJson(apiRequestBodyAsJson).build();
=======
		final CommandWrapper commandRequest = new CommandWrapperBuilder().createOutWardInv().withJson(apiRequestBodyAsJson).build();
>>>>>>> upstream/master
		final CommandProcessingResult result = this.commandSourceWritePlatformService.logCommandSource(commandRequest);
		return this.toApiJsonSerializer.serialize(result);
	
	}

	/**
<<<<<<< HEAD
	 * @param chargeCodeId
	 * @param uriInfo
	 * @return retrieved single charge code details
	 */
	@GET
	@Path("{chargeCodeId}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })

	public String retrieveSingleOutWardInvDataDetails(@PathParam("chargeCodeId") final Long chargeCodeId,@Context final UriInfo uriInfo) {
	   
		context.authenticatedUser().validateHasReadPermission(resourceNameForPermissions);
		OutWardStagingInvData chargeCodeData = outWardStagingInvReadPlatformService.retrieveSingleChargeCodeDetails(chargeCodeId);
		final ApiRequestJsonSerializationSettings settings = apiRequestParameterHelper.process(uriInfo.getQueryParameters());
		if(settings.isTemplate()){
		chargeCodeData.setChargeTypeData(this.chargeCodeReadPlatformService.getChargeType());
		chargeCodeData.setDurationTypeData(this.chargeCodeReadPlatformService.getDurationType());
		chargeCodeData.setBillFrequencyCodeData(this.chargeCodeReadPlatformService.getBillFrequency());
		}
		return this.toApiJsonSerializer.serialize(settings,chargeCodeData,RESPONSE_PARAMETERS);
	}

	/**
	 * @param chargeCodeId
	 * @param apiRequestBodyAsJson
	 * @return update charge code here
	 */
	@PUT
	@Path("{chargeCodeId}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })

	public String updateSingleOutWardInvData(@PathParam("chargeCodeId") final Long chargeCodeId,final String apiRequestBodyAsJson) {
	   
		context.authenticatedUser().validateHasReadPermission(resourceNameForPermissions);
		final CommandWrapper commandRequest = new CommandWrapperBuilder().updateChargeCode(chargeCodeId).withJson(apiRequestBodyAsJson)	.build();
=======
	 * @param outWardInvId
	 * @param uriInfo
	 * @return retrieved single outWardInvId details
	 */
	@GET
	@Path("{outWardInvId}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })

	public String retrieveSingleOutWardInvDataDetails(@PathParam("outWardInvId") final Long outWardInvId,@Context final UriInfo uriInfo) {
	   
		context.authenticatedUser().validateHasReadPermission(resourceNameForPermissions);
		OutWardStagingInvData outWardStagingInvData = this.outWardStagingInvReadPlatformService.retrieveSingleOutWardStagingInvDetails(outWardInvId);
		final ApiRequestJsonSerializationSettings settings = apiRequestParameterHelper.process(uriInfo.getQueryParameters());
		return this.toApiJsonSerializer.serialize(settings,outWardStagingInvData,RESPONSE_PARAMETERS);
	}

	/**
	 * @param outWardInvId
	 * @param apiRequestBodyAsJson
	 * @return update outWardInvId here
	 */
	@PUT
	@Path("{outWardInvId}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })

	public String updateSingleOutWardInvData(@PathParam("outWardInvId") final Long outWardInvId,final String apiRequestBodyAsJson) {
	   
		context.authenticatedUser().validateHasReadPermission(resourceNameForPermissions);
		final CommandWrapper commandRequest = new CommandWrapperBuilder().updateOutWardInv(outWardInvId).withJson(apiRequestBodyAsJson)	.build();
>>>>>>> upstream/master
		final CommandProcessingResult result = this.commandSourceWritePlatformService.logCommandSource(commandRequest);
		return this.toApiJsonSerializer.serialize(result);
	}
	
<<<<<<< HEAD
	/**
	 * @param uriInfo
	 * @return retrieved template data for creating charge codes
	 */
	/*@GET
	@Path("{priceId}/{clientId}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String retrieveChargeCodeTemplateData(@PathParam("priceId") final Long priceId, 
			@PathParam("clientId") final Long clientId,@QueryParam("contractId")Long contractId,
			@QueryParam("paytermCode")String paytermCode, @Context final UriInfo uriInfo) {
		
		if(contractId !=null && paytermCode != null){
			
			 this.orderWritePlatformService.checkingContractPeriodAndBillfrequncyValidation(contractId, paytermCode);
			 
		}


		if(contractId !=null && paytermCode != null){
			this.orderWritePlatformService.checkingContractPeriodAndBillfrequncyValidation(contractId, paytermCode);
		}
		//context.authenticatedUser().validateHasReadPermission(resourceNameForPermissions);
		final ChargeCodeData chargeCodeData = this.chargeCodeReadPlatformService.retrieveChargeCodeForRecurring(priceId);
		final BigDecimal finalAmount=this.chargeCodeWritePlatformService.calculateFinalAmount(chargeCodeData,clientId,priceId);
		chargeCodeData.setPlanfinalAmount(finalAmount);
		final ApiRequestJsonSerializationSettings settings = apiRequestParameterHelper.process(uriInfo.getQueryParameters());
		return this.toApiJsonSerializer.serialize(settings, chargeCodeData, RESPONSE_PARAMETERS);

	}*/
=======
>>>>>>> upstream/master

}
