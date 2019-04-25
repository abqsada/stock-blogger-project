import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})
export class FeedComponent implements OnInit {
  // Handles incoming feed
  values: any;

  // create private Router variable 'router' to use the Router in this component
  constructor(private router: Router, private http: HttpClient) { }
  // Runs on init
  ngOnInit() {
    console.log('Entered testroutes.component.ts ngOnInit method!');
    console.log('vvvv IGNORE THIS ERROR vvvv');
    this.getFeed(); // Gets the feed on initialization
  }
  // Gets blog feed from API
  getFeed() {
    this.http.get('http://localhost:3000/api/feed').subscribe(response => {
      this.values = response;
  }, error => {
    console.log(error);
    });
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
