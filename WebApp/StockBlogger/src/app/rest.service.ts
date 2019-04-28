import { Injectable, OnInit } from '@angular/core';

import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class RestService implements OnInit {

  // Declare variables to be used in account methods
  private userId: number;
  private userName: String;
  private dateJoined: Date;
  private password: String;
  // Declare variables to be used in blog posting methods
  private postId: number;
	private title: String;
	private body: String;
	private postDate: Date;
  
  ngOnInit(): void {
    throw new Error("Method not implemented.");
  }
  // BASE URL *Does NOT include URL parameters*
  // such as /blogposts, /twitter, or /accounts
  readonly baseUrl = 'http://localhost:3000';

  public User (userId:number,userName:String,dateJoined:Date,password:String) {
		this.userId = userId;
		this.userName = userName;
    this.password = password;
    this. dateJoined = dateJoined;
  }

  // Not sure if needed
  //public editUser(userName:String,dateJoined:Date,password:String){
    //this.userName = userName;
    //th is.dateJoined = dateJoined;
    //this.password = password;
  //}
  
  //Begin user account methods
  public getUserName(): String{
    return this.userName;
  }

  public getPassword(): String{
    return this.password;
  }

  public getUserId(): number{
    return this.userId;
  }

  public getDateJoined(){
    return this.dateJoined;
  }
  // Begin blog post methods
  public Post (userId: number,title:String,body:String, postDate:Date){
    this.userId = userId;
		this.title = title;
		this.body = body;
    this.postDate = postDate;
  }

  // Not sure if needed
  //public editPost (userId:number,title:String,body:String,postDate:Date){
    //this.userId = userId;
		//this.title = title;
		//this.body = body;
		//this.postDate = postDate;
  //}

  public getTitle(): String{
    return this.title;
  }

  public setTitle(title: String): void{
    this.title = title;
  }

  public getBody(): String{
    return this.body;
  }

  public setBody(body: String){
    this.body = body;
  }

  public getPostDate(): Date{
    return this.postDate;
  }

  public setPostDate(postDate: Date){
    this.postDate = postDate;
  }

  public getPostId(): number{
    return this.postId;
  }

//   readonly httpOptions = {
//   headers: new HttpHeaders({
//     'Content-Type':  'application/json'
//   })
// };

  // Inject the Http Client into the constructor
  constructor(private http: HttpClient) { 

  // private extractData(res: Response) {
  //   let body = res;
  //   return body || { };
  // }
}}