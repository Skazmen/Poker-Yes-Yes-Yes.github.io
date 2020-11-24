import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './ranking.component.html',
  styleUrls: ['./ranking.component.scss']

})

export class RankingComponent implements OnInit{
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
