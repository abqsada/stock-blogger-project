import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './account/register/register.component';
import { LoginComponent } from './account/login/login.component';
import { ResetpasswordComponent } from './account/resetpassword/resetpassword.component';
import { HomeComponent } from './home/home.component';
import { TestroutesComponent } from './testroutes/testroutes.component';
import { TestroutesDetailComponent } from './testroutes-detail/testroutes-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RegisterComponent,
    LoginComponent,
    ResetpasswordComponent,
    TestroutesComponent,
    TestroutesDetailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
