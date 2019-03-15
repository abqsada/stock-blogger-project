import { Component, OnInit, Output, EventEmitter } from '@angular/core';


@Component({ // Sprinkles in some Angular Magic
  // Most imortantly, allows us to bind typescript data to the HTML template
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  count = 0;
  clicked = false;

  // @Output() messageEvent = new EventEmitter<boolean>();
  ngOnInit(): void {
    console.log('Entered home.component.ts ngOnInit method!');
  }
  onClick() {

    this.count++;
    console.log(this.count);
    if (this.count === 10) {
      this.clicked = true;
    }
    // this.messageEvent.emit(this.clicked);
    console.log(this.clicked);
    console.log('Entered home.component.ts onClick method!');
  }
}
