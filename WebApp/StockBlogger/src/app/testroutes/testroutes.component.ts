import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-testroutes',
  templateUrl: './testroutes.component.html',
  styleUrls: ['./testroutes.component.css']
})
export class TestroutesComponent implements OnInit {
  // create private Router variable 'router' to use the Router in this component
  constructor(private router: Router) { }

  // Handles Clicking Admin Button
  clickHandler() {
    var ans;
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

  // Handles navigation to the next page
  nextHandler() {
    this.router.navigate(['account']);
  }
  // Handles navigation to the previous page
  prevHandler() {
    this.router.navigate(['']);
  }
  // Runs on init
  ngOnInit() {
    console.log('Entered testroutes.component.ts ngOnInit method!');
  }

}
