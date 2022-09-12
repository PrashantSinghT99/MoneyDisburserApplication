package com.pms.cde.pensionerDisbursementMicroservice.client;

import java.io.IOException;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.pms.cde.pensionerDisbursementMicroservice.Exception.PensionerDetailNotFoundException;
import com.pms.cde.pensionerDisbursementMicroservice.Model.PensionerDetail;

@FeignClient(name="pensioner-details-service", url = "http://localhost:7002/details")
public interface PensionDetailsClient {
	@GetMapping("/pensionerDetailByAadhaar/{aadhaarNumber}")
	public PensionerDetail getPensionerDetailByAadhaar(@RequestHeader("Authorization") String header,@PathVariable long aadhaarNumber)
			throws IOException, PensionerDetailNotFoundException;
}
