import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-start',
  templateUrl: './start_menu.component.html',
  styleUrls: ['./start_menu.component.scss']

})

export class StartMenuComponent implements OnInit {
  title = 'app';

  constructor(private router: Router) {}

  ngOnInit(): void {
  }

  // tslint:disable-next-line:typedef
  goToSignUp(): void {
      this.router.navigate([`signup`]);
  }

  goToLogin(): void {
    this.router.navigate([`login`]);
  }
}
