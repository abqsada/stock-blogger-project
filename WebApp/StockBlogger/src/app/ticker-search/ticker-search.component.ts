import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { RestService } from '../rest.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-ticker-search',
  templateUrl: './ticker-search.component.html',
  styleUrls: ['./ticker-search.component.css']
})


export class TickerSearchComponent implements OnInit {
  // Handles the ticker the user searches for
  tickerSymbol: string;
  // Handles displaying an error message
  errorMessage: string;
  // Handles displaying which ticker was selected
  selectedTicker: string;
  // 'values' object that holds ticker data
  values: any;
  config: { body: Config; headers: any; feedUrl: string; textfile: string; };
  error: any;
  headers: any;

  readonly httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
    })
  };

  constructor(private http: HttpClient,
              private rest: RestService) { }
  // Runs on Initialization
  ngOnInit() {
    console.log('Entered ticker-search.component.ts');
    console.log('vvvv IGNORE THIS ERROR vvvv');
    // this.rest.getTickers(); // Gets all available tickers on initialization
    this.showConfig();
  }

  getConfigResponse(): Observable<HttpResponse<Config>> {
    return this.http.get<Config>(
      this.rest.baseUrl, { observe: 'response' });
  }
  // The callback in this method receives a typed data object, 
  // which is easier and safer to consume:
  showConfig() {
    this.rest.getConfig()
      .subscribe(
        (data: Config) => this.config = { ...data }, // success path
        error => this.error = error // error path
      );
  }
 // Displays the response headers as well as the configuration
  showConfigResponse() {
    this.rest.getConfig()
      // resp is of type `HttpResponse<Config>`
      .subscribe(resp => {
        // display its headers
        const keys = resp.headers.keys();
        this.headers = keys.map(key =>
          `${key}: ${resp.headers.get(key)}`);

        // access the body directly, which is typed as `Config`.
        this.config = { ...resp.body };
      });
  }

  // Sorts Ticker data against users searched ticker
  sortTickers(values: any) {
    // TODO: Use either REGEX or some sorting algorithm to find data
    // Only relevant to the ticker a user has entered
  }
  // Handles when a user enters a ticker symbol
  onSubmit() {
    // Acceptable characters for input
    const letters = /^[A-Za-z]+$/;

    if (this.tickerSymbol.valueOf().match(letters)) {
      if (this.tickerSymbol.length < 6 && this.tickerSymbol.length > 2) {
        // Log to Console for Testing
        console.log('SENDING TICKER: ' + this.tickerSymbol);
        this.errorMessage = '';
        this.selectedTicker = 'Displaying Stock Data for: ' + this.tickerSymbol.toUpperCase();
        // Find data relevant only to the users specified ticker
        this.sortTickers(this.tickerSymbol);
        return this.tickerSymbol;
      }

    }
    if (this.tickerSymbol.length > 6 || this.tickerSymbol.length < 2) {
      // For Binding to the HTML
      this.errorMessage = 'Something isn\'t right. Try again.';
      this.selectedTicker = '';
      console.log(this.errorMessage); // Log to Console for Testing
    } else {
      // For Binding to the HTML
      this.errorMessage = 'Something isn\'t right. Try again.';
      this.selectedTicker = '';
      console.log(this.errorMessage); // Log to Console for Testing
    }

  }

}

// Define an interface with the correct shape
export interface Config {
  body: Config;
  headers: any;
  feedUrl: string;
  textfile: string;
}
