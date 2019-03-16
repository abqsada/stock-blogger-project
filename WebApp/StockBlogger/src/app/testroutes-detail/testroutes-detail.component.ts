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
  this.router.navigate(['adminsonly']);
}
// Handles navigation to the previous page
prevHandler() {
  this.router.navigate(['account']);
}

  ngOnInit() {
    console.log('Entered testroutes-detail.component.ts ngOnInit method!')
  }
}
