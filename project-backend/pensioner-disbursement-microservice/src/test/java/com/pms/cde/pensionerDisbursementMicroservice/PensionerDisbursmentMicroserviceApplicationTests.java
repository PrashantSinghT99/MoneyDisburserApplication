package com.pms.cde.pensionerDisbursementMicroservice;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import com.pms.cde.pensionerDisbursementMicroservice.PensionerDisbursmentMicroserviceApplication;
import com.pms.cde.pensionerDisbursementMicroservice.Model.Bank;
import com.pms.cde.pensionerDisbursementMicroservice.Model.CustomErrorResponse;
import com.pms.cde.pensionerDisbursementMicroservice.Model.PensionerDetail;
import com.pms.cde.pensionerDisbursementMicroservice.Model.ProcessPensionInput;
import com.pms.cde.pensionerDisbursementMicroservice.Model.ProcessPensionResponse;

import nl.jqno.equalsverifier.EqualsVerifier;

@SpringBootTest(classes = PensionerDisbursmentMicroserviceApplicationTests.class)

public class PensionerDisbursmentMicroserviceApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void main()
	{
		PensionerDisbursmentMicroserviceApplication.main(new String[] {});
	}	
	
	
	@Test
	void testNoArgsBank()
	{
		assertThat(new Bank()).isNotNull();
	}
	
	@Test
	void testNoArgsCustomErrorResponse()
	{
		assertThat(new CustomErrorResponse()).isNotNull();
	}
	
	@Test
	void testAllArgsCustomErrorResponse()
	{
		CustomErrorResponse cr = new CustomErrorResponse( LocalDateTime.of(2019, 03, 28, 14, 33, 48, 123456789), HttpStatus.NOT_FOUND, "Not found", Arrays.asList(new String[] {"Not Found"}));
		assertThat(assertThat(cr).isNotNull());
	}
	
	@Test
	void testNoArgsPensionerDetail()
	{
		assertThat(new PensionerDetail()).isNotNull();
	}
	
	@Test
	void testNoArgsProcessPensionInput()
	{
		assertThat(new ProcessPensionInput()).isNotNull();
	}
	
	@Test
	void testAllArgsProcessPensionResponse()
	{
		ProcessPensionResponse processPensionResponse = new ProcessPensionResponse(10);
		assertThat(assertThat(processPensionResponse).isNotNull());
	}
	
	@Test
	void testSetterBank()
	{
		Bank bank = new Bank();
		bank.setAcctno(77334455);
		bank.setName("SBI");
		bank.setBankType("public");
		assertThat(assertThat(bank).isNotNull());
	}
	
	@Test
	void testSetterCustomErrorResponse()
	{
		CustomErrorResponse customErrorResponse = new CustomErrorResponse();
		customErrorResponse.setMessage(Arrays.asList(new String[] {"Not Found"}));
		customErrorResponse.setReason("Missing detail");
		customErrorResponse.setStatus(HttpStatus.NOT_FOUND);
		customErrorResponse.setTimestamp(LocalDateTime.of(2019, 03, 28, 14, 33, 48, 123456789));
		assertThat(assertThat(customErrorResponse).isNotNull());
	}
	
	@Test
	void testSetterPensionerDetail()
	{
		PensionerDetail pensionerDetail=new PensionerDetail();
		Bank bank=new Bank();
		bank.setAcctno(99887766);
		bank.setName("SBI");
		bank.setBankType("public");
		pensionerDetail.setDateOfBirth("2000-01-01");
		pensionerDetail.setName("Sangeetha");
		pensionerDetail.setPan("ZPHYU9236L");
		pensionerDetail.setSalary(30000.0);
		//pensionerDetail.setAllowances(45000.0);
		pensionerDetail.setBank(bank);
		pensionerDetail.setPensionType("self");
		assertThat(assertThat(pensionerDetail).isNotNull());
	}
	
	@Test
	void testSetterProcessPensionInputTest()
	{
		ProcessPensionInput processPensionInput = new ProcessPensionInput();
		processPensionInput.setAadharNumber(312233445599l);
		processPensionInput.setPensionAmount(50000.0);
		processPensionInput.setServiceCharge(2000);
		assertThat(assertThat(processPensionInput).isNotNull());
	}
	
	@Test
	void testSetterProcessPensionInputResponse()
	{
		ProcessPensionResponse processPensionResponse = new ProcessPensionResponse();
		processPensionResponse.setPensionStatusCode(10);
		assertThat(assertThat(processPensionResponse).isNotNull());
	}
	


}
