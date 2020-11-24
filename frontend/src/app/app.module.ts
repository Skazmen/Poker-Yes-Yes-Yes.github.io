import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import {NbButtonModule, NbLayoutModule, NbThemeModule} from '@nebular/theme';
import {NbEvaIconsModule} from '@nebular/eva-icons';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {RouterModule} from '@angular/router';
import {StartMenuComponent} from './components/start/start_menu.component';
import {SignupMenuComponent} from './components/signup/signup_menu.component';
import {MainMenuComponent} from './components/main_menu/main_menu.component';
import {LoginMenuComponent} from './components/login/login_menu.component';

@NgModule({
  declarations: [
    AppComponent,
    StartMenuComponent,
    SignupMenuComponent,
    MainMenuComponent,
    LoginMenuComponent
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
