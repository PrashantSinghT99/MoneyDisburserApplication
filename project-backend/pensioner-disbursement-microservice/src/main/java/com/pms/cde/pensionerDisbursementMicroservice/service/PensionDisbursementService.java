package com.pms.cde.pensionerDisbursementMicroservice.service;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.pms.cde.pensionerDisbursementMicroservice.Model.PensionerDetail;
import com.pms.cde.pensionerDisbursementMicroservice.Model.ProcessPensionInput;
import com.pms.cde.pensionerDisbursementMicroservice.Model.ProcessPensionResponse;

public interface PensionDisbursementService {
	
	public ProcessPensionResponse code(PensionerDetail pensionerDetail, ProcessPensionInput processPensionInput);
	public ProcessPensionResponse getcode(@RequestHeader("Authorization") String header,
			@Valid @RequestBody ProcessPensionInput processPensionInput) throws Exception;
}
