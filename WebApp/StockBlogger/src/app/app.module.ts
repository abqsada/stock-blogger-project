import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './account/register/register.component';
import { LoginComponent } from './account/login/login.component';
import { ResetpasswordComponent } from './account/resetpassword/resetpassword.component';
import { HomeComponent } from './home/home.component';
import { AccountComponent } from './account/account.component';
import { ErrorComponent } from './error/error.component';
import { AdminComponent } from './admin/admin.component';
import { SecretComponent } from './secret/secret.component';
import { NavComponent } from './nav/nav.component';
import { ChartComponent } from './chart/chart.component';
import { AboutComponent } from './about/about.component';
import { FeedComponent } from './feed/feed.component';
import { ContactComponent } from './contact/contact.component';
import { RegisterpageComponent } from './registerpage/registerpage.component';
import { CKEditorModule } from '@ckeditor/ckeditor5-angular';
import { ContributeComponent } from './contribute/contribute.component';
import { TickerSearchComponent } from './ticker-search/ticker-search.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RegisterComponent,
    LoginComponent,
    ResetpasswordComponent,
    AccountComponent,
    ErrorComponent,
    AdminComponent,
    SecretComponent,
    NavComponent,
    ChartComponent,
    AboutComponent,
    FeedComponent,
    ContactComponent,
    RegisterpageComponent,
    ContributeComponent,
    TickerSearchComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    AppRoutingModule,
    CKEditorModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
