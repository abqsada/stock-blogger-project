import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

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

  constructor(private http: HttpClient) { }
  // Runs on Initialization
  ngOnInit() {
    console.log('Entered ticker-search.component.ts');
    console.log('vvvv IGNORE THIS ERROR vvvv');
    this.getTickers(); // Gets all available tickers on initialization
  }
  // Gets all ticker data from API
  getTickers() {  // This URL likely needs to be changed, just boilerplate right now
    this.http.get('http://localhost:3000/api/ticker').subscribe(response => {
      this.values = response;
      console.log(this.values);
  }, error => {
    console.log('* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *');
    console.log('* * * * * * Please make sure all necessary servers are up and running properly! * * * * * *');
    console.log('* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *');
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
