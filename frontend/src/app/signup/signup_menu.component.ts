import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-signup',
  templateUrl: './signup_menu.component.html',
  styleUrls: ['./signup_menu.component.scss']

})

export class SignupMenuComponent implements OnInit{
  title = 'app';
  buttonName = '';

  constructor() {}

  ngOnInit(): void {
    this.buttonName = 'BOOM';
  }
}
