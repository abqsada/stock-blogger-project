import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AccountComponent } from './account/account.component';
import { LoginComponent } from './account/login/login.component';
import { RegisterComponent } from './account/register/register.component';
import { ResetpasswordComponent } from './account/resetpassword/resetpassword.component';
import { ErrorComponent } from './error/error.component';
import { AdminGuard } from './admin.guard';
import { AdminComponent } from './admin/admin.component';
import { CanActivate } from '@angular/router/src/utils/preactivation';
import { SecretComponent } from './secret/secret.component';
import { AboutComponent } from './about/about.component';
import { FeedComponent } from './feed/feed.component';
import { ContactComponent } from './contact/contact.component';
import { RegisterpageComponent } from './registerpage/registerpage.component';
import { ContributeComponent } from './contribute/contribute.component';
// Handles the page navigation within the application
// each path specifies a URL Parameter, with a component that controls the logic(.ts),Markup(.html), and Styling(.css) of that path
const routes: Routes = [
  { path: '', component: HomeComponent} ,
  {
    path: 'about', // localhost:4200/about
    component: AboutComponent,
  },
  {
    path: 'feed', // localhost:4200/feed
    component: FeedComponent
  },
  {
    path: 'contribute', // localhost:4200/contribute
    component: ContributeComponent
  },
  {
    path: 'contact', // localhost:4200/contact
    component: ContactComponent
  },
  {
    path: 'account', // localhost:4200/account
    component: AccountComponent
  },
  {
    path: 'login', // localhost:4200/login
    component: LoginComponent
  },
  {
    path: 'registerpage', // localhost:4200/registerpage
    component: RegisterpageComponent
  },
  {
    path: 'register', // localhost:4200/register
    component: RegisterComponent
  },
  {
    path: 'reset', // localhost:4200/reset
    component: ResetpasswordComponent
  },
  {
    path: 'secret', // localhost:4200/secret
    component: SecretComponent
  },
  {
    path: 'adminsonly', // localhost:4200/adminsonly
    component: AdminComponent,
    canActivate: [AdminGuard]
  },
  { // Handles pages not specified
    path: '**',
    component: ErrorComponent
  }

];

@NgModule({ // Specifies we want ^these^ routes set in the Url Router
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule] // Makes the Router public
})
export class AppRoutingModule { }
