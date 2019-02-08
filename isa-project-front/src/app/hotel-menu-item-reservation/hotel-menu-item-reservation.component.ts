import { Component, OnInit } from '@angular/core';
import { HotelsService } from '../all-hotels/hotels.service';
import { UserService } from '../user.service';
import { ActivatedRoute, Router } from '../../../node_modules/@angular/router';
import { Hotel } from '../dto/hotel.model';
import { Observable } from '../../../node_modules/rxjs';
import { HotelMenuItem } from '../dto/hotel-menu-item.model';
import { HotelMenuItemReservation } from '../dto/hotel-menu-item-reservation.model';
import { Authority } from '../dto/authority.model';
import { AuthorityDTO } from '../dto/authorityDTO.model';

@Component({
  selector: 'app-hotel-menu-item-reservation',
  templateUrl: './hotel-menu-item-reservation.component.html',
  styleUrls: ['./hotel-menu-item-reservation.component.css']
})
export class HotelMenuItemReservationComponent implements OnInit {

  role: Authority = new Authority();
  roles: Authority[] = [];
  autoDTO: AuthorityDTO = new AuthorityDTO();


  user: boolean = false;
  adminHotel: boolean = false;
  adminRent: boolean = false;


  startDate: Date;
  endDate: Date;
  period: number;
  guestsNumber: number;

  private sub: any;
  id: number;
  len: number;
  username: string;

  hotel: Hotel;
  menu: HotelMenuItem[] = [];
  selectedItems: HotelMenuItem[] = [];

  constructor(
    private hotelsService: HotelsService,
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService
  ) { }

  ngOnInit() {
    this.len = localStorage.length;
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

    this.startDate = JSON.parse(localStorage.getItem('startDate'));
    this.endDate = JSON.parse(localStorage.getItem('endDate'));
    this.guestsNumber = JSON.parse(localStorage.getItem('guestsNumber'));

    this.endDate = new Date(this.endDate + 'T00:00:00+01:00');
    this.startDate = new Date(this.startDate + 'T00:00:00+01:00');
    let differnce = this.endDate.getTime() - this.startDate.getTime();
    let oneDay = 1000 * 60 * 60 * 24;
    this.period = Math.round(differnce / oneDay);

    this.startDate = JSON.parse(localStorage.getItem('startDate'));
    this.endDate = JSON.parse(localStorage.getItem('endDate'));

    this.sub = this.route.params.subscribe(params => { //uzimanje parametara iz url-a
      this.id = + params['id'];
    });

    this.getHotel(this.id).subscribe(data => {
      if (data != undefined) {
        this.hotel = data;
      }
    });

    this.getMenu(this.id).subscribe(data => {
      this.menu = data;
    });
  }

  public makeReservation() {
    this.selectedItems.forEach(item => {
      let itemReservation = new HotelMenuItemReservation();
      itemReservation.startReservation = this.startDate;
      itemReservation.endReservation = this.endDate;
      itemReservation.price = item.price * this.period;
      itemReservation.username = localStorage.getItem('username');
      itemReservation.item = item;

      this.hotelsService.makeItemReservation(item.id, itemReservation);
      this.router.navigate(['rentACar']);
    });
  }

  public next() {
    this.router.navigate(['rentACar']);
  }


  public selectItem(id: number | string) {
    this.menu.forEach(item => {
      if (item.id == id) {
        item.selected = true;
        this.selectedItems.push(item);
      }
    });
  }

  public removeItem(id: number | string) {
    let newMenu: HotelMenuItem[] = [];
    this.menu.forEach(item => {
      if (item.id == id) {
        item.selected = false;
      } else {
        if (item.selected == true) {
          newMenu.push(item);
        }
      }
    });
    this.selectedItems = newMenu;
  }

  public getHotel(id: number | string): Observable<Hotel> {
    return this.hotelsService.getHotel(id);
  }

  public getMenu(id: number | string): Observable<HotelMenuItem[]> {
    return this.hotelsService.getHotelMenu(id);
  }

  logOut() {
    this.roles = [];
    this.userService.logOut();
    this.adminHotel = false;
    this.adminRent = false;
    this.user = false;
    this.router.navigate['home'];
  }

  public getRoles(username: string): Observable<AuthorityDTO> {
    return this.userService.getRoles(username);
  }

}
