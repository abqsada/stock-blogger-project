import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  constructor(private router: Router) { }

// Handles navigation to the next page
nextHandler() {
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
// Handles navigation to the previous page
prevHandler() {
  this.router.navigate(['account']);
}

promptHandler() {
  const confirm = prompt('NONE SHALL PASS!');

  if (confirm === 'fleshwound' || 'password' || '1234567890') {
    this.router.navigate(['admin']);
  }
}

  ngOnInit() {
    console.log('Entered contact.component.ts ngOnInit method!');
  }
}
