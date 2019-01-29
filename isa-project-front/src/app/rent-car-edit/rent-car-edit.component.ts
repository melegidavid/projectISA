import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';

@Component({
  selector: 'app-rent-car-edit',
  templateUrl: './rent-car-edit.component.html',
  styleUrls: ['./rent-car-edit.component.css']
})
export class RentCarEditComponent implements OnInit {

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
