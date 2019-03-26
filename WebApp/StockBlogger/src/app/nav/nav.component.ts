import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
// Handles the navbar at the top of the application
// Persistent: Stays at the top regardless of scroll position or page navigation
export class NavComponent implements OnInit {

  constructor(private router: Router) { }
  account = ['DEV', 'GUEST', 'USER']; // DEV ACCOUNT FOR TESTING
  route; // Stores current URL Route

  ngOnInit() {
    console.log('Entering nav.component.ts ngOnInit method!')
  }
  // Keeps user on same page if incorrect answer is made
  handleRoute() {
    this.route = this.router.url;
    console.log(this.route);
    if (this.route === '/') {
      this.route = '';
    }
  }
  // Assigns route var to current route
  setCurrentRoute() {
    this.route = this.router.url;
  }
  // Gets current route
  getRoute() {
    return this.route;
  }
  // Handles clicking admin nav buttons
  adminClick() {
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
      default:
      this.router.navigate([this.route]); // wrong answers stay on CURRENT page
    }
  }
}
