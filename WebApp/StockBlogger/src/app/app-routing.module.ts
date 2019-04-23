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

const routes: Routes = [
  { path: '', component: HomeComponent} ,
  {
    path: 'about',
    component: AboutComponent,
  },
  {
    path: 'feed',
    component: FeedComponent
  },
  {
    path: 'contribute',
    component: ContributeComponent
  },
  {
    path: 'contact',
    component: ContactComponent
  },
  {
    path: 'account',
    component: AccountComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'registerpage',
    component: RegisterpageComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'reset',
    component: ResetpasswordComponent
  },
  {
    path: 'secret',
    component: SecretComponent
  },
  {
    path: 'adminsonly',
    component: AdminComponent,
    canActivate: [AdminGuard]
  },
  {
    path: '**',
    component: ErrorComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
