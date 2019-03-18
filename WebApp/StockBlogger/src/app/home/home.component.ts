import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';


@Component({ // Sprinkles in some Angular Magic
  // Most imortantly, allows us to bind typescript data to the HTML template
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  count = 0;
  clicked = false;

  constructor(private router: Router) { }

  // @Output() messageEvent = new EventEmitter<boolean>();
  ngOnInit(): void {
    console.log('Entered home.component.ts ngOnInit method!');
  }
  // Handles Clicking Admin Button
  clickHandler() {
    const confirm = prompt('What is your favorite color?');
    if (confirm === 'Black' || 'black') {
      // routes to Admin page if Black/black is entered
      this.router.navigate(['adminsonly']);
    }
  }

  // Handles the next button
  nextHandler() {
    // Navigates to the right
    this.router.navigate(['testroutes']);
  }
  // Handles the previous button
  prevHandler() {
    // Navigates to the left
    const confirm = prompt('What is your favorite color?');
    if (confirm === 'Black' || 'black') {
      // routes to Admin page if Black/black is entered
      this.router.navigate(['adminsonly']);
    }
  }
  // Handles the Click Me button
  onClick() {
    this.count++;
    console.log(this.count);
    if (this.count === 10) {
      this.clicked = true;
    }
    // this.messageEvent.emit(this.clicked);
    console.log(this.clicked);
    console.log('Entered home.component.ts onClick method!');
  }
}
