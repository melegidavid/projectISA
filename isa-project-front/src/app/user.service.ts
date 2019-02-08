import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from '../../node_modules/rxjs';
import { LoginRequest } from './dto/loginRequest.model';
import { map } from 'rxjs/operators';
//import 'rxjs/Rx';
import { TokenState } from './dto/tokenState.model';
import { UserDTO } from './dto/user.model';
import { AuthorityDTO } from './dto/authorityDTO.model';
import { VehicleReservationDTO } from './dto/vehicleReservationDTO';
import { RatingUpdateDTO } from './dto/ratingUpdate';
import { RoomReservation } from './dto/room-reservation.model';
import { AvioFlightReservation } from './dto/avio-flight-reservation';
import { DateRange } from './dto/date-range';
import { RentCarReport } from './dto/rent-car-report';
import { HotelReport } from './dto/hotel-report';
import { AvioReport } from './dto/avio-report';




@Injectable()
export class UserService {

  private baseUrl = 'http://localhost:9004/users';
  private headers = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) }

  constructor(private http: HttpClient) { }

  getUser(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  //temp metoda za uzimanje preko username 
  getUserByUsername(username: string): Observable<UserDTO> {
    return this.http.get<UserDTO>("http://localhost:9004/users/" + username, { responseType: 'json' });
  }

  //ovo ako skontamo kako preko id
  getFriendsOfUser(id: number): Observable<any> {
    return this.http.get("http://localhost:9004/users/" + id + "/friends", { responseType: 'json' });
  }

  //do tad koristimo username
  getFriendsOfUserByUsername(username: string): Observable<any> {
    return this.http.get("http://localhost:9004/users/" + username + "/friendsList", { responseType: 'json' });
  }

  addFriend(id: number, newFriend: UserDTO): Observable<any> {
    return this.http.post("http://localhost:9004/users/" + id + "/addFriend", newFriend, { responseType: 'json' });
  }


  removeFriend(id: number, idFriend: number): Observable<any> {
    return this.http.delete("http://localhost:9004/users/" + id + "/friends/" + idFriend, { responseType: 'text' });
  }

  cancelVehicleReservation(id: number): Observable<VehicleReservationDTO[]> {
    return this.http.get<VehicleReservationDTO[]>("http://localhost:9004/vehicle_reservations/" + id + "/cancelVehicleReservation");
  }

  cancelHotelReservation(id: number): Observable<RoomReservation[]> {
    console.log('usao u otkazivanje hotelskih rezervacija SERVIS');
    return this.http.get<RoomReservation[]>("http://localhost:9004/vehicle_reservations/" + id + "/cancelRoomReservation");
  }

  cancelAvioReservation(id: number): Observable<AvioFlightReservation[]> {
    console.log('usao u otkazivanje avio rezervacija SERVIS');
    return this.http.get<AvioFlightReservation[]>("http://localhost:9004/vehicle_reservations/" + id + "/cancelAvioReservation");
  }

  getVehicleReservations(username: string): Observable<VehicleReservationDTO[]> {
    return this.http.get<VehicleReservationDTO[]>("http://localhost:9004/vehicle_reservations/" + username);
  }

  getHotelReservations(username: string): Observable<RoomReservation[]> {
    return this.http.get<RoomReservation[]>("http://localhost:9004/users/room_reservations/" + username);
  }

  getAvioReservations(username: string): Observable<AvioFlightReservation[]> {
    return this.http.get<AvioFlightReservation[]>("http://localhost:9004/users/avio_reservations/" + username);
  }

  updateVehicleRating(idRes: number, newValue: number): Observable<VehicleReservationDTO[]> {
    let ratingDTO = new RatingUpdateDTO();
    ratingDTO.idReservation = idRes;
    ratingDTO.newValue = newValue;
    console.log('dada1');
    return this.http.post<VehicleReservationDTO[]>("http://localhost:9004/vehicle_reservations/updateVehicleRating/", ratingDTO);
  }


  updateHotelRating(idRes: number, newValue: number): Observable<RoomReservation[]> {
    let ratingDTO = new RatingUpdateDTO();
    ratingDTO.idReservation = idRes;
    ratingDTO.newValue = newValue;
    return this.http.post<RoomReservation[]>("http://localhost:9004/vehicle_reservations/updateHotelRating/", ratingDTO);
  }

  updateRoomRating(idRes: number, newValue: number): Observable<RoomReservation[]> {
    let ratingDTO = new RatingUpdateDTO();
    ratingDTO.idReservation = idRes;
    ratingDTO.newValue = newValue;
    return this.http.post<RoomReservation[]>("http://localhost:9004/vehicle_reservations/updateRoomRating", ratingDTO);

  }

  updateRentCarRating(idRes: number, newValue: number): Observable<VehicleReservationDTO[]> {
    let ratingDTO = new RatingUpdateDTO();
    ratingDTO.idReservation = idRes;
    ratingDTO.newValue = newValue;
    return this.http.post<VehicleReservationDTO[]>("http://localhost:9004/vehicle_reservations/updateRentCarRating", ratingDTO);

  }

  updateFlightRating(idRes: number, newValue: number): Observable<AvioFlightReservation[]> {
    let ratingDTO = new RatingUpdateDTO();
    ratingDTO.idReservation = idRes;
    ratingDTO.newValue = newValue;
    return this.http.post<AvioFlightReservation[]>("http://localhost:9004/vehicle_reservations/updateFlightRating", ratingDTO);

  }


  updateAvioRating(idRes: number, newValue: number): Observable<AvioFlightReservation[]> {
    let ratingDTO = new RatingUpdateDTO();
    ratingDTO.idReservation = idRes;
    ratingDTO.newValue = newValue;
    return this.http.post<AvioFlightReservation[]>("http://localhost:9004/vehicle_reservations/updateAvioRating", ratingDTO);

  }

  generateReport(id: number, range: DateRange): Observable<RentCarReport> {
    return this.http.post<RentCarReport>("http://localhost:9004/rent_a_cars/" + id + "/generate", range);
  }

  generateReportHotel(id: number, range: DateRange): Observable<HotelReport> {
    return this.http.post<HotelReport>("http://localhost:9004/hotels/" + id + "/generate", range);
  }

  generateReportAvio(id: number, range: DateRange): Observable<AvioReport> {
    return this.http.post<AvioReport>("http://localhost:9004/avio_companies/" + id + "/generate", range);
  }

  getUsersForSearch(username: string): Observable<any> {
    return this.http.get("http://localhost:9004/users/" + username + "/usersToSearch", { responseType: 'json' });
  }

  getUsersRequest(username: string): Observable<any> {
    return this.http.get("http://localhost:9004/users/" + username + "/listOfRequests", { responseType: 'json' });
  }

  acceptFriendship(id: number, idFriend: number): Observable<any> {
    return this.http.post("http://localhost:9004/users/" + id + "/acceptFriendshipRequest/" + idFriend, { responseType: 'json' });
  }

  declineFriedship(id: number, idFriend: number): Observable<any> {
    return this.http.delete("http://localhost:9004/users/" + id + "/declineFriendshipRequest/" + idFriend);
  }


  createUser(user: Object) {
    console.log('usao u servis regitracije angulara');
    this.http.post(`${this.baseUrl}` + `/register`, user)
      .subscribe((data) => {
        console.log(data);
      })
  }

  logInUser(logInRequest: LoginRequest): Observable<TokenState> {
    console.log('usao u servis login angulara');
    return this.http.post<TokenState>(`http://localhost:9004/auth/login`, logInRequest, this.headers);//mozda ne ;
  }

  logOut() {
    localStorage.clear();
    //this.http.post<TokenState>(`http://localhost:9004/auth/logout`, this.headers);
  }



  updateUser(id: number, value: UserDTO): Observable<any> {
    return this.http.post("http://localhost:9004/users/" + id + "/update", value, { responseType: 'json' });
  }

  deleteUser(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getUsersList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  getUsersByAge(age: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/age/${age}`);
  }


  deleteAll(): Observable<any> {
    return this.http.delete(`${this.baseUrl}` + `/delete`, { responseType: 'text' });
  }

  getRoles(username: string): Observable<AuthorityDTO> {
    return this.http.get<AuthorityDTO>("http://localhost:9004/auth/" + username + "/getRoles");
  }

  addUser(user: UserDTO) {
    this.http.post<UserDTO>("http://localhost:9004/users/add", user)
      .subscribe(data => {

      });
  }

  changePassword(id: number, oldPass: string, newPass: string) {
    return this.http.post("http://localhost:9004/users/" + id + "/changePassword/" + oldPass + "/" + newPass, { responseType: 'text' });
  }

  getInvites(id: number): Observable<any> {
    return this.http.get("http://localhost:9004/users/" + id + "/invites", { responseType: 'json' });
  }


}
