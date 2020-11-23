import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SignupMenuComponent} from './signup/signup_menu.component';


const routes: Routes = [
  {path: 'signup', component: SignupMenuComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
