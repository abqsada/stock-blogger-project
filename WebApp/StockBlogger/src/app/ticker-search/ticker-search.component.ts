import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-ticker-search',
  templateUrl: './ticker-search.component.html',
  styleUrls: ['./ticker-search.component.css']
})


export class TickerSearchComponent implements OnInit {
  // Handles the ticker the user searches for
  tickerSymbol: string;
  errorMessage: string;
  selectedTicker: string;
  tickerData: string; // Probably will have to be an array or observable

  constructor() { }
  // Runs on Initialization
  ngOnInit() {
    console.log('Entered ticker-search.component.ts');
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
        // Returns the users response as a ticker symbol
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
