import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Config } from './ticker-search/ticker-search.component';

@Injectable({
  providedIn: 'root'
})
export class RestService {

  // BASE URL *Does NOT include URL parameters*
  // such as /blogposts, /twitter, or /accounts
  readonly tickerEndPoint = 'http://localhost:3000/api/feed';

  // Stores tickers as JSON Object
  values: any;

  // Inject the Http Client into the constructor
  constructor(private http: HttpClient) { }

  // Gets ticker data from the ticker API
  getTickers() {
    // Makes get request that implements the 'Config Interface' at baseURL (localhost:3000)
    this.http.get<Config>(this.tickerEndPoint).subscribe(response => { // subscribes to the HTTP response
      this.values = response; // Assign the JSON Object received from the HTTP response to 'values'
      console.log(this.values); // ! ! ! LOGS JSON OBJECT TO CONSOLE FOR TESTING ! ! !
      console.log('SUCCESSFULLY RETRIEVED TICKER JSON OBJECT FROM ' + this.tickerEndPoint);
  }, error => {
    console.log(error);
    });
    // now returns an Observable of Config
    return this.http.get<Config>(this.tickerEndPoint);
  }

}
