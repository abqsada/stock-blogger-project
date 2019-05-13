import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RestService } from '../rest.service';

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
  showTicker: boolean = false;

  private tickerObject: any;
  // Request options for HTTP POST request for sending ticker
  // to backend for specific ticker data
  readonly httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  // Inject the HTTP Client and the RestService
  constructor(private http: HttpClient,
    private rest: RestService) { }
  // Runs on Initialization
  ngOnInit() {
    console.log('Entered ticker-search.component.ts');
  }

  getTickers() {
    this.rest.getTickers(); // Gets all available tickers from rest.service
    this.tickerObject = this.rest.tickerObject;
    // this.sortTickers(this.values);
  }

  // Sorts Ticker data against users searched ticker
  sortTickers(values: any) {
    // TODO: Use either REGEX or some sorting algorithm to find data
    // Only relevant to the ticker a user has entered
    console.log('Ticker Sorting not yet implemented');
  }

  sendTicker(ticker: any): any {
    return this.http.post(this.rest.tickerUrl + '/api/postticker',
      ticker, this.httpOptions);
  }
  showTickers() {
    this.showTicker = true;
  }
  // Handles when a user enters a ticker symbol
  onSubmit() {
    // Acceptable characters for input
    const letters = /^[A-Za-z]+$/;

    if (this.tickerSymbol.valueOf().match(letters)) {
      if (this.tickerSymbol.length < 6 && this.tickerSymbol.length > 2) {
        // Log to Console for Testing
        console.log('SENDING TICKER: ' + this.tickerSymbol + ' TO BACKEND!!');
        console.log('VVVV IGNORE THIS ERROR **Cannot read property \'symbol\' of undefined** IGNORE THIS ERROR VVVV')
        this.errorMessage = '';
        // Ensure each entry displays the proper info for each ticker
        this.selectedTicker = 'Displaying Stock Data for: ' + this.tickerSymbol.toUpperCase();
        // Find data relevant only to the users specified ticker
        this.sendTicker(this.tickerSymbol); // POST ticker to backend
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
