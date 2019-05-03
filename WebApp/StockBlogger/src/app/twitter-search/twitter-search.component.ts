import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RestService } from '../rest.service';

@Component({
  selector: 'app-twitter-search',
  templateUrl: './twitter-search.component.html',
  styleUrls: ['./twitter-search.component.css']
})
export class TwitterSearchComponent implements OnInit {

  // Inject HttpClient and RestService into the constructor
  constructor(private http: HttpClient,
              private rest: RestService) { }

  ngOnInit() {
    console.log("Entered twitter-search component.ts")
    this.rest.getHashtags();
  }

  sortHashtags(hashTags: any){
    
  }

}
