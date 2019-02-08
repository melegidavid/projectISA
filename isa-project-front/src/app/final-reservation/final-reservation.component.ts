import { Component, OnInit } from '@angular/core';
import { AvioFlight } from '../dto/avio-flight.model';
import { Vehicle } from '../dto/vehicle.model';
import { HotelRoom } from '../dto/hotel-room.model';
import { Router } from '../../../node_modules/@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-final-reservation',
  templateUrl: './final-reservation.component.html',
  styleUrls: ['./final-reservation.component.css']
})
export class FinalReservationComponent implements OnInit {

  flight: AvioFlight;
  vehicle: Vehicle;
  rooms: HotelRoom[] = [];

  username: string;

  constructor(
    private router: Router,
    private userService: UserService
  ) { }

  ngOnInit() {
    this.username = localStorage.getItem('username');
    this.flight = JSON.parse(localStorage.getItem('flight'));
    this.rooms = JSON.parse(localStorage.getItem('rooms'));
    this.vehicle = JSON.parse(localStorage.getItem('vehicle'));
    this.flight.avioCompany.name;
    this.flight.startLocation.city
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
    this.userService.logOut();
    this.ngOnInit();
  }

}
