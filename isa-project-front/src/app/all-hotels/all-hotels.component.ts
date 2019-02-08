import { Component, OnInit } from '@angular/core';
import { Observable } from '../../../node_modules/rxjs';
import { HttpClient } from '@angular/common/http';
import { Hotel } from '../dto/hotel.model';
import { HotelsService } from './hotels.service';
import { UserService } from '../user.service';
import { RoomReservation } from '../dto/room-reservation.model';
import { RoomSearch } from '../dto/room-search.model';
import { AvioFlight } from '../dto/avio-flight.model';

@Component({
  selector: 'app-all-hotels',
  templateUrl: './all-hotels.component.html',
  styleUrls: ['./all-hotels.component.css'],
  providers: [HotelsService]
})

export class AllHotelsComponent implements OnInit {
  
  searchClicked: boolean = false;
  nameSearch: string;
  countrySearch: string;
  citySearch: string;
  roomsSearch: number;
  startDate: Date;
  endDate: Date;
 // roomReservation: RoomReservation;

  len: number;
  username: string;
  hotels : Hotel[] = [];
  hotel: Hotel;

  flight: AvioFlight;
  
  constructor(
    private http: HttpClient,
    private hotelsService: HotelsService,
    private userService: UserService) {
  }
  
  ngOnInit() {
    this.len = localStorage.length;
    this.username = localStorage.getItem('username');
    this.flight = JSON.parse(localStorage.getItem('flight'));
    if(this.flight == undefined) {
      localStorage.removeItem('startDate');
      localStorage.removeItem('endDate');
    } else {
      this.startDate = JSON.parse(localStorage.getItem('startDate'));
      this.countrySearch = this.flight.endLocation.country;
      this.citySearch = this.flight.endLocation.city;
    }
    // this.startDate = undefined;
    // this.endDate = undefined;
    this.getHotels().subscribe(data => {
      this.hotels = data;
    });
  }

  public search() {
    if(this.startDate >= this.endDate) {
      alert("You can't check out before you check in");
    } else if(this.startDate == undefined || this.endDate == undefined) {
      alert("Choose both check in and check out date");
    } else {
      let roomSearch = new RoomSearch();
      console.log("pocetni u searchu" + this.startDate);
      roomSearch.startDate = this.startDate;
      roomSearch.endDate = this.endDate;
      
      localStorage.setItem('startDate', JSON.stringify(this.startDate));
      localStorage.setItem('endDate', JSON.stringify(this.endDate));
      this.searchClicked = true;
      
      this.searchHotels(roomSearch).subscribe(data => {
        this.hotels = data;
      });
    }
  }

  public roomsReservation() {
        
  }

  public getHotels(): Observable<Hotel[]> {
    return this.hotelsService.getHotels();
  }

  public searchHotels(roomSearch: RoomSearch) : Observable<Hotel[]> {
    return this.hotelsService.searchHotels(roomSearch);
  }

  logOut() {
    console.log('usao u logout');
    this.userService.logOut();
    
    console.log('ostalo ' + localStorage.length);
    this.ngOnInit();
  }
}
