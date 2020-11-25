import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './master_lobby.component.html',
  styleUrls: ['./master_lobby.component.scss']

})

export class MasterLobbyComponent implements OnInit{
  get player8(): string {
    return this._player8;
  }

  set player8(value: string) {
    this._player8 = value;
  }
  get player7(): string {
    return this._player7;
  }

  set player7(value: string) {
    this._player7 = value;
  }
  get player6(): string {
    return this._player6;
  }

  set player6(value: string) {
    this._player6 = value;
  }
  get player5(): string {
    return this._player5;
  }

  set player5(value: string) {
    this._player5 = value;
  }
  get player4(): string {
    return this._player4;
  }

  set player4(value: string) {
    this._player4 = value;
  }
  get player3(): string {
    return this._player3;
  }

  set player3(value: string) {
    this._player3 = value;
  }
  get player2(): string {
    return this._player2;
  }

  set player2(value: string) {
    this._player2 = value;
  }
  title = 'app';
  player1 = 'Ja';
  private _player2 = 'Brak';
  private _player3 = 'Brak';
  private _player4 = 'Brak';
  private _player5 = 'Brak';
  private _player6 = 'Brak';
  private _player7 = 'Brak';
  private _player8 = 'Brak';
  startMoney = 10000;
  bigDark = 2000;
  lilDark = 1000;
  tableId = 0;

  constructor(private router: Router) {}

  ngOnInit(): void {
  }

  goBack(): void {
    this.router.navigate([`mainMenu`]);
  }

  searchGame(): void {
  }

  searchGameRandom(): void {
  }
}
