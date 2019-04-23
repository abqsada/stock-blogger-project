import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RestService {

  // BASE URL *Does NOT include URL parameters
  // such as /blogposts, or /accounts
  readonly endpoint = 'http://localhost:3000/api';

//   readonly httpOptions = {
//   headers: new HttpHeaders({
//     'Content-Type':  'application/json'
//   })
// };

  // Inject the Http Client into the constructor
  constructor(private http: HttpClient) { }

  // private extractData(res: Response) {
  //   let body = res;
  //   return body || { };
  // }
}
