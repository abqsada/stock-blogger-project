import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {

  constructor(private router: Router) { }

// Handles navigation to the next page
nextHandler() {
  this.router.navigate(['contact']);
}
// Handles navigation to the previous page
prevHandler() {
  this.router.navigate(['account']);
}

promptHandler() {
  const confirm = prompt('NONE SHALL PASS!');

  if(confirm === 'fleshwound' || 'password' || '1234567890') {
    this.router.navigate(['admin']);
  }
}
  ngOnInit() {
    console.log('Entered about.component.ts ngOnInit method!')
  }
}