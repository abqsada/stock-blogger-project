import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';



@Injectable({
  providedIn: 'root'
})
export class RestService implements OnInit {

  // Declare the account object with variables to be used in account methods
  private accountObject: {
    userId: number;
    userName: String;
    dateJoined: Date;
    password: String;
  };
  private incomingAccount: any;
  // Declare variables to be used in blog posting methods
  private blogObject: {
    postId: number;
    title: String;
    body: String;
    postDate: Date;
  };
  // BASE URL *Does NOT include URL parameters*
  // such as /blogposts, /twitter, or /accounts
  readonly baseUrl = 'http://localhost:3000';
  values: any;

  // Inject the Http Client into the constructor
  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  // Gets all ticker data from API
  getTickers() {  // This URL likely needs to be changed, just boilerplate right now
    this.http.get(this.baseUrl + '/api/ticker').subscribe(response => {
      this.values = response;
      console.log(this.values);
  }, error => {
    console.log('* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *');
    console.log('* * * * * * Please make sure all necessary servers are up and running properly! * * * * * *');
    console.log('* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *');
    });
  }

  public User(userId: number, userName: String, dateJoined: Date, password: String) {
    this.accountObject.userId = userId;
    this.accountObject.userName = userName;
    this.accountObject.password = password;
    this.accountObject.dateJoined = dateJoined;
  }

  // Not sure if needed
  // public editUser(userName:String,dateJoined:Date,password:String){
  // this.userName = userName;
  // th is.dateJoined = dateJoined;
  // this.password = password;
  // }

  // Begin user account methods
  public getAccount() { // Makes HTTP get request to snag account data from API

    this.http.get(this.baseUrl + '/api/account').subscribe(response => {
      this.incomingAccount = response;
      console.log(this.incomingAccount);
  }, error => {
    console.log('* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *');
    console.log('* * * * * * Please make sure all necessary servers are up and running properly! * * * * * *');
    console.log('* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *');
    });
  }

  public getUserName(): String {
    console.log(this.accountObject.userName);
    return this.accountObject.userName;
  }

  public getPassword(): String {
    console.log(this.accountObject.password);
    return this.accountObject.password;
  }

  public getUserId(): number {
    console.log(this.accountObject.userId);
    return this.accountObject.userId;
  }

  public getDateJoined() {
    console.log(this.accountObject.dateJoined);
    return this.accountObject.dateJoined;
  }
  // Begin blog post methods
  public Post(userId: number, title: String, body: String, postDate: Date) {
    this.accountObject.userId = userId;
    this.blogObject.title = title;
    this.blogObject.body = body;
    this.blogObject.postDate = postDate;
  }

  // Not sure if needed
  // public editPost (userId:number,title:String,body:String,postDate:Date){
  // this.userId = userId;
  // this.title = title;
  // this.body = body;
  // this.postDate = postDate;
  // }

  public getTitle(): String {
    console.log(this.blogObject.title);
    return this.blogObject.title;
  }

  public setTitle(title: String): void {
    this.blogObject.title = title;
  }

  public getBody(): String {
    console.log(this.blogObject.body);
    return this.blogObject.body;
  }

  public setBody(body: String) {
    this.blogObject.body = body;
  }

  public getPostDate(): Date {
    console.log(this.blogObject.postDate);
    return this.blogObject.postDate;
  }

  public setPostDate(postDate: Date) {
    this.blogObject.postDate = postDate;
  }

  public getPostId(): number {
    console.log(this.blogObject.postId);
    return this.blogObject.postId;
  }

}
