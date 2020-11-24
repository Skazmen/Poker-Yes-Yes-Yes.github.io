import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SignupComponent } from './components/signup/signup.component';
import {MainMenuComponent} from './components/main_menu/main_menu.component';
import {StartMenuComponent} from './components/start/start_menu.component';
import {LoginComponent} from './components/login/login.component';
import {RankingComponent} from './components/ranking/ranking.component';


const routes: Routes = [
  {path: 'signup', component: SignupComponent},
  {path: 'login', component: LoginComponent},
  {path: 'mainMenu', component: MainMenuComponent},
  {path: 'ranks', component: RankingComponent},
  {path: '', component: StartMenuComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
