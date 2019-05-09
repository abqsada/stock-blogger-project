import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RestService } from 'src/app/rest.service';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  // Empty object to store new user creds
  new: any = {};

   // Inject the router for navigation, HttpCLient for GET and POST, and RestService for created objects
  constructor(private http: HttpClient,
              private rest: RestService) { }

   // httpOptions decides which format can be received from GET and POST
   readonly httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })
  };
  
  register(){
    console.log(this.new);
  }

  // POST: Pass the new username and new password for signup on the register page
  PostNewUser(newUser: any): any {
    return this.http.post(this.rest.userUrl, newUser, this.httpOptions);
  }
  PostNewPassword(newPassword: any): any {
    return this.http.post(this.rest.userUrl, newPassword, this.httpOptions);
  }

  ngOnInit() {
    console.log('Entered register.component.ts ngOnInit method!');
  }

}
