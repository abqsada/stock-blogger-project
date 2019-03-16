import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-testroutes',
  templateUrl: './testroutes.component.html',
  styleUrls: ['./testroutes.component.css']
})
export class TestroutesComponent implements OnInit {

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
    this.router.navigate(['account']);
  }
  // Handles navigation to the previous page
  prevHandler() {
    this.router.navigate(['']);
  }

  ngOnInit() {
    console.log('Entered testroutes.component.ts ngOnInit method!');
  }

}
