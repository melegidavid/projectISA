import { Component, OnInit } from '@angular/core';
import { AvioFlightSeat } from '../dto/avio-flight-seat.model';
import * as $ from 'jquery';
import { Observable } from '../../../node_modules/rxjs';
import { AvioCompaniesService } from '../all-avio-companies/avio-companies.service';
import { ActivatedRoute, Router } from '../../../node_modules/@angular/router';
import { UserService } from '../user.service';
import { UserDTO } from '../dto/user.model';
import { getLocaleFirstDayOfWeek } from '@angular/common';
import { Authority } from '../dto/authority.model';
import { AuthorityDTO } from '../dto/authorityDTO.model';

@Component({
  selector: 'app-avio-flight-seats',
  templateUrl: './avio-flight-seats.component.html',
  styleUrls: ['./avio-flight-seats.component.css']
})
export class AvioFlightSeatsComponent implements OnInit {

  id: number;
  username: string;

  role: Authority = new Authority();
  roles: Authority[] = [];
  autoDTO: AuthorityDTO = new AuthorityDTO();


  user: boolean = false;
  adminHotel: boolean = false;
  adminRent: boolean = false;


  sub: any;
  classOfFlight: string;
  numberOfTravelers: number;

  seats: AvioFlightSeat[] = [];
  selectedSeats: AvioFlightSeat[] = [];

  numOfRows: number;
  numOfColumns: number = 6;

  clickedCounter = 0;

  constructor(
    private avioCompaniesService: AvioCompaniesService,
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.username = localStorage.getItem('username');
    this.classOfFlight = localStorage.getItem('fligthClass');
    this.numberOfTravelers = JSON.parse(localStorage.getItem('numberOfTravelers'));


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



    this.sub = this.route.params.subscribe(params => { //uzimanje parametara iz url-a
      this.id = + params['id'];
    });

    this.getAvioFlightSeats(this.id).subscribe(data => {
      this.seats = data;
      this.numOfRows = this.seats.length / this.numOfColumns;
    });


  }

  setSeats() {
    localStorage.setItem("selectedSeats", JSON.stringify(this.selectedSeats));
  }

  logOut() {
    this.roles = [];
    this.userService.logOut();
    this.adminHotel = false;
    this.adminRent = false;
    this.user = false;
    this.router.navigate['home'];
  }


  public getAvioFlightSeats(id: number | string): Observable<AvioFlightSeat[]> {
    return this.avioCompaniesService.getAvioFlightSeats(id);
  }

  public selectSeat(s: AvioFlightSeat) {
    if (s.clicked == undefined) {
      if (this.clickedCounter >= this.numberOfTravelers) {
        alert("You already select " + this.numberOfTravelers + " seats");
      } else {
        s.clicked = true;
        this.selectedSeats.push(s);
        this.clickedCounter++;
      }
    } else {
      s.clicked = undefined;
      let newSeats: AvioFlightSeat[] = [];
      this.selectedSeats.forEach(seat => {
        if (seat.id != s.id) {
          newSeats.push(seat);
        }
      });
      this.selectedSeats = newSeats;
      this.clickedCounter--;
    }
  }

  public getRoles(username: string): Observable<AuthorityDTO> {
    return this.userService.getRoles(username);
  }

}
