import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './options.component.html',
  styleUrls: ['./options.component.scss']

})

export class OptionsComponent implements OnInit{
  title = 'app';

  constructor(private router: Router) {}

  ngOnInit(): void {
  }

  goBack(): void {
    this.router.navigate([`mainMenu`]);
  }

  deleteAcc(): void {
  }

  updatePass(): void {
  }
}
