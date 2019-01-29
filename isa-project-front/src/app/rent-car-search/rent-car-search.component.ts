import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';

@Component({
  selector: 'app-rent-car-search',
  templateUrl: './rent-car-search.component.html',
  styleUrls: ['./rent-car-search.component.css']
})
export class RentCarSearchComponent implements OnInit {

  len: number;
  username: string;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.len = localStorage.length;
    this.username = localStorage.getItem('username');
    console.log(localStorage);
  }

  logOut() {
    console.log('usao u logout');
    this.userService.logOut();
    
    console.log('ostalo ' + localStorage.length);
    this.ngOnInit();
  }

}
