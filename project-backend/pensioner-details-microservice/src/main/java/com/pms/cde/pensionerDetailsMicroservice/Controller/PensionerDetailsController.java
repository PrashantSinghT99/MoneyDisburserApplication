package com.pms.cde.pensionerDetailsMicroservice.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pms.cde.pensionerDetailsMicroservice.Model.PensionerDetail;
import com.pms.cde.pensionerDetailsMicroservice.Service.PensionerdetailService;
import com.pms.cde.pensionerDetailsMicroservice.client.AuthorizationClient;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/details")
@CrossOrigin
public class PensionerDetailsController {
	private static final Logger Log = LoggerFactory.getLogger(PensionerDetailsController.class);

	@Autowired
	private PensionerdetailService pensionerdetailService;

	@Autowired
	private AuthorizationClient authorizationClient;

	/*
	 * POST: localhost:7002/details/pensionerDetailByAadhaar/401256780903
	 * 
	 */

	@GetMapping("/pensionerDetailByAadhaar/{aadhaarNumber}")
	public PensionerDetail getPensionerDetailByAadhaar(@RequestHeader("Authorization") String header,
			@PathVariable long aadhaarNumber) throws Exception {

		Log.info("start getPensionerDetailByAadhaar " + aadhaarNumber);

		Log.debug("" + aadhaarNumber);
		Log.info("end getPensionerDetailByAadhaar");
		if (authorizationClient.authorizeRequest(header)) {
			return pensionerdetailService.getPensionerDetailByAadhaarNumber(aadhaarNumber);
		} else {
			throw new Exception("User Not Authorized");
		}
	}
}
