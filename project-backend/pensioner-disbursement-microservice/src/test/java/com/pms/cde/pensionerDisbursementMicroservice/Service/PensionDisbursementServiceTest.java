


package com.pms.cde.pensionerDisbursementMicroservice.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.pms.cde.pensionerDisbursementMicroservice.Model.Bank;
import com.pms.cde.pensionerDisbursementMicroservice.Model.PensionerDetail;
import com.pms.cde.pensionerDisbursementMicroservice.Model.ProcessPensionInput;
import com.pms.cde.pensionerDisbursementMicroservice.Model.ProcessPensionResponse;
import com.pms.cde.pensionerDisbursementMicroservice.service.PensionDisbursmentServiceImpl;

@SpringBootTest(classes = PensionDisbursementServiceTest.class)
public class PensionDisbursementServiceTest {

	@InjectMocks
	PensionDisbursmentServiceImpl service;
	
	@Mock
	ProcessPensionResponse response;
	
	int serviceCharge = 500;
	
	@Test
	public void testServiceObjectNotNull() {
		assertNotNull(service);
	}
	
	@Test
	public void testResponseObjectNotNull() {
		assertNotNull(response);
	}
	
	@Test
	public void testGettingCodeForPublicTypeBankFamilyPensionType() {
		Bank bank = new Bank("ICIC", 17238346, "public");
		PensionerDetail pensionerDetail=new PensionerDetail("Vaibhav", "24-12-1995", "SMUOI3987M",98000.0,5500.0,"self", bank);
		ProcessPensionInput processPensionInput=new ProcessPensionInput(360416808052l,83900.0, 500.0);
		response = service.code(pensionerDetail, processPensionInput);
		assertEquals(10, response.getPensionStatusCode());
	}
	
	@Test
	public void testGettingCodeForPrivateTypeBankFamilyPensionType() {
		Bank bank = new Bank("AXIS", 21990011, "private");
		PensionerDetail pensionerDetail=new PensionerDetail("Aaryav", "12-11-2008", "VGDUY3489A",58500.0,10000.0,"family", bank);
		ProcessPensionInput processPensionInput=new ProcessPensionInput(134530581844l,39250.0, 550.0);
		response = service.code(pensionerDetail, processPensionInput);
		assertEquals(10, response.getPensionStatusCode());
	}
	
	@Test
	public void testGettingCodeForPublicTypeBankSelfPensionType() {
		Bank bank = new Bank("EastBank", 22334455, "public");
		PensionerDetail pensionerDetail=new PensionerDetail("ghi", "2002-10-28", "ABCDE1234F", 70000.0, 3000.0, "self", bank);
		ProcessPensionInput processPensionInput=new ProcessPensionInput(112233445566l,28000.0, 560.0);
		response = service.code(pensionerDetail, processPensionInput);
		assertEquals(21, response.getPensionStatusCode());
	}
	
	@Test
	public void testGettingCodeForPrivateTypeBankSelfPensionType() {
		Bank bank = new Bank("WestBank", 22334455, "private");
		PensionerDetail pensionerDetail=new PensionerDetail("jkl", "2003-11-27", "ABCDE1234F", 80000.0, 4000.0, "self", bank);
		ProcessPensionInput processPensionInput=new ProcessPensionInput(112233445566l,27000.0, 550.0);
		response = service.code(pensionerDetail, processPensionInput);
		assertEquals(21, response.getPensionStatusCode());
	}

}
