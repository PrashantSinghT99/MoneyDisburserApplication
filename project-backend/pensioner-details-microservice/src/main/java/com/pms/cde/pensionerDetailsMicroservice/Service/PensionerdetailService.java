package com.pms.cde.pensionerDetailsMicroservice.Service;

import java.io.IOException;
import java.text.ParseException;

import com.pms.cde.pensionerDetailsMicroservice.Exception.AadharNumberNotFoundException;
import com.pms.cde.pensionerDetailsMicroservice.Model.PensionerDetail;

public interface PensionerdetailService {
	public PensionerDetail getPensionerDetailByAadhaarNumber(long aadhaarNumber)
			throws IOException, AadharNumberNotFoundException, NumberFormatException, ParseException;
}
