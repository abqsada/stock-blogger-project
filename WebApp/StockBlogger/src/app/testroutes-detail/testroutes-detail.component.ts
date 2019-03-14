import { Component, OnInit } from '@angular/core';
import { AngularFirestore } from 'angularfire2/firestore';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-testroutes-detail',
  templateUrl: './testroutes-detail.component.html',
  styleUrls: ['./testroutes-detail.component.css']
})
export class TestroutesDetailComponent implements OnInit {
  testroutes$;
  constructor(
    private afs: AngularFirestore,
    private route: ActivatedRoute
    ) { }
  ngOnInit() {
    // Uses activated route to listen to changes in the URL parameter (animal names)
    // Calls route.paramMap, retuns observable of route parameters
    this.testroutes$ = this.route.paramMap.pipe(
    // Use switchMap to listen to URL changes
      switchMap(params => {
        // Switch to an observable of the animal data from firestore
        const name = params.get('name'); // Matches 'name' in router config!!!
        // Returns the actual name of the animal which we can pass on to firestore to retrieve
        // as a single document
        return this.afs.doc('animals/' + name).valueChanges();
      })
    );
  }
}
