import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RestService } from '../rest.service';

@Component({
  selector: 'app-twitter-search',
  templateUrl: './twitter-search.component.html',
  styleUrls: ['./twitter-search.component.css']
})
export class TwitterSearchComponent implements OnInit {
  // Handles the hashtag term that the user will search for
  hashTagName: string;
  // Handles the hashtag that is selected
  selectedHashtag: string;
  // Twitter data to be sorted through
  tValues: any;
  // Inject HttpClient and RestService into the constructor
  constructor(private http: HttpClient,
              private rest: RestService) { }

  ngOnInit() {
    console.log('Entered twitter-search component.ts');
    this.rest.getHashtags();
  }

  // Sort through twitter API data for relevant match to user entered search
  sortHashtags(hashTags: any){
  // Same thing will need to happen here as the ticker-search component
  // Grab the twitter information relating to a specific hashtag
}
// Used when a user enters a keyword for twitter
onSubmit(){
  console.log("Sending Twitter data: " + this.hashTagName);
  this.selectedHashtag = ("Displaying selected hashtag and volume of tweets for: " +
  this.hashTagName);

  // Just like the ticker-search componenet, only display what was asked for
  this.sortHashtags(this.hashTagName);
  return this.hashTagName;
}

}