import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import {NbButtonModule, NbLayoutModule, NbThemeModule} from '@nebular/theme';
import {NbEvaIconsModule} from '@nebular/eva-icons';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {RouterModule} from '@angular/router';
import {StartMenuComponent} from './components/start/start_menu.component';
import {SignupComponent} from './components/signup/signup.component';
import {MainMenuComponent} from './components/main_menu/main_menu.component';
import {LoginComponent} from './components/login/login.component';
import {RankingComponent} from './components/ranking/ranking.component';
import {OptionsComponent} from './components/options/options.component';
import {GameSearchComponent} from './components/game_search/game_search.component';

@NgModule({
  declarations: [
    AppComponent,
    StartMenuComponent,
    MainMenuComponent,
    SignupComponent,
    LoginComponent,
    RankingComponent,
    OptionsComponent,
    GameSearchComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    NbThemeModule.forRoot({name: 'cosmic'}),
    NbLayoutModule,
    NbEvaIconsModule,
    NbButtonModule,
    RouterModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
