package com.pms.cde.pensionerDetailsMicroservice.Controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.pms.cde.pensionerDetailsMicroservice.Controller.PensionerDetailsController;
import com.pms.cde.pensionerDetailsMicroservice.Model.Bank;
import com.pms.cde.pensionerDetailsMicroservice.Model.PensionerDetail;
import com.pms.cde.pensionerDetailsMicroservice.Service.PensionerdetailServiceImpl;
import com.pms.cde.pensionerDetailsMicroservice.Util.DateUtil;
import com.pms.cde.pensionerDetailsMicroservice.client.AuthorizationClient;
@SpringBootTest(classes = PensionDetailsControllerTest.class)
class PensionDetailsControllerTest {

	@InjectMocks
	PensionerDetailsController controller;

	@Mock
	PensionerdetailServiceImpl service;

	@Mock
	AuthorizationClient authorizationClient;

	@Test
	void testToGetCorrectPenionerDetailsFromController() throws Exception {
		PensionerDetail pensionerDetail = new PensionerDetail("Amit", DateUtil.parseDate("27-02-1996"), "RCASD1111T",
				100000, 5000, "self", new Bank("SBI", 47923878, "private"));
		when(authorizationClient.authorizeRequest("")).thenReturn(true);
		when(service.getPensionerDetailByAadhaarNumber(401256780903L)).thenReturn(pensionerDetail);
		PensionerDetail actual = controller.getPensionerDetailByAadhaar("",401256780903L);
		assertNotNull(actual);
		assertEquals(actual, pensionerDetail);

	}
	
	@Test
	void testToGetCorrectPenionerDetailsFromService() throws Exception{
		
		
		PensionerDetail pensionerDetail = new PensionerDetail("Amit", DateUtil.parseDate("27-02-1996"), "RCASD1111T",
				100000, 5000, "self", new Bank("SBI", 47923878, "private"));
		when(authorizationClient.authorizeRequest("")).thenReturn(true);
		when(service.getPensionerDetailByAadhaarNumber(401256780903L)).thenReturn(pensionerDetail);
		PensionerDetail actual = service.getPensionerDetailByAadhaarNumber(401256780903L);
		assertNotNull(actual);
		assertEquals(actual, pensionerDetail);

		
	}

	@Test
	void testForAadharNumberNotInCsvFile() throws Exception {
		
		when(authorizationClient.authorizeRequest("")).thenReturn(true);
		PensionerDetail actual = controller.getPensionerDetailByAadhaar("",401256780903L);
		assertNull(actual);
	}

}
