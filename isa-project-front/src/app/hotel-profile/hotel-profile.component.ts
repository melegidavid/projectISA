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
import { HotelReport } from '../dto/hotel-report';
import { DateRange } from '../dto/date-range';
import * as $ from 'jquery';
import { Address } from '../dto/address.model';
import { DomSanitizer, SafeUrl } from '../../../node_modules/@angular/platform-browser';
import { AvioFlight } from '../dto/avio-flight.model';
import { Authority } from '../dto/authority.model';
import { AuthorityDTO } from '../dto/authorityDTO.model';



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
  maxGuestsNumber: number;
  startPrice: number;
  endPrice: number;

  role: Authority = new Authority();
  roles: Authority[] = [];
  autoDTO: AuthorityDTO = new AuthorityDTO();


  user: boolean = false;
  adminHotel: boolean = false;
  adminRent: boolean = false;


  hotel: Hotel;
  room: HotelRoom;
  rooms: HotelRoom[] = [];
  selectedRooms: HotelRoom[] = [];
  menu: HotelMenuItem[] = [];
  avgRatingHotel: number;

  report: HotelReport;
  showReport: boolean;
  dateRange: DateRange;
  startDateMy: Date;
  endDateMy: Date;

  private sub: any;

  showAdminControls: boolean;

  id: number;
  len: number;
  username: string;

  searchClicked: boolean = false;

  addressUrl: string;
  trustedUrl: SafeUrl;

  flight: AvioFlight;
  alreadyExistingHotel: Hotel;


  constructor(
    private hotelService: HotelsService,
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService,
    private sanitizer: DomSanitizer) {

    this.router = router;
  }

  ngOnInit() {
    this.len = localStorage.length;
    this.avgRatingHotel = 1;
    this.showReport = false;
    this.dateRange = new DateRange();
    this.startDateMy = new Date();
    this.endDateMy = new Date();

    this.username = localStorage.getItem('username');


    if (this.username) {
      this.getRoles(this.username).subscribe(data => {
        this.autoDTO = data;
        this.roles = this.autoDTO.authorities;

        if (this.roles[0].authority === 'HOTEL_ADMIN') {
          this.user = false;
          this.adminHotel = true;
          this.adminRent = false;
        } else if (this.roles[0].authority === 'RENT_CAR_ADMIN') {
          this.user = false;
          this.adminHotel = false;
          this.adminRent = true;
        } else if (this.roles[0].authority === 'REGISTERED_USER') {
          this.user = true;
          this.adminHotel = false;
          this.adminRent = false;
        }
      });
    }
    this.maxGuestsNumber = JSON.parse(localStorage.getItem('numberOfTravelers'));
    this.flight = JSON.parse(localStorage.getItem('flight'));
    this.alreadyExistingHotel = JSON.parse(localStorage.getItem('hotel'));


    let x = localStorage.getItem('role');
    if (x == undefined || x == null || x != "HOTEL_ADMIN") {
      this.showAdminControls = false;
    } else {
      this.showAdminControls = true;
    }

    this.sub = this.route.params.subscribe(params => { //uzimanje parametara iz url-a
      this.id = + params['id'];
    });

    this.hotelService.getHotelAvgRating(this.id).subscribe(data => {
      this.avgRatingHotel = data;
    });

    this.getHotel(this.id).subscribe(data => {
      if (data != undefined) {
        this.hotel = data;
        this.makeAddressUrl(this.hotel.addressDTO);
        this.trustedUrl = this.sanitizer.bypassSecurityTrustResourceUrl(this.addressUrl);
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

  public makeAddressUrl(address: Address) {
    this.addressUrl = 'https://maps.google.com/maps?q=';
    let grad = address.city.split(" ");
    let ulica = address.street.split(" ");

    for (let i = 0; i < grad.length; i++) {
      if (i == 0) {
        this.addressUrl += grad[i];
      } else {
        this.addressUrl += '%20' + grad[i];
      }
    }

    for (let i = 0; i < ulica.length; i++) {
      this.addressUrl += '%20' + ulica[i];
    }
    this.addressUrl += '%20' + address.number;
    this.addressUrl += '&t=&z=13&ie=UTF8&iwloc=&output=embed';
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
        localStorage.setItem('hotel', JSON.stringify(this.hotel));
      });

      localStorage.setItem('rooms', JSON.stringify(this.selectedRooms));
      localStorage.setItem('guestsNumber', JSON.stringify(this.guestsNumber));
      this.router.navigate(['hotels', this.id, 'menu_reservation']);

    }
  }

  public next() {
    this.router.navigate(['rentACar']);
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
    this.roles = [];
    this.userService.logOut();
    this.adminHotel = false;
    this.adminRent = false;
    this.user = false;
    this.router.navigate['home'];
  }

  generateReport() {

    this.dateRange = new DateRange();
    this.dateRange.startDate = this.startDateMy;
    this.dateRange.endDate = this.endDateMy;

    this.userService.generateReportHotel(this.id, this.dateRange).subscribe(data => {
      this.report = data;
      this.showReport = true;
    });


  }

  public getRoles(username: string): Observable<AuthorityDTO> {
    return this.userService.getRoles(username);
  }

}
