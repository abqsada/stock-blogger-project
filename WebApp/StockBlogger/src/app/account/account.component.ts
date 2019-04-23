import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  constructor(private router: Router) { }

  // Handles Clicking Admin Button
  clickHandler() {
    this.router.navigate(['login']);
  }
  // Handles navigation to the next page
  nextHandler() {
    this.router.navigate(['about']);
  }
  // Handles navigation to the previous page
  prevHandler() {
    this.router.navigate(['contribute']);
  }
  // Program Entry Point
  ngOnInit() {
    console.log('Entered account.component.ts ngOnInit method!');
  }
}
