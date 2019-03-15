import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { TestroutesComponent } from './testroutes/testroutes.component';
import { TestroutesDetailComponent } from './testroutes-detail/testroutes-detail.component';
import { AccountComponent } from './account/account.component';
import { LoginComponent } from './account/login/login.component';
import { RegisterComponent } from './account/register/register.component';
import { ResetpasswordComponent } from './account/resetpassword/resetpassword.component';
import { ErrorComponent } from './error/error.component';
import { AdminGuard } from './admin.guard';
import { AdminComponent } from './admin/admin.component';
import { CanActivate } from '@angular/router/src/utils/preactivation';
import { SecretComponent } from './secret/secret.component';

const routes: Routes = [
  { path: '', component: HomeComponent} ,
  {
    path: 'testroutes',
    component: TestroutesComponent,
  },
  {
    path: 'details',
    component: TestroutesDetailComponent
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
    path: 'admin',
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
