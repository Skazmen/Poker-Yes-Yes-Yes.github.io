import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup_menu.component.html',
  styleUrls: ['./signup_menu.component.scss']

})

export class SignupMenuComponent implements OnInit{
  title = 'app';

  constructor(private router: Router) {}

  ngOnInit(): void {
  }

  goToMainMenu(): void {
    this.router.navigate([`mainMenu`]);
  }
}
