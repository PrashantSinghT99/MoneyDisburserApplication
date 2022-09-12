import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { PensionServiceService } from '../pension-service.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  constructor(private _pensionService: PensionServiceService) { }

  //VARIABLE FOR LOGIN ERROR STATUS

  alert: boolean = false;

  //RESPONSE DATA AFTER LOGIN REQUEST

  public userData = {};

  loader: boolean = false;

  //LOGIN CREDENTIALS

  credential = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  });

  ngOnInit(): void {
    //IF USER LOGGED IN ,THEN REDIRECT NO NEED TO LOGIN AGAIN
    if (sessionStorage.getItem('token')) {
      window.location.href = '/pensiondetails';
    }
  }

  //LOGIN
  loginSubmit() {
    this.loader = true;
    this._pensionService.getLogedIn(this.credential.value).subscribe(
      (value) => {
        this.loader = false;
        if (value.token) {
          //AFTER SUCCESSFUL LOGIN JWT TOKEN IS STORED VIA SESSION STORAGE
          sessionStorage.token = value.token;

          window.location.href = '/pensiondetails';
        }
        // ALERT MESSAGE SET TO TRUE IF LOGIN FAILED
        else this.alert = true;
      },
      (error: any) => {
        if (error.ok == false) {
          this.loader = false;
          window.location.href = '/error';
        }
      }
    );
  }
}
