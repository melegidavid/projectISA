import { Component, OnInit } from '@angular/core';
import { HotelsService } from '../all-hotels/hotels.service';
import { Hotel } from '../dto/hotel.model';
import { Router, ActivatedRoute, ParamMap} from '@angular/router';
import { Observable } from '../../../node_modules/rxjs';
import { HotelRoom } from '../dto/hotel-room.model';
import { HotelMenuItem } from '../dto/hotel-menu-item.model';
import { UserService } from '../user.service';

@Component({
  selector: 'app-hotel-profile',
  templateUrl: './hotel-profile.component.html',
  styleUrls: ['./hotel-profile.component.css'],
  providers: [HotelsService]
})
export class HotelProfileComponent implements OnInit {

  hotel: Hotel;
  rooms: HotelRoom[] = [];
  menu : HotelMenuItem[] = [];
  private sub: any;
  id: number;
  len: number;
  username: string;

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
    console.log(localStorage);

    this.sub = this.route.params.subscribe(params => { //uzimanje parametara iz url-a
      this.id = + params['id'];
    });

    this.getHotel(this.id).subscribe(data => {
      if(data != undefined) {
        this.hotel = data;
      }
    });

    this.getHotelRooms(this.id).subscribe(data => {
      if(data != undefined) {
        this.rooms = data;
      }
    });

    this.getHotelMenu(this.id).subscribe(data => {
      if(data != undefined) {
        this.menu = data;
      }
    });

    console.log(this.hotel); //ISPISUJE UNDEFINED, ZASTO???
  }

  public getHotel(id: number | string) : Observable<Hotel> {
    return this.hotelService.getHotel(id);
  }

  public getHotelRooms(id: number | string) : Observable<HotelRoom[]> {
    return this.hotelService.getHotelRooms(id);
  }

  public getHotelMenu(id: number | string) : Observable<HotelMenuItem[]> {
    return this.hotelService.getHotelMenu(id);
  }

  logOut() {
    console.log('usao u logout');
    this.userService.logOut();
    
    console.log('ostalo ' + localStorage.length);
    this.ngOnInit();
  }

}
