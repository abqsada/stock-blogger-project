import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http'; // Import the HTTPClient for GET/POST requests

@Injectable({
  providedIn: 'root'
})
export class RestService implements OnInit {
// Objects
  // Declare the account object with variables to be used in account methods
  private accountObject: {
    userId: number;
    userName: String;
    dateJoined: Date;
    password: String;
  };
  // Declare objects for incoming account and twitter data
  private incomingAccount: any;
  // Declare the blog object with variables to be used in blog posting methods
  private blogObject: {
    postId: number;
    title: String;
    body: String;
    postDate: Date;
  };
  // Declare the ticker object with variables to be used in displaying ticker data
  public tickerObject: {
    symbol: String;
    name: String;
    currency: any;
    price: number;
    priceOpen: number;
    dayLow: number;
    dayHigh: number;
    volume: number;
    stockNameLong: String;
    timezone: String;
    lastTrade: any;
  };
  // Declare the twitter object with variables to be used in displaying twitter data
  public twitterObject: {
    twitterUser: String;
    hashTag: String;
    volume: any
  };
  // baseURL's for each API back end functions
  readonly tickerUrl: any = 'http://localhost:3000';
  readonly twitterUrl: any =  'http://localhost:3001';
  readonly userUrl: any = 'http://localhost:8888?command=getuser&userName=Silly&password=oldBear';
  readonly addUserUrl: any = 'http://localhost:8888?command=adduser&userName=Same&password=oldThing&dateJoined=2019-01-30';
  readonly postUrl: any = 'http://localhost:8888?command=addpost&userId=1&title=TheseWords&body=ShouldSplit&postDate=2019-02-07';
  readonly testTickers: any = 'assets/testData.json';
  readonly testHashTags: any = 'assets/testTwitter.txt';
  // Temporary Data
  hashTags: any;
  values: any; // Handles ALL incoming Raw JSON data
  tickers: any; // Handles the tickers array
  ticker: any; // Handles individual ticker objects
  first: any;
  second: any;
  third: any;
  fourth: any;
  fifth: any;

  // Inject the Http Client into the constructor
  constructor(private http: HttpClient) {}

  ngOnInit() {
  }
  public getTickerObject(): any {
    return this.tickerObject;
  }

  // Gets all ticker data from API and assigns the tickers to local variables
  public getTickers(): void {  // Through our HTTP Client, make a get request to our tickerUrl (asks the backend nicely)
    // 'Subscribe' to the response we get back from the backend. Subscribing opens a data stream to our values variable
    // This allows us to not only use the incoming data, but store it and share it with other classes.
    // Currently getting test data!
    this.http.get(/*this.tickerUrl*/ this.testTickers ).subscribe(response => {
      this.values = response; // Example: {symbols_requested: 3, symbols_returned: 2, data: Array(2)}
      this.tickers = response['data']; // Get tickers object
      this.first = response['data'][0]; // Get the first ticker object
      this.second = response['data'][1]; // Get the second ticker object
      this.third = response['data'][2]; // Get the third ticker object
      this.fourth = response['data'][3]; // Get the fourth ticker object
      this.fifth = response['data'][4]; // Get the fifth ticker object
      console.log('Logging entire JSON Object to console for testing: ');
      console.log(this.values); // Log the whole data JSON Object to the console
      console.log('Logging all arrays of ticker data to console for testing: ');
      console.log(this.tickers); // Log the ticker objects to the console
      console.log('Logging each array of ticker data to console for testing: ');
      console.log(this.first);
      console.log(this.second);
      console.log(this.third);
      console.log(this.fourth);
      console.log(this.fifth);

  }, error => { // Handles any thrown errors by the HTTP Client
    this.serverNotConnected(); // Log the error to the console
    });
  }

  //  Http Get request for the generated twitter data from the twitter API
  public getHashtags() { // The twitter api is currently designed to post to the localhost port 3001
    this.http.get(this.twitterUrl).subscribe((res) => {
      this.hashTags = res;
      console.log(this.hashTags);
      console.log('Incoming Hashtags: ');
      console.log(this.hashTags);
    }, error => {
    this.serverNotConnected();
    });
 }

