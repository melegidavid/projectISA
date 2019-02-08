import { Component, OnInit } from '@angular/core';
import { AvioFlight } from '../dto/avio-flight.model';
import { Vehicle } from '../dto/vehicle.model';
import { HotelRoom } from '../dto/hotel-room.model';
import { Router } from '../../../node_modules/@angular/router';
import { UserService } from '../user.service';
import { Authority } from '../dto/authority.model';
import { AuthorityDTO } from '../dto/authorityDTO.model';
import { Observable } from 'rxjs';
import { Hotel } from '../dto/hotel.model';

@Component({
  selector: 'app-final-reservation',
  templateUrl: './final-reservation.component.html',
  styleUrls: ['./final-reservation.component.css']
})
export class FinalReservationComponent implements OnInit {

  flight: AvioFlight;
  vehicle: Vehicle;
  rooms: HotelRoom[] = [];
  hotel: Hotel;

  username: string;

  role: Authority = new Authority();
  roles: Authority[] = [];
  autoDTO: AuthorityDTO = new AuthorityDTO();


  user: boolean = false;
  adminHotel: boolean = false;
  adminRent: boolean = false;

  constructor(
    private router: Router,
    private userService: UserService
  ) { }

  ngOnInit() {
    this.username = localStorage.getItem('username');
  
    if(this.username){
      this.getRoles(this.username).subscribe(data=>{
        this.autoDTO = data;
        this.roles = this.autoDTO.authorities;

        if(this.roles[0].authority === 'HOTEL_ADMIN'){
          this.user = false;
          this.adminHotel = true;
          this.adminRent = false;
        }else if(this.roles[0].authority === 'RENT_CAR_ADMIN'){
          this.user = false;
          this.adminHotel = false;
          this.adminRent = true;   
        }else if(this.roles[0].authority === 'REGISTERED_USER'){
          this.user = true;
          this.adminHotel = false;
          this.adminRent = false;     
        }
      });
    }

    this.flight = JSON.parse(localStorage.getItem('flight'));
    this.rooms = JSON.parse(localStorage.getItem('rooms'));
    this.vehicle = JSON.parse(localStorage.getItem('vehicle'));
    this.hotel = JSON.parse(localStorage.getItem('hotel'));
  }

  public finishReservation() {
    localStorage.removeItem('flight');
    localStorage.removeItem('vehicle');
    localStorage.removeItem('rooms');
    localStorage.removeItem('guestsNumber');
    localStorage.removeItem('numberOfTravelers');
    localStorage.removeItem('selectedSeats');
    localStorage.removeItem('flightClass');
    localStorage.removeItem('endDate');
    localStorage.removeItem('startDate');

    this.router.navigate(['home']);
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
