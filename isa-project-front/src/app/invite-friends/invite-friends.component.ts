import { Component, OnInit } from '@angular/core';
import { UserDTO } from '../dto/user.model';
import { UserService } from '../user.service';
import { Observable } from '../../../node_modules/rxjs';
import { AvioFlight } from '../dto/avio-flight.model';
import { AvioCompaniesService } from '../all-avio-companies/avio-companies.service';
import { AvioFlightReservation } from '../dto/avio-flight-reservation';
import { AvioFlightSeat } from '../dto/avio-flight-seat.model';
import { Router } from '../../../node_modules/@angular/router';

@Component({
  selector: 'app-invite-friends',
  templateUrl: './invite-friends.component.html',
  styleUrls: ['./invite-friends.component.css']
})
export class InviteFriendsComponent implements OnInit {

  friends: UserDTO[] = [];
  username: string;
  user: UserDTO;

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

  public getUser(username: string): Observable<UserDTO> {
    return this.userService.getUserByUsername(username);
  }

  public getFriends(id: number): Observable<UserDTO[]> {
    return this.userService.getFriendsOfUser(id);
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
