import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class PensionServiceService {
  // Authorisation : "http://localhost:7001/"
  // disbursement : "http://localhost:7003/"
  // process : "http://localhost:7004/"

  //url's
  public _authurl = 'http://localhost:7001/';
  // public _authurl = 'http://load-balancer-pst-886047748.ap-south-1.elb.amazonaws.com/';
  // public _disburseurl = 'http://localhost:7003/';
  // public _processurl = 'http://13.233.89.244:7004/';
  public _processurl = 'http://localhost:7004/';
  

  constructor(private _http: HttpClient) {
    console.log('in httpclient construct');
  }

  //LOGIN 
  getLogedIn(credential: any) {
    return this._http.post<any>(
      this._authurl + 'auth/authenticate',
      credential
    );
  }

  //PENSION DETAIL VALIDATION
  getPensionDetails(data: any) {
    console.log('successfully pension details');
   //TOKEN
    const token = sessionStorage.getItem('token');

    //PASSING TOKEN TO HEADER
    const header = token ? { Authorization: `Bearer ${token}` } : undefined;
    console.log('token is added');
    //HTTP REQUEST FOR LOGIN
    return this._http.post<any>(
      this._processurl + 'process/PensionDetail',
      {
        name: data.name,
        dateOfBirth: data.dateOfBirth,
        pan: data.pan.toUpperCase(),
        aadharNumber: Number(data.aadharNumber),
        pensionType: data.pensionType,
      },
      {
        headers: header,
      }
    );
  }

//PENSION DISBURSEMENT
  disburse(data: any) {
    console.log("inside process");
    //TAKING TOKEN FROM SESSION STORAGE
    const token = sessionStorage.getItem('token');

    //PASSING TOKEN TO HEADER
    const header = token ? { Authorization: `Bearer ${token}` } : undefined;

    //PENSION DISBURSE REQUEST
    return this._http.post<any>(
      this._processurl + 'process/ProcessPension',
      data,
      {
        headers: header,
      }
    );
  }
}
