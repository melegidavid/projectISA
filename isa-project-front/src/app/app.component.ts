import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserService } from './user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  userLogged : boolean = false;

  constructor(private http: HttpClient, private userService: UserService) { }

  ngOnInit() {
  }

  logOut() {
    console.log('usao u logout');
    this.userService.logOut();
    console.log('ostalo ' + localStorage.length)
  }

}
