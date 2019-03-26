import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';


@Component({ // Sprinkles in some Angular Magic
  // Most imortantly, allows us to bind typescript data to the HTML template
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  count = 0; // For onClick
  clicked = false; // For onClick
  // create private Router variable 'router' to use the Router in this component
  constructor(private router: Router) { }

  // @Output() messageEvent = new EventEmitter<boolean>();
  ngOnInit(): void {
    console.log('Entered home.component.ts ngOnInit method!');
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
  // Handles the next button
  nextHandler() {
    // Navigates to the right
    this.router.navigate(['feed']);
  }
  // Handles the previous button
  prevHandler() {
    // Navigates to the left
    const confirm = prompt('What is your favorite color?');
    switch (confirm) {
      case 'secret':
      this.router.navigate(['secret']);
      break;
      case '':
      break;
      case 'blue':
      this.router.navigate(['']); // go home
      break;
      case 'Black':
      this.router.navigate(['adminsonly']);
      break;
      case 'black':
      this.router.navigate(['adminsonly']);
      break;
    }
  }
  // Handles the Click Me button
  onClick() {
    // Increment 'count' each click
    this.count++;
    console.log(this.count);
    // After 10  clicks...
    if (this.count === 10) {
      this.clicked = true; // Disable button
      this.router.navigate(['secret']); // Route to secret navigation page
    }
    // this.messageEvent.emit(this.clicked);
    console.log(this.clicked); // Log each click to the console
    console.log('Entered home.component.ts onClick method!'); // For testing
  }
}
