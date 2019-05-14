import { Component, OnInit } from '@angular/core';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RestService } from '../rest.service';


@Component({
  selector: 'app-contribute',
  templateUrl: './contribute.component.html',
  styleUrls: ['./contribute.component.css']
})
// Implements OnInit
export class ContributeComponent implements OnInit {

  // Import the RTE for creating posts
  public Editor = ClassicEditor;

  // Object created to handle the created blog post
  created: any = {};

   // Inject the router for navigation, HttpCLient for GET and POST, and RestService for created objects
  constructor(private router: Router,
              private http: HttpClient,
              private rest: RestService) { }

  // httpOptions decides which format can be received from GET and POST
  readonly httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })
  };

  // Method to handle when a post is created
  createPost(): void {
    console.log(this.created);
    this.created.sendPost();
  }

  // Create the POST to send the blog post to the back end url
  sendPost(body: any): any {
    return this.http.post(this.rest.postUrl, body, this.httpOptions);
  }

  ngOnInit(): void {
    console.log('Entered contribute.component.ts');
  }

  // Handles navigation to the next page
  nextHandler(): void {
    this.router.navigate(['account']);
  }
  // Handles navigation to the previous page
  prevHandler(): void {
    this.router.navigate(['feed']);
  }

}
