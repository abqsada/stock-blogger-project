import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-ticker-search',
  templateUrl: './ticker-search.component.html',
  styleUrls: ['./ticker-search.component.css']
})


export class TickerSearchComponent implements OnInit {
  // Handles the ticker the user searches for
  tickerSymbol: string;

  constructor() { }

  ngOnInit() {
    console.log('Entered ticker-search.component.ts');
  }

  onSubmit() {
    console.log('SENDING TICKER: ' + this.tickerSymbol);
    return this.tickerSymbol;
  }

}
