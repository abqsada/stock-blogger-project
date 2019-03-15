import { Component, OnInit } from '@angular/core';

@Component({ // Sprinkles in some Angular Magic
  // Most imortantly, allows us to bind typescript data to the HTML template
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{

  clicked = false;

  ngOnInit(): void {
    console.log('Entered home.component.ts ngOnInit method!');
  }
}
