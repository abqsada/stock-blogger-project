import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RestService } from '../rest.service';

@Component({
  selector: 'app-ticker-search',
  templateUrl: './ticker-search.component.html',
  styleUrls: ['./ticker-search.component.css']
})
// Implements the OnInit class
export class TickerSearchComponent implements OnInit {
  // Handles the ticker the user searches for
  tickerSymbol: string;
  // Handles displaying an error message
  errorMessage: string;
  // Handles if ticker data is ready to be displayed
  showTicker: boolean = false;

// Declare the ticker object with variables to be used in displaying ticker data
private tickerObject: {
  symbol: String;
  name: String;
  currency: any;
  price: number;
  priceOpen: number;
  dayLow: number;
  dayHigh: number;
  volume: number;
  stockNameLong: String;
  timezone: String;
  lastTrade: any;
};  // Request options for HTTP POST request for sending ticker
  // to backend for specific ticker data
  private readonly httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  // Inject the HTTP Client and the RestService
  constructor(private http: HttpClient,
              private rest: RestService) { }
  // Runs on Initialization
  ngOnInit(): void {
    console.log('Entered ticker-search.component.ts');

    console.log('#####################################');
    console.log('#### Connecting to Ticker API... ####');
    console.log('#####################################');
    // Establish Connection to the Ticker API
    this.http.get(this.rest.tickerUrl).subscribe(res => {
      console.log('**********************************************************');
      console.log('******* Successfully Connected! Here is your data: *******');
      console.log(res);
      console.log('**********************************************************');
    });
  }
  // Utilize the rest service to make an HTTP Get request 
  public getTickers(): void {
    this.rest.getTickers(); // Gets all available tickers from rest.service
    this.tickerObject = this.rest.tickerObject;
    // this.sortTickers(this.values);
  }

  // Sorts Ticker data against users searched ticker
  sortTickers(values: any): void {
    // TODO: Use either REGEX or some sorting algorithm to find data
    // Only relevant to the ticker a user has entered
    console.log('Ticker Sorting not yet implemented');
  }

  sendTicker(ticker: any): any {
    return this.http.post(this.rest.tickerUrl + '/api/postticker',
      ticker, this.httpOptions);
  }

  // Handles when a user enters a ticker symbol
  onSubmit(): any {
    // Acceptable characters for input
    const letters = /^[A-Za-z]+$/;

    if (this.tickerSymbol.valueOf().match(letters)) {
      if (this.tickerSymbol.length < 6 && this.tickerSymbol.length > 2) {
        // Log to Console for Testing
        console.log('SENDING TICKER: ' + this.tickerSymbol + ' TO BACKEND!!');
        console.log('VVVV IGNORE THIS ERROR **Cannot read property \'symbol\' of undefined** IGNORE THIS ERROR VVVV')
        this.errorMessage = '';
        this.showTicker = true;
        // Ensure each entry displays the proper info for each ticker
        this.tickerSymbol = this.tickerSymbol.toUpperCase();
        // Checks user input for a ticker symbol
        switch (this.tickerSymbol) { // When a ticker symbol that is fetchable is found...
          case 'AAPL':
          this.tickerObject = this.rest.first; // Assign the matched ticker object from the rest service to 'this.tickerObject'
          break;

          case 'MSFT':
          this.tickerObject = this.rest.second; // Assign the matched ticker object from the rest service to 'this.tickerObject'
          break;

          case 'NFLX':
          this.tickerObject = this.rest.third; // Assign the matched ticker object from the rest service to 'this.tickerObject'
          break;

          case 'TEVA':
          this.tickerObject = this.rest.fourth; // Assign the matched ticker object from the rest service to 'this.tickerObject'
          break;

          case 'TSLA':
          this.tickerObject = this.rest.fifth; // Assign the matched ticker object from the rest service to 'this.tickerObject'
          break;
        }
        // Find data relevant only to the users specified ticker
        this.sendTicker(this.tickerSymbol); // POST ticker to backend
        return this.tickerSymbol;
      }
    }

    if (this.tickerSymbol.length > 6 || this.tickerSymbol.length < 2) {
      // For Binding to the HTML
      this.errorMessage = 'Something isn\'t right. Try again.';
      console.log(this.errorMessage); // Log to Console for Testing
    } else {
      // For Binding to the HTML
      this.errorMessage = 'Something isn\'t right. Try again.';
      console.log(this.errorMessage); // Log to Console for Testing
    }

  }

}
