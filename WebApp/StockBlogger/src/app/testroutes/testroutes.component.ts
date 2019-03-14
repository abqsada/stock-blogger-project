import { Component, OnInit } from '@angular/core';
import { AngularFirestore } from 'angularfire2/firestore';

@Component({
  selector: 'app-testroutes',
  templateUrl: './testroutes.component.html',
  styleUrls: ['./testroutes.component.css']
})
export class TestroutesComponent implements OnInit {
  testroutes$;
  // Inject firestore into the constructor
  constructor(private afs: AngularFirestore) { }

  ngOnInit() {
    // Reference test documents
    // Call value changes to listen to them as a real time
    // OBSERVABLE
    this.testroutes$ = this.afs.collection('testroutes').valueChanges();
  }

}
