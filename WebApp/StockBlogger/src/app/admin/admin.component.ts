import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
// Implements OnInit
export class AdminComponent implements OnInit {
  // Inject the Router
  constructor(private router: Router) { }

// Handles Clicking Admin Button
clickHandler(): void {
  this.router.navigate(['/']);
}

// Handles navigation to the next page
nextHandler(): void {
  this.router.navigate(['/']);
  }
// Handles navigation to the previous page
prevHandler(): void {
  this.router.navigate(['details']);
  }

  ngOnInit(): void {
    console.log('Entered admin.component.ts');
  }
}

