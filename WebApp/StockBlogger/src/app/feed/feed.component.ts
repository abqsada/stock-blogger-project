import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { RestService } from '../rest.service';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})

export class FeedComponent implements OnInit {
  // Inject the Angular Router
  constructor(private router: Router) { }

  // Runs on init
  ngOnInit() {
    console.log('Entered testroutes.component.ts ngOnInit method!');
    console.log('vvvv IGNORE THIS ERROR vvvv');
    console.log('Testing!');
  }

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
