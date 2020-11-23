import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-start',
  templateUrl: './start_menu.component.html',
  styleUrls: ['./start_menu.component.scss']

})

export class StartMenuComponent implements OnInit {
  title = 'app';
  buttonName = '';

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.buttonName = 'BOOM';
  }

  // tslint:disable-next-line:typedef
  goToSignUp() {
    if (this.buttonName === 'BOOM') {
      this.buttonName = 'BANG';
      this.router.navigateByUrl(`signup`);
    } else {
      this.buttonName = 'BOOM';
    }
  }
}
