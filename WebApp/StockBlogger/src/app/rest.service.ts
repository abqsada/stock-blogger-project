import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Config } from './ticker-search/ticker-search.component';

@Injectable({
  providedIn: 'root'
})
export class RestService {

  // BASE URL *Does NOT include URL parameters*
  // such as /blogposts, /twitter, or /accounts
  readonly baseUrl = 'http://localhost:3000';
  values: any;

//   readonly httpOptions = {
//   headers: new HttpHeaders({
//     'Content-Type':  'application/json'
//   })
// };

  // Inject the Http Client into the constructor
  constructor(private http: HttpClient) { }

  configUrl = 'assets/config.json';

  getConfig() {
    // now returns an Observable of Config
    return this.http.get<Config>(this.baseUrl);
  }

  // Gets all ticker data from API
  getTickers() {  // This URL likely needs to be changed, just boilerplate right now
    this.http.get(this.baseUrl + '/api/ticker').subscribe(response => {
      this.values = response;
      console.log(this.values);
  }, error => {
    console.log(error);
    });
  }

  // private extractData(res: Response) {
  //   let body = res;
  //   return body || { };
  // }
}
