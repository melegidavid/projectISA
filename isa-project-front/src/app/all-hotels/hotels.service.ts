import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Hotel } from '../dto/hotel.model';
import { Observable } from '../../../node_modules/rxjs';
import { HotelRoom } from '../dto/hotel-room.model';
import { HotelMenuItem } from '../dto/hotel-menu-item.model';
import { RoomReservation } from '../dto/room-reservation.model';
import { RoomSearch } from '../dto/room-search.model';
import { HotelMenuItemReservation } from '../dto/hotel-menu-item-reservation.model';

@Injectable({
  providedIn: 'root'
})

export class HotelsService {

  constructor(private http: HttpClient) {

  }

  getHotels() : Observable<Hotel[]> {
    return this.http.get<Hotel[]>("http://localhost:9004/hotels", {responseType: 'json'});
  }

  getHotel(id: number | string) : Observable<Hotel>{
    console.log("http://localhost:9004/hotels/" + id);
    return this.http.get<Hotel>("http://localhost:9004/hotels/" + id, {responseType: 'json'});
  }

  getHotelRooms(id: number | string): Observable<HotelRoom[]>{
    return this.http.get<HotelRoom[]>("http://localhost:9004/hotels/" + id + "/rooms");
  }

  getHotelMenu(id: number | string) : Observable<HotelMenuItem[]> {
    return this.http.get<HotelMenuItem[]>("http://localhost:9004/hotels/" + id + "/menu");
  }

  addHotel(hotel: Hotel) {
    this.http.post<Hotel>("http://localhost:9004/hotels", hotel)
    .subscribe(data => {
      
    });
  }

  updateHotel(hotel: Hotel) {
    this.http.post<Hotel>("http://localhost:9004/hotels/update", hotel).subscribe();
  }

  searchHotels(roomSearch: RoomSearch) : Observable<Hotel[]> {
    return this.http.post<Hotel[]>("http://localhost:9004/hotels/search", roomSearch);
  }

  getFreeRooms(id:number, roomSearch: RoomSearch): Observable<HotelRoom[]> {
    return this.http.post<HotelRoom[]>("http://localhost:9004/hotels/"+ id +"/freeRooms", roomSearch);
  }

  searchRooms(id:number, roomSearch: RoomSearch) : Observable<HotelRoom[]> {
    return this.http.post<HotelRoom[]>("http://localhost:9004/hotels/"+ id +"/search", roomSearch);
  }

  getHotelRoom(id: number | string) : Observable<HotelRoom> {
    return this.http.get<HotelRoom>("http://localhost:9004/hotels/rooms/" + id, {responseType: 'json'});
  }

  addHotelRoom(hotelId: number | string, hotelRoom: HotelRoom) : Observable<HotelRoom> {
    return this.http.post<HotelRoom>("http://localhost:9004/hotels/"+ hotelId + "/rooms", hotelRoom);
  }

  updateHotelRoom(hotelId: number | string, roomId: number | string, hotelRoom: HotelRoom) : Observable<HotelRoom> {
    return this.http.post<HotelRoom>("http://localhost:9004/hotels/" + hotelId + "/rooms/" + roomId, hotelRoom);
  }

  deleteHotelRoom(roomId: number | string) {
    this.http.delete("http://localhost:9004/hotels/rooms/" + roomId).subscribe();
  }

  makeReservation(id: number | string, roomReservation: RoomReservation) {
    return this.http.post<RoomReservation>("http://localhost:9004/hotels/reservation/" + id, roomReservation)
    .subscribe();
  }

  makeItemReservation(id: number | string, itemReservation: HotelMenuItemReservation) {
    return this.http.post<HotelMenuItemReservation>("http://localhost:9004/hotels/item_reservation/" + id, itemReservation)
    .subscribe();
  }

  getHotelMenuItem(hotelId: number | string, itemId: number | string) : Observable<HotelMenuItem> {
    return this.http.get<HotelMenuItem>("http://localhost:9004/hotels/"+ hotelId + "/menu/"+ itemId);
  }

  addHotelMenuItem(hotelId: number | string, hotelMenuItem: HotelMenuItem) : Observable<HotelMenuItem> {
    return this.http.post<HotelMenuItem>("http://localhost:9004/hotels/" + hotelId + "/menu/", hotelMenuItem);
  }

  updateHotelMenuItem(hotelId: number | string, itemId: number | string, hotelMenuItem: HotelMenuItem) : Observable<HotelMenuItem> {
    return this.http.post<HotelMenuItem>("http://localhost:9004/hotels/"+ hotelId + "/menu/"+ itemId, hotelMenuItem);
  }

  deleteHotelMenuItem(itemId: number | string) {
    this.http.delete("http://localhost:9004/hotels/menu/"+ itemId).subscribe();
  }

  getHotelAvgRating(id: number) : Observable<number> {
    return this.http.get<number>("http://localhost:9004/hotels/" + id + "/avgHotelRating");
  }
}
