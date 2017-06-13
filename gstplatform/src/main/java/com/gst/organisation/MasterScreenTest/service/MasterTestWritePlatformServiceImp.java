package com.gst.organisation.MasterScreenTest.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.gst.infrastructure.core.api.JsonCommand;
import com.gst.infrastructure.core.data.CommandProcessingResult;
import com.gst.infrastructure.core.data.CommandProcessingResultBuilder;
import com.gst.infrastructure.core.serialization.FromJsonHelper;
import com.gst.infrastructure.security.service.PlatformSecurityContext;
import com.gst.organisation.MasterScreenTest.domain.MasterChildTest;
import com.gst.organisation.MasterScreenTest.domain.MasterChildTestRepository;
import com.gst.organisation.MasterScreenTest.domain.MasterTest;
import com.gst.organisation.MasterScreenTest.domain.MasterTestRepository;

/**
 * @author Trigital
 * 
 */
@Service
public class MasterTestWritePlatformServiceImp implements MasterTestWritePlatformService {

	private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(MasterTestWritePlatformServiceImp.class);

	private final PlatformSecurityContext context;
	private final MasterTestRepository masterTestRepository;
	private final FromJsonHelper fromApiJsonHelper;
	private final MasterChildTestRepository masterChildTestRepository; 
    
	@Autowired
	public MasterTestWritePlatformServiceImp(final PlatformSecurityContext context,final MasterTestRepository masterTestRepository,
			final FromJsonHelper fromApiJsonHelper, final MasterChildTestRepository masterChildTestRepository) {
		
		this.context = context;
		this.masterTestRepository = masterTestRepository;
		this.fromApiJsonHelper = fromApiJsonHelper;
		this.masterChildTestRepository = masterChildTestRepository;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 */
	@Transactional
	@Override
	public CommandProcessingResult createMasterTest(final JsonCommand command) {

		try {
			//this.apiJsonDeserializer.validaForCreate(command.json());
			final MasterTest masterTest  = MasterTest.fromJson(command);
			
			List<MasterChildTest> childDataList = new ArrayList<MasterChildTest>();
			final JsonArray childDetailsArray = command.arrayOfParameterNamed("childDetails").getAsJsonArray();
			String[] childListDatas = new String[childDetailsArray.size()];
			for(int i = 0; i < childDetailsArray.size(); i++){
				childListDatas[i] = childDetailsArray.get(i).toString();
		     }
		
			for (final String childListData : childListDatas) {
						
					final JsonElement element = this.fromApiJsonHelper.parse(childListData);
					final Long invoiceId = this.fromApiJsonHelper.extractLongNamed("invoiceId",element);
					final String column15 = this.fromApiJsonHelper.extractStringNamed("column15",element);
					final String column16 = this.fromApiJsonHelper.extractStringNamed("column16",element);
					final BigDecimal column17 = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("column17",element);
					final BigDecimal column18 = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("column18",element);
					final BigDecimal column19 = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("column19",element);
					final BigDecimal column20 = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("column20",element);
					final BigDecimal column21 = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("column21",element);
					final BigDecimal column22 = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("column22",element);
					final BigDecimal column23 = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("column23",element);
					final BigDecimal column24 = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("column24",element);
					final BigDecimal column25 = this.fromApiJsonHelper.extractBigDecimalWithLocaleNamed("column25",element);
					final Integer status = this.fromApiJsonHelper.extractIntegerWithLocaleNamed("status",element);
					final String column26 = this.fromApiJsonHelper.extractStringNamed("column26",element);
					final String column27 = this.fromApiJsonHelper.extractStringNamed("column27",element);
				
					childDataList.add(new MasterChildTest(invoiceId, column15, column16, column17, column18, column19, column20, column21,
							column22, column23, column24, column25, status,column26, column27));
				
			}
			this.masterChildTestRepository.save(childDataList);
			
			this.masterTestRepository.save(masterTest);
			return new CommandProcessingResultBuilder().withCommandId(command.commandId()).withEntityId(masterTest.getId()).build();
		} catch (final DataIntegrityViolationException dve) {
			handleDataIntegrityIssues(command, dve);
			return new CommandProcessingResult(Long.valueOf(-1L));
		}
	}

	private void handleDataIntegrityIssues(final JsonCommand command,
			final DataIntegrityViolationException dve) {
		   LOGGER.error(dve.getMessage(), dve);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.lang.Long)
	 */
	/*@Transactional
	@Override
	public CommandProcessingResult updateOutWardInv(final JsonCommand command,final Long outWardInvId) {
		
		
		try {
			context.authenticatedUser();
			this.apiJsonDeserializer.validaForCreate(command.json());
			final OutWardStagingInv outWardStagingInv = retrieveOutWardInvById(outWardInvId);
			
			final Map<String, Object> changes = outWardStagingInv.update(command);
			if (!changes.isEmpty()) {
				outWardStagingInvRepository.saveAndFlush(outWardStagingInv);
			}

			return new CommandProcessingResultBuilder().withCommandId(command.commandId()).withEntityId(outWardStagingInv.getId()).with(changes).build();
		} catch (DataIntegrityViolationException dve) {
			return new CommandProcessingResult(Long.valueOf(-1L));
		}
	}

	private OutWardStagingInv retrieveOutWardInvById(final Long outWardInvId) {
		final OutWardStagingInv outWardStagingInv = this.outWardStagingInvRepository.findOne(outWardInvId);
		if (outWardStagingInv == null) {
			throw new OutWardStagingInvNotFoundException(outWardInvId);
		}
		return outWardStagingInv;
	}*/

}
