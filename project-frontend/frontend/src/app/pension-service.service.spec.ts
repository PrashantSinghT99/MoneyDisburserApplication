import { TestBed } from '@angular/core/testing';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {HttpTestingController,HttpClientTestingModule} from '@angular/common/http/testing';

import { PensionServiceService } from './pension-service.service';

describe('PensionServiceService', () => {
  let service: PensionServiceService;
  let httpMock: HttpTestingController;
  let httpClient: HttpClient;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientTestingModule],
      providers:[PensionServiceService]
    });
    service = TestBed.inject(PensionServiceService);
    httpMock = TestBed.inject(HttpTestingController);
    httpClient = TestBed.inject(HttpClient);
  });

  afterEach(() => {
    httpMock.verify();
  });

//Testing User Login.
it("Test User Login",()=>
{
  const dummyCredential:any={
    "username":"User","password":"User@1234"
};
  const dummyToken:any={
    "token":"dummytokenstringtestvalidation"
  };
service.getLogedIn(dummyCredential).subscribe(data=>{
  expect(data).toEqual(dummyToken);
})

const req = httpMock.expectOne(service._authurl+"auth/authenticate");
    expect(req.request.method).toEqual('POST');
    expect(req.request.body).toEqual(dummyCredential);

const expectedResponse = new HttpResponse({ status: 200, statusText: 'OK', body: dummyToken });
req.event(expectedResponse)
});


//Test get user details 
it("Testing get UserDetails",()=>
{
  const userDetails:any=
  { "name" : "Amit", "dateOfBirth" : "1996-02-27", "pan" : "RCASD1111T", "aadharNumber" : 401256780903, "pensionType" : "Self" }
  
  
  const responseData:any={
    "name": "Amit",
    "dateOfBirth": "27/02/1996",
    "pan": "RCASD1111T",
    "pensionType": "self",
    "pensionAmount": 85000.0
}

service.getPensionDetails(userDetails).subscribe(data=>{
  expect(data).toEqual(responseData);
})


const req = httpMock.expectOne(service._processurl+"process/PensionDetail");
    expect(req.request.method).toEqual('POST');
    expect(req.request.body).toEqual(userDetails);

const expectedResponse = new HttpResponse({ status: 200, statusText: 'OK', body: responseData });
req.event(expectedResponse)
});

//Testing Disbursement
it("Test Pension Disbursement",()=>
{
  const disbusrementInput:any=
  { "aadharNumber" : 401256780903, "pensionAmount": 85000.0}
  
  
  const disbursementStatus:any=
  {"pensionStatusCode":10}

service.disburse(disbusrementInput).subscribe(data=>{
  expect(data).toEqual(disbursementStatus);
})

const req = httpMock.expectOne(service._processurl+"process/ProcessPension");
    expect(req.request.method).toEqual('POST');
    expect(req.request.body).toEqual(disbusrementInput);

const expectedResponse = new HttpResponse({ status: 200, statusText: 'OK', body: disbursementStatus });
req.event(expectedResponse)
});
});
