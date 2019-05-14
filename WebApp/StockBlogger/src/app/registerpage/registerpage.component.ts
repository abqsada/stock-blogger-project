import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RestService } from '../rest.service';

@Component({
  selector: 'app-registerpage',
  templateUrl: './registerpage.component.html',
  styleUrls: ['./registerpage.component.css']
})
// Implements OnInit
export class RegisterpageComponent implements OnInit {

  // Object created to handle the created user
  new: any = {};

  constructor(private http: HttpClient,
              private rest: RestService) { }
    // Instantiate new HTTP Header options
  readonly httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  sendNewUsername(userName: any) {
    return this.http.post(this.rest.addUserUrl, userName, this.httpOptions);
  }
  sendNewPassword(password: any) {
    return this.http.post(this.rest.addUserUrl, password, this.httpOptions);
  }

  registerUser(): void {
    console.log(this.new);
    this.new.sendNewUsername();
    this.new.sendNewPassword();
  }

  ngOnInit() {
    console.log('Entered registerpage.component.ts');
  }

}
