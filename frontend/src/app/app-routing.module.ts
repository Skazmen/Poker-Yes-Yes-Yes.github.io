import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SignupMenuComponent } from './signup/signup_menu.component';
import {MainMenuComponent} from './main_menu/main_menu.component';


const routes: Routes = [
  {path: 'signup', component: SignupMenuComponent},
  {path: 'mainMenu', component: MainMenuComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
