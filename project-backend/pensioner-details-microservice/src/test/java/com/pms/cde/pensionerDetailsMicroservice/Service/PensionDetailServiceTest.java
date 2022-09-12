package com.pms.cde.pensionerDetailsMicroservice.Service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.pms.cde.pensionerDetailsMicroservice.Exception.AadharNumberNotFoundException;
import com.pms.cde.pensionerDetailsMicroservice.Model.Bank;
import com.pms.cde.pensionerDetailsMicroservice.Model.PensionerDetail;
import com.pms.cde.pensionerDetailsMicroservice.Service.PensionerdetailServiceImpl;
import com.pms.cde.pensionerDetailsMicroservice.Util.DateUtil;
@SpringBootTest(classes = PensionDetailServiceTest.class)
@AutoConfigureMockMvc
@SuppressWarnings("deprecation")
class PensionDetailServiceTest {

	@InjectMocks
	private PensionerdetailServiceImpl pds;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	

	@Test
	void testNotNullPensionDetailServiceObject() {
		assertNotNull(pds);
	}

}
