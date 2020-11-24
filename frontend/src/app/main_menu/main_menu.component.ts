import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './main_menu.component.html',
  styleUrls: ['./main_menu.component.scss']

})

export class MainMenuComponent implements OnInit {
  title = 'app';
  buttonName = '';

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.buttonName = 'BOOM';
  }

  goToMainMenu(): void {
    this.router.navigate([`signup`]);
  }
}
