import { Component, OnInit } from '@angular/core';
import { HotelsService } from '../all-hotels/hotels.service';
import { Hotel } from '../dto/hotel.model';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { Observable } from '../../../node_modules/rxjs';
import { HotelRoom } from '../dto/hotel-room.model';
import { HotelMenuItem } from '../dto/hotel-menu-item.model';
import { UserService } from '../user.service';
import { RoomSearch } from '../dto/room-search.model';
import { RoomReservation } from '../dto/room-reservation.model';
import * as $ from 'jquery';


@Component({
  selector: 'app-hotel-profile',
  templateUrl: './hotel-profile.component.html',
  styleUrls: ['./hotel-profile.component.css'],
  providers: [HotelsService]
})
export class HotelProfileComponent implements OnInit {

  period: any;
  startDate: Date;
  endDate: Date;

  roomsNumber: number;
  guestsNumber: number;
  startPrice: number;
  endPrice: number;

  hotel: Hotel;
  room: HotelRoom;
  rooms: HotelRoom[] = [];
  selectedRooms: HotelRoom[] = [];
  menu: HotelMenuItem[] = [];

  private sub: any;
  id: number;
  len: number;
  username: string;

  searchClicked: boolean = false;

  constructor(
    private hotelService: HotelsService,
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService) {

    this.router = router;
  }

  ngOnInit() {
    this.len = localStorage.length;
    this.username = localStorage.getItem('username');

    this.sub = this.route.params.subscribe(params => { //uzimanje parametara iz url-a
      this.id = + params['id'];
    });

    this.getHotel(this.id).subscribe(data => {
      if (data != undefined) {
        this.hotel = data;
      }
    });

    if (JSON.parse(localStorage.getItem('startDate')) == undefined) {
      this.getHotelRooms(this.id).subscribe(data => {
        if (data != undefined) {
          this.rooms = data;
        }
      });
    } else {
      this.startDate = JSON.parse(localStorage.getItem('startDate'));
      this.endDate = JSON.parse(localStorage.getItem('endDate'));

      this.endDate = new Date(this.endDate + 'T00:00:00+01:00');
      this.startDate = new Date(this.startDate + 'T00:00:00+01:00');
      let differnce = this.endDate.getTime() - this.startDate.getTime();
      let oneDay = 1000 * 60 * 60 * 24;
      this.period = Math.round(differnce / oneDay);

      this.startDate = JSON.parse(localStorage.getItem('startDate'));
      this.endDate = JSON.parse(localStorage.getItem('endDate'));


      //this.period = this.endDate - this.startDate;
      let roomSearch = new RoomSearch();
      roomSearch.startDate = JSON.parse(localStorage.getItem('startDate'));
      roomSearch.endDate = JSON.parse(localStorage.getItem('endDate'));

      this.getFreeHotelRooms(this.id, roomSearch)
        .subscribe(data => {
          this.rooms = data;
        });
    }

    this.getHotelMenu(this.id).subscribe(data => {
      if (data != undefined) {
        this.menu = data;
      }
    });
  }

  public search() {
    let roomSearch = new RoomSearch();
    roomSearch.startDate = JSON.parse(localStorage.getItem('startDate'));
    roomSearch.endDate = JSON.parse(localStorage.getItem('endDate'));
    roomSearch.guestsNumber = this.guestsNumber;
    roomSearch.roomsNumber = this.roomsNumber;
    roomSearch.startPrice = this.startPrice;
    roomSearch.endPrice = this.endPrice;

    this.searchClicked = true;
    this.searchRooms(this.id, roomSearch)
      .subscribe(data => {
        this.rooms = data;
      });
  }

  public makeReservation() {
    let bedsSum: number = 0;
    this.selectedRooms.forEach(room => {
      bedsSum += room.bedNumber;
    });
    //ako je vise kreveta rezrvisano nego sto ima gostiju nemoj mu dati da rezervise.
    if (bedsSum > this.guestsNumber) {
      alert("You can't reserve more rooms and beds than you specified");
    } else {
      //ako je dobro selektovao
      this.selectedRooms.forEach(room => {
        let roomReservation = new RoomReservation();
        roomReservation.startReservation = this.startDate;
        roomReservation.endReservation = this.endDate;
        roomReservation.username = localStorage.getItem('username');
        roomReservation.price = this.period * room.price;

        this.hotelService.makeReservation(room.id, roomReservation);
      });
      localStorage.setItem('guestsNumber', JSON.stringify(this.guestsNumber));
      this.router.navigate(['hotels', this.id, 'menu_reservation']);

    }
  }

  public next() {
    this.router.navigate(['rentACars']);
  }

  //selektuje sobu i ubaci je u listu selektovanih soba
  public selectRoom(id: number | string) {
    this.rooms.forEach(room => {
      if (room.id == id) {
        room.free = false;
        this.selectedRooms.push(room);
      }
    });
  }

  public removeRoom(id: number | string) {
    let newRooms: HotelRoom[] = [];
    this.rooms.forEach((room) => {
      if (room.id == id) {
        room.free = true;
      } else {
        if (room.free == false) {
          newRooms.push(room);
        }
      }
    });
    this.selectedRooms = newRooms;
  }

  public getHotel(id: number | string): Observable<Hotel> {
    return this.hotelService.getHotel(id);
  }

  public getHotelRooms(id: number | string): Observable<HotelRoom[]> {
    return this.hotelService.getHotelRooms(id);
  }

  public getHotelRoom(id: number | string): Observable<HotelRoom> {
    return this.hotelService.getHotelRoom(id);
  }

  public getFreeHotelRooms(id: number, roomSearch: RoomSearch): Observable<HotelRoom[]> {
    return this.hotelService.getFreeRooms(id, roomSearch);
  }

  public getHotelMenu(id: number | string): Observable<HotelMenuItem[]> {
    return this.hotelService.getHotelMenu(id);
  }

  public searchRooms(id: number, roomSearch: RoomSearch): Observable<HotelRoom[]> {
    return this.hotelService.searchRooms(id, roomSearch);
  }

  logOut() {
    console.log('usao u logout');
    this.userService.logOut();

    console.log('ostalo ' + localStorage.length);
    this.ngOnInit();
  }

}
