import { Component, OnInit } from '@angular/core';
import { UserDTO } from '../dto/user.model';
import { UserService } from '../user.service';
import { Observable } from '../../../node_modules/rxjs';
import { AvioFlight } from '../dto/avio-flight.model';
import { AvioCompaniesService } from '../all-avio-companies/avio-companies.service';
import { AvioFlightReservation } from '../dto/avio-flight-reservation';
import { AvioFlightSeat } from '../dto/avio-flight-seat.model';
import { Router } from '../../../node_modules/@angular/router';
import { Authority } from '../dto/authority.model';
import { AuthorityDTO } from '../dto/authorityDTO.model';

@Component({
  selector: 'app-invite-friends',
  templateUrl: './invite-friends.component.html',
  styleUrls: ['./invite-friends.component.css']
})
export class InviteFriendsComponent implements OnInit {

  friends: UserDTO[] = [];
  username: string;
  user: UserDTO;

  role: Authority = new Authority();
  roles: Authority[] = [];
  autoDTO: AuthorityDTO = new AuthorityDTO();


  userFlag: boolean = false;
  adminHotel: boolean = false;
  adminRent: boolean = false;


  selectedFriends: UserDTO[] = [];

  numberOfTravelers: number;

  numberOfFriends: number;

  flight: AvioFlight;
  reservation: AvioFlightReservation = new AvioFlightReservation();
  i: number;

  seats: AvioFlightSeat[] = [];

  constructor(private userService: UserService,
    private avioService: AvioCompaniesService,
    private router: Router) { }

  ngOnInit() {
    this.username = localStorage.getItem('username');

    if (this.username) {
      this.getRoles(this.username).subscribe(data => {
        this.autoDTO = data;
        this.roles = this.autoDTO.authorities;

        if (this.roles[0].authority === 'HOTEL_ADMIN') {
          this.userFlag = false;
          this.adminHotel = true;
          this.adminRent = false;
        } else if (this.roles[0].authority === 'RENT_CAR_ADMIN') {
          this.userFlag = false;
          this.adminHotel = false;
          this.adminRent = true;
        } else if (this.roles[0].authority === 'REGISTERED_USER') {
          this.userFlag = true;
          this.adminHotel = false;
          this.adminRent = false;
        }
      });
    }


    this.flight = JSON.parse(localStorage.getItem('flight'));
    this.seats = JSON.parse(localStorage.getItem('selectedSeats'));
    this.getUser(this.username).subscribe(data => {
      this.user = data;
      this.getFriends(this.user.id).subscribe(data => {
        this.friends = data;
      });
    });

    this.numberOfTravelers = JSON.parse(localStorage.getItem('numberOfTravelers'));
    this.numberOfFriends = this.numberOfTravelers - 1;
  }

  next() {
    this.inviteFriends(this.user.id, this.flight.id, this.selectedFriends).subscribe(data => {

      for (this.i = 0; this.i < this.seats.length - 1; this.i++) {
        this.reservation.avioFlight = this.flight;
        this.reservation.user = this.selectedFriends[this.i];
        this.reservation.seat = this.seats[this.i];
        this.reservation.flightRating = -1;
        this.reservation.companyRating = -1;
        this.reservation.isDeleted = false;

        this.makeReservation(this.reservation).subscribe(data => {
        });
      }
      this.reservation.user = this.user;
      this.reservation.seat = this.seats[this.i];
      this.reservation.flightRating = -1;
      this.reservation.companyRating = -1;
      this.reservation.isDeleted = false;

      this.makeReservation(this.reservation).subscribe(data => {

      });

      this.router.navigate(['hotels']);

    });
  }

  logOut() {
    this.roles = [];
    this.userService.logOut();
    this.adminHotel = false;
    this.adminRent = false;
    this.userFlag = false;
    this.router.navigate['home'];
  }


  public getUser(username: string): Observable<UserDTO> {
    return this.userService.getUserByUsername(username);
  }

  public getFriends(id: number): Observable<UserDTO[]> {
    return this.userService.getFriendsOfUser(id);
  }

  public getRoles(username: string): Observable<AuthorityDTO> {
    return this.userService.getRoles(username);
  }

  public inviteFriend(friend: UserDTO) {
    if (friend.selected == undefined) {
      friend.selected = true;
      this.selectedFriends.push(friend);
    }
  }

  public removeFriend(friend: UserDTO) {
    if (friend.selected == true) {
      friend.selected = undefined;
      let newFriends: UserDTO[] = [];
      this.selectedFriends.forEach(f => {
        if (f.id != friend.id) {
          newFriends.push(f);
        }
      });
      this.selectedFriends = newFriends;
    }
  }

  public inviteFriends(id: number, idFlight: number, selectedFriends: UserDTO[]): Observable<any> {
    return this.userService.inviteFriends(id, idFlight, selectedFriends);
  }

  public makeReservation(reservation: AvioFlightReservation): Observable<any> {
    return this.avioService.makeReservation(reservation);
  }
}
