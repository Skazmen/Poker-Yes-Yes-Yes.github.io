import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SignupMenuComponent } from './components/signup/signup_menu.component';
import {MainMenuComponent} from './components/main_menu/main_menu.component';
import {StartMenuComponent} from './components/start/start_menu.component';


const routes: Routes = [
  {path: 'signup', component: SignupMenuComponent},
  {path: 'mainMenu', component: MainMenuComponent},
  {path: '', component: StartMenuComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
