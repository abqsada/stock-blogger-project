import { Component, OnInit } from '@angular/core';
import { AngularFirestore } from 'angularfire2/firestore';

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
