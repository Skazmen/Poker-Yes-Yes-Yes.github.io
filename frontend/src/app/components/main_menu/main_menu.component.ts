import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './main_menu.component.html',
  styleUrls: ['./main_menu.component.scss']

})

export class MainMenuComponent implements OnInit {
  get user(): string {
    return this._user;
  }

  set user(value: string) {
    this._user = value;
  }
  title = 'app';
  private _user = 'User';

  constructor(private router: Router) {}

  ngOnInit(): void {
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
}
