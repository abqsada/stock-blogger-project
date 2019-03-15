import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-testroutes',
  templateUrl: './testroutes.component.html',
  styleUrls: ['./testroutes.component.css']
})
export class TestroutesComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    console.log('Entered testroutes.component.ts ngOnInit method!');
  }

}
