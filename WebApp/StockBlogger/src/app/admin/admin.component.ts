import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor(private router: Router) { }

// Handles Clicking Admin Button
clickHandler() {
  this.router.navigate(['/']);
}

// Handles navigation to the next page
// nextHandler() {
//   this.router.navigate(['adminsonly']);
//   }
// Handles navigation to the previous page
prevHandler() {
  this.router.navigate(['details']);
  }

  ngOnInit() {
  }

}
