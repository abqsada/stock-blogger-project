import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { TestroutesComponent } from './testroutes/testroutes.component';
import { TestroutesDetailComponent } from './testroutes-detail/testroutes-detail.component';

const routes: Routes = [
  { path: '', component: HomeComponent} ,
  {
    path: 'testroutes',
    component: TestroutesComponent,
    children: [
      { path: '', component: TestroutesDetailComponent} ,
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
