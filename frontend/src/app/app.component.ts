import {Component, OnInit} from '@angular/core';
import {WebSocketService} from './web-socket.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'frontend';

  constructor(private webSocketService: WebSocketService) {}

  ngOnInit(): void {
    this.webSocketService.listen('test').subscribe((data) => {
      console.log(data);
    });
  }
}
