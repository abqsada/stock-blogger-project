import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-testroutes-detail',
  templateUrl: './testroutes-detail.component.html',
  styleUrls: ['./testroutes-detail.component.css']
})
export class TestroutesDetailComponent implements OnInit {

  constructor() { }
  ngOnInit() {
    console.log('Entered testroutes-detail.component.ts ngOnInit method!')
  }
}
