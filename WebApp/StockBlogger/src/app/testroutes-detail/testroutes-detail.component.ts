import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-testroutes-detail',
  templateUrl: './testroutes-detail.component.html',
  styleUrls: ['./testroutes-detail.component.css']
})
export class TestroutesDetailComponent implements OnInit {

  constructor(private router: Router) { }

// Handles Clicking Admin Button
clickHandler() {
  const confirm = prompt('What is your favorite color?');

  if (confirm === 'Black' || 'black') {
    this.router.navigate(['adminsonly']);
  }
}

// Handles navigation to the next page
nextHandler() {
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
// Handles navigation to the previous page
prevHandler() {
  this.router.navigate(['account']);
}

  ngOnInit() {
    console.log('Entered testroutes-detail.component.ts ngOnInit method!')
  }
}
