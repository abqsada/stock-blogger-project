// Angualr Magic
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
// ALL REQUIRED COMPONENTS WE HAVE CREATED
import { RegisterComponent } from './account/register/register.component';
import { LoginComponent } from './account/login/login.component';
import { ResetpasswordComponent } from './account/resetpassword/resetpassword.component';
import { HomeComponent } from './home/home.component';
import { AccountComponent } from './account/account.component';
import { ErrorComponent } from './error/error.component';
import { AdminComponent } from './admin/admin.component';
import { SecretComponent } from './secret/secret.component';
import { NavComponent } from './nav/nav.component';
import { AboutComponent } from './about/about.component';
import { FeedComponent } from './feed/feed.component';
import { ContactComponent } from './contact/contact.component';
import { RegisterpageComponent } from './registerpage/registerpage.component';
import { ContributeComponent } from './contribute/contribute.component';
import { TickerSearchComponent } from './ticker-search/ticker-search.component';
import { TwitterSearchComponent } from './twitter-search/twitter-search.component';
// 3RD Party Imports
import { CKEditorModule } from '@ckeditor/ckeditor5-angular';
import { DownloaderComponent } from './downloader/downloader.component';

// This is the Angular Project's entry point
// Basically the 'meeting place' for all components (classes)
@NgModule({ // Root App Module
  declarations: [ // Components added here via Angular CLI
    AppComponent, // WebApp Starting Point
    HomeComponent,
    RegisterComponent,
    LoginComponent,
    ResetpasswordComponent,
    AccountComponent,
    ErrorComponent,
    AdminComponent,
    SecretComponent,
    NavComponent,
    AboutComponent,
    FeedComponent,
    ContactComponent,
    RegisterpageComponent,
    ContributeComponent,
    TickerSearchComponent,
    DownloaderComponent,
    TwitterSearchComponent
  ],
  imports: [ // Modules added here via Angular CLI
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    AppRoutingModule,
    CKEditorModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
