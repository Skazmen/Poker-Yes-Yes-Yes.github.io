import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']

})

export class LoginComponent implements OnInit{
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