  // Begin user account methods
  public getAccount() { // Makes HTTP get request to snag account data from API
    console.log('Getting Account!');
    this.http.get(this.userUrl).subscribe(response => {
      this.incomingAccount = response;
      console.log(this.incomingAccount);
  }, error => {
    this.serverNotConnected();
    });
  }

  public User(userId: number, userName: String, dateJoined: Date, password: String) {
    this.accountObject.userId = userId;
    this.accountObject.userName = userName;
    this.accountObject.password = password;
    this.accountObject.dateJoined = dateJoined;
  }

  public getUserName(): String {
    console.log(this.accountObject.userName);
    return this.accountObject.userName;
  }

  public setUserName(userName: String){
    this.accountObject.userName = userName;
  }

  public getPassword(): String {
    console.log(this.accountObject.password);
    return this.accountObject.password;
  }

  public setPassword(password: String) {
    this.accountObject.password = password;
  }

  public getUserId(): number {
    console.log(this.accountObject.userId);
    return this.accountObject.userId;
  }

  public setUserId(userId: number) {
    this.accountObject.userId = userId;
  }

  public getDateJoined() {
    console.log(this.accountObject.dateJoined);
    return this.accountObject.dateJoined;
  }

  public setdateJoined(dateJoined: Date) {
    this.accountObject.dateJoined = dateJoined;
  }

  // Begin blog post methods
  public Post(userId: number, title: String, body: String, postDate: Date) {
    this.accountObject.userId = userId;
    this.blogObject.title = title;
    this.blogObject.body = body;
    this.blogObject.postDate = postDate;
  }

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

  public setPostId(postId: number){
    this.blogObject.postId = postId;
  }
  // Begin ticker methods
  public assignTicker(symbol: String, name: String, currency: String, price: number) {
    this.ticker['symbol'] = symbol;
    this.tickerObject.name = name;
    this.tickerObject.currency = currency;
    this.tickerObject.price = price;
  } 

  public getSymbol(): String {
    console.log(this.tickerObject.symbol);
    return this.tickerObject.symbol;
  }

  public setSymbol(symbol: String) {
    this.tickerObject.symbol = symbol
  }

  public getName(): String {
    console.log(this.tickerObject.name);
    return this.tickerObject.name;
  }

  public setName(name: String) {
    this.tickerObject.name = name;
  }

  public getCurrency(): String {
    console.log(this.tickerObject.currency);
    return this.tickerObject.currency;
  }

  public setCurrency(currency: String) {
    this.tickerObject.currency = currency;
  }

  public getPrice(): number {
    console.log(this.tickerObject.price);
    return this.tickerObject.price;
  }

  public setPrice(price: number) {
    this.tickerObject.price = price;
  }

  //Begin twitter methods
  public Twitter(twitterUser: String, hashTag: String, volume: number) {
    this.twitterObject.twitterUser = twitterUser;
    this.twitterObject.hashTag = hashTag;
    this.twitterObject.volume = volume;
  }
  
  public getTwitterUser(): String {
    console.log(this.twitterObject.twitterUser);
    return this.twitterObject.twitterUser;
  }

  public setTwitterUser(twitterUser: String) {
    this.twitterObject.twitterUser = twitterUser;
  }

  public getHashTag(): String {
    console.log(this.twitterObject.hashTag);
    return this.twitterObject.hashTag;
  }

  public setHashTag(hashTag: String) {
    this.twitterObject.hashTag = hashTag;
  }

  public getVolume(): number {
    console.log(this.twitterObject.volume);
    return this.twitterObject.volume;
  }

  public setVolume(volume: number) {
    this.twitterObject.volume = volume;
  }

  public serverNotConnected() {
    console.log('* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *');
    console.log('* * * * * * Please make sure all necessary servers are up and running properly! * * * * * *');
    console.log('* * * * * * * * * * * * * Refresh Page To Retry Server Connection * * * * * * * * * * * * *');
    console.log('* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *');
  }

}

// Define a *public* interface with the correct shape for HTTP Headers
export interface Config {
  body: Config;
  headers: any;
  testData: string;
  testTwitter: string;
}
