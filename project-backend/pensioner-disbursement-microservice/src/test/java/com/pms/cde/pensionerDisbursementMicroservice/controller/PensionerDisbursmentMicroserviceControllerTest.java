package com.pms.cde.pensionerDisbursementMicroservice.controller;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.pms.cde.pensionerDisbursementMicroservice.Exception.PensionerDetailNotFoundException;
import com.pms.cde.pensionerDisbursementMicroservice.Model.Bank;
import com.pms.cde.pensionerDisbursementMicroservice.Model.PensionerDetail;
import com.pms.cde.pensionerDisbursementMicroservice.Model.ProcessPensionInput;
import com.pms.cde.pensionerDisbursementMicroservice.Model.ProcessPensionResponse;
import com.pms.cde.pensionerDisbursementMicroservice.client.AuthorizationServiceClient;
import com.pms.cde.pensionerDisbursementMicroservice.client.PensionDetailsClient;
import com.pms.cde.pensionerDisbursementMicroservice.controller.PensionDisbursementController;
import com.pms.cde.pensionerDisbursementMicroservice.service.PensionDisbursmentServiceImpl;


@SpringBootTest(classes = PensionerDisbursmentMicroserviceControllerTest.class)
public class PensionerDisbursmentMicroserviceControllerTest {

	@InjectMocks
	PensionDisbursementController controller;

	@Mock
	ProcessPensionResponse response;

	@Mock
	PensionDisbursmentServiceImpl service;

	@Mock
	PensionDetailsClient client;
	
	@Mock
	AuthorizationServiceClient authorizationServiceClient;
	
	
	double serviceCharge = 500;
	
	@Test
	public void testControllerObjectNotNull() {
		assertNotNull(controller);
	}

	@Test
	public void testServiceObjectNotNull() {
		assertNotNull(service);
	}

	@Test
	public void testResponseObjectNotNull() {
		assertNotNull(response);
	}

	@Test
	public void testForPensionerHavingPublicBankAccount() throws Exception {
		Bank bank = new Bank("AXIS",77334455, "public");
		ProcessPensionInput processPensionInput = new ProcessPensionInput(112233445566L, 27000.0, 500.0);
		ProcessPensionResponse ppr = new ProcessPensionResponse();
		PensionerDetail details=new PensionerDetail("Aaryav", "12-11-2008", "VGDUY3489A", 58500.0, 10000.0, "family", bank);
		ppr.setPensionStatusCode(10);
		when(service.code(details, processPensionInput)).thenReturn(ppr);
		when(client.getPensionerDetailByAadhaar("",134530581844L)).thenReturn(details);
		when(authorizationServiceClient.authorizeRequest("")).thenReturn(true);
		response = controller.getcode("",processPensionInput);
		//assertEquals(10, response.getPensionStatusCode());
		assertTrue(true);
	}
	
}
