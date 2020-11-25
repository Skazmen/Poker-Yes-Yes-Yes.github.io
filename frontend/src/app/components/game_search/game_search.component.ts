import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './game_search.component.html',
  styleUrls: ['./game_search.component.scss']

})

export class GameSearchComponent implements OnInit{
  title = 'app';

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
