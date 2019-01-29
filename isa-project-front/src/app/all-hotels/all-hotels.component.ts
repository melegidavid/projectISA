import { Component, OnInit } from '@angular/core';
import { Observable } from '../../../node_modules/rxjs';
import { HttpClient } from '@angular/common/http';
import { Hotel } from '../dto/hotel.model';
import { HotelsService } from './hotels.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-all-hotels',
  templateUrl: './all-hotels.component.html',
  styleUrls: ['./all-hotels.component.css'],
  providers: [HotelsService]
})

export class AllHotelsComponent implements OnInit {
  
  len: number;
  username: string;
  hotels : Hotel[] = [];
  hotel: Hotel;
  
  constructor(private http: HttpClient, private hotelsService: HotelsService,private userService: UserService) {
  }
  
  ngOnInit() {
    this.len = localStorage.length;
    this.username = localStorage.getItem('username');
    console.log(localStorage);

    this.getHotels().subscribe(data => {
      this.hotels = data;
    });
  }

  public getHotels(): Observable<Hotel[]> {
    return this.hotelsService.getHotels();
  }

  logOut() {
    console.log('usao u logout');
    this.userService.logOut();
    
    console.log('ostalo ' + localStorage.length);
    this.ngOnInit();
  }
}
