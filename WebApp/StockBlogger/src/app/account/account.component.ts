import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RestService } from '../rest.service';


@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
  // Declare empty Object 'model' for storing user credentials
  // {username: "username", password: "password"}
  model: any = {};

  // Inject the router for navigation, HttpCLient for GET and POST, and RestService for created objects
  constructor(private router: Router,
              private http: HttpClient,
              private rest: RestService) { }
  
  // httpOptions decides which format can be received from GET and POST
  readonly httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })
  };           

  // Handles Logging a user in and sends the corresponsing POST requests
  login() {
    console.log(this.model); // For Testing
    this.rest.getAccount();
    this.model.postUser();
    this.model.postPassword();
  }

  // POST: Pass the username for the user currently trying to login
  // userUrl will change to the port backend has set up for these post requests
  postUser(userName: any): any {
    return this.http.post(this.rest.userUrl, userName, this.httpOptions);
  }
  postPassword(password: any): any {
    return this.http.post(this.rest.userUrl, password, this.httpOptions);
  }

  // Handles Clicking Admin Button
  clickHandler() {
    this.router.navigate(['login']);
  }
  // Handles navigation to the next page
  nextHandler() {
    this.router.navigate(['about']);
  }
  // Handles navigation to the previous page
  prevHandler() {
    this.router.navigate(['contribute']);
  }
  // Program Entry Point
  ngOnInit() {
    console.log('Entered account.component.ts ngOnInit method!');
  }
}
