import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  //STATUS OF LOGIN OR LOGOUT
  login: string = "Login";
  constructor() { }

  //TOKEN EXIST THEN LOGIN TO LOGOUT
  ngOnInit(): void {
    if (sessionStorage.getItem("token")) {
      this.login = "Logout";
    }
  }

  // NAVIGATE BACK TO HOME COMPONENT AFTER LOGIN COMPONENT TOKEN GETS REMOVED

  doLogin() {
    if (sessionStorage.getItem("token")) {
      sessionStorage.removeItem("token");
      window.location.href = "/";
    }
    else {
      window.location.href = "/login";
    }
  }

}
