import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { RestService } from '../rest.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})

export class FeedComponent implements OnInit {
  // Handles incoming feed
  values: any;
  headers: any;
  error: any;
  // Declare header
  // readonly httpOptions = {
  //   headers: new HttpHeaders({
  //     'Content-Type':  'application/json'
  //   })
  // };

  // create private Router variable 'router' to use the Router in this component
  constructor(private router: Router,
              private http: HttpClient,
              private rest: RestService) { }

  // Runs on init
  ngOnInit() {
    console.log('Entered testroutes.component.ts ngOnInit method!');
    console.log('vvvv IGNORE THIS ERROR vvvv');
    console.log('Testing!');
  }
  // Gets blog feed from API
  // getFeed() {
  //   this.http.get('http://localhost:3000/api/feed').subscribe(response => {
  //     this.values = response;
  // }, error => {
  //   console.log(error);
  //   });
  // }

//   getConfigResponse(): Observable<HttpResponse<Config>> {
//     return this.http.get<Config>(
//       this.rest.configUrl, { observe: 'response' });
//   }
//   // The callback in this method receives a typed data object, 
//   // which is easier and safer to consume:
//   showConfig() {
//     this.rest.getConfig()
//       .subscribe(
//         (data: Config) => this.config = { ...data }, // success path
//         error => this.error = error // error path
//       );
//   }
//  // Displays the response headers as well as the configuration
//   showConfigResponse() {
//     this.rest.getConfig()
//       // resp is of type `HttpResponse<Config>`
//       .subscribe(resp => {
//         // display its headers
//         const keys = resp.headers.keys();
//         this.headers = keys.map(key =>
//           `${key}: ${resp.headers.get(key)}`);

//         // access the body directly, which is typed as `Config`.
//         this.config = { ...resp.body };
//       });
//   }

  // Handles Clicking Admin Button
  clickHandler() {
    const confirm = prompt('What is your favorite color?');
    // Checks user input
    switch (confirm) {
      case 'secret':
        this.router.navigate(['secret']); // secret page(duh)
        break;
      case '':
        break;
      case 'blue':
        this.router.navigate(['']); // go home
        break;
      case 'Black':
        this.router.navigate(['adminsonly']); // admin page
        break;
      case 'black':
        this.router.navigate(['adminsonly']); // admin page
        break;
    }
  }

  // Handles navigation to the next page
  nextHandler() {
    this.router.navigate(['contribute']);
  }

  // Handles navigation to the previous page
  prevHandler() {
    this.router.navigate(['']);
  }

}

// // Define an interface with the correct shape
// export interface Config {
//   body: Config;
//   headers: any;
//   feedUrl: string;
//   textfile: string;
// }
