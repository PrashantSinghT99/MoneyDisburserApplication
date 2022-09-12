package com.pms.cde.pensionerDisbursementMicroservice.controller;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.cde.pensionerDisbursementMicroservice.Model.ProcessPensionInput;
import com.pms.cde.pensionerDisbursementMicroservice.Model.ProcessPensionResponse;
import com.pms.cde.pensionerDisbursementMicroservice.service.PensionDisbursmentServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/disburse")
@CrossOrigin
public class PensionDisbursementController {
	
	private static final Logger log = LoggerFactory.getLogger(PensionDisbursementController.class);

	@Autowired
	private PensionDisbursmentServiceImpl pensionDisbursmentServiceImpl;

	



	@PostMapping("/disbursePension")
	public ProcessPensionResponse getcode(@RequestHeader("Authorization") String header,
			@Valid @RequestBody ProcessPensionInput processPensionInput) throws Exception {
		
		log.info("Start getcode");
		return pensionDisbursmentServiceImpl.getcode(header,processPensionInput);

	}
}
