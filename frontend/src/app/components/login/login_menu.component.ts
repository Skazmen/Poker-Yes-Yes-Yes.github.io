import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './login_menu.component.html',
  styleUrls: ['./login_menu.component.scss']

})

export class LoginMenuComponent implements OnInit{
  title = 'app';

  constructor(private router: Router) {}

  ngOnInit(): void {
  }

  goToMainMenu(): void {
    this.router.navigate([`mainMenu`]);
  }

  goBack(): void {
    this.router.navigate([``]);
  }
}
