package com.pms.cde.pensionerDisbursementMicroservice.service;

//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.cde.pensionerDisbursementMicroservice.Exception.PensionerDetailNotFoundException;
import com.pms.cde.pensionerDisbursementMicroservice.Model.PensionerDetail;
import com.pms.cde.pensionerDisbursementMicroservice.Model.ProcessPensionInput;
import com.pms.cde.pensionerDisbursementMicroservice.Model.ProcessPensionResponse;
import com.pms.cde.pensionerDisbursementMicroservice.client.AuthorizationServiceClient;
import com.pms.cde.pensionerDisbursementMicroservice.client.PensionDetailsClient;
import com.pms.cde.pensionerDisbursementMicroservice.controller.PensionDisbursementController;

@Service
public class PensionDisbursmentServiceImpl implements PensionDisbursementService {

	private static final Logger log = LoggerFactory.getLogger(PensionDisbursementController.class);

	@Autowired
	private PensionDetailsClient pensionDetailsClient;
	@Autowired
	private AuthorizationServiceClient authorizationServiceClient;

	@Autowired
	private PensionDisbursementService pensionDisbursmentService;

	public ProcessPensionResponse code(PensionerDetail pensionerDetail, ProcessPensionInput processPensionInput) {
		ProcessPensionResponse processPensionResponse = new ProcessPensionResponse();
		double serviceCharge = pensionerDetail.getBank().getBankType().equalsIgnoreCase(Constants.BANK_PUBLIC)
				? ConstantsConfig.PUBLIC_BANK_CHARGE
				: ConstantsConfig.PRIVATE_BANK_CHARGE;

		double pensionAmount = pensionerDetail.getPensionType().equalsIgnoreCase(Constants.PENSION_SELF)
				? ((0.8 * pensionerDetail.getSalary()) + pensionerDetail.getAllowance())
				: ((0.5 * pensionerDetail.getSalary()) + pensionerDetail.getAllowance());
		if (serviceCharge == processPensionInput.getServiceCharge()
				&& pensionAmount == processPensionInput.getPensionAmount())
			processPensionResponse.setPensionStatusCode(ConstantsConfig.DISBURSE_SUCCESSFULL);

		else {
			processPensionResponse.setPensionStatusCode(ConstantsConfig.DISBURSE_FAILED);

		}

		return processPensionResponse;
	}

	public ProcessPensionResponse getcode(String header, @Valid ProcessPensionInput processPensionInput)
			throws Exception {
		if (authorizationServiceClient.authorizeRequest(header)) {
			try {
				//sending process pension adhaar to process detail to fetch details
				PensionerDetail pensionerDetail = pensionDetailsClient.getPensionerDetailByAadhaar(header,
						processPensionInput.getAadharNumber());
				System.out.println(pensionerDetail.getAllowance() + " " + pensionerDetail.getName() + " "
						+ pensionerDetail.getBank() + " " + pensionerDetail.getDateOfBirth() + " "
						+ pensionerDetail.getPan() + " " + pensionerDetail.getPensionType() + " "
						+ pensionerDetail.getSalary());
				ProcessPensionResponse processPensionResponse = pensionDisbursmentService.code(pensionerDetail,
						processPensionInput);
				log.debug("The code is " + processPensionResponse);

				log.info("End getcode");

				return processPensionResponse;
			} catch (PensionerDetailNotFoundException e) {
				throw new PensionerDetailNotFoundException("pensioneer with given aadhaar number "
						+ processPensionInput.getAadharNumber() + " is not found ");
			}
		} else {
			throw new Exception("User Not Authorized");
		}

	}
}
