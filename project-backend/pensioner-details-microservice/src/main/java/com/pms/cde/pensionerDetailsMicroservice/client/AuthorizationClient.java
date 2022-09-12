package com.pms.cde.pensionerDetailsMicroservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

// @FeignClient(name = "auth-service",url = "http://demo-lb-19644722.ap-south-1.elb.amazonaws.com/auth")
//@FeignClient(name = "auth-service",url = "http://43.204.37.160:7001/auth")
@FeignClient(name = "auth-service", url = "http://localhost:7001/auth")
public interface AuthorizationClient {
	@PostMapping(value = "/authorize")
	public boolean authorizeRequest(@RequestHeader(value = "Authorization", required = true) String header);
}
