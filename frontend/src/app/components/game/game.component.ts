import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']

})

export class GameComponent implements OnInit {
  get time(): any {
    return this._time;
  }

  set time(value: any) {
    this._time = value;
  }

  get cash(): any {
    return this._cash;
  }

  set cash(value: any) {
    this._cash = value;
  }
  title = 'app';
  private _user = 'User';
  private _time: any;
  private _cash: any;

  get user(): string {
    return this._user;
  }

  set user(value: string) {
    this._user = value;
  }

  constructor(private router: Router) {}

  ngOnInit(): void {
    this._time = 15;
    this.cash = 10000;
  }

  goHome(): void {
    this.router.navigate([``]);
  }

  goRanks(): void {
    this.router.navigate([`ranks`]);
  }

  goOptions(): void {
    this.router.navigate([`options`]);
  }

  goSearch(): void {
    this.router.navigate([`search`]);
  }

  goLobby(): void {
    this.router.navigate([`mLobby`]);
  }

  betPoker(): void {
    this._time += 1;
  }

  checkPoker(): void {
    this._time += 1;
  }

  foldPoker(): void {
    this._time += 1;
  }

  callPoker(): void {
    this._time += 1;
  }

  raisePoker(): void {
    this._time += 1;
  }
}
