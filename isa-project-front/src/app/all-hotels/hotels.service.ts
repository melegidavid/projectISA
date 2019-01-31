import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Hotel } from '../dto/hotel.model';
import { Observable } from '../../../node_modules/rxjs';
import { HotelRoom } from '../dto/hotel-room.model';
import { HotelMenuItem } from '../dto/hotel-menu-item.model';

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
    console.log("pre dodavanja hotela");
    this.http.post<Hotel>("http://localhost:9004/hotels", hotel)
    .subscribe(data => {
      
    });
    console.log("posle dodavanja hotela");
  }

}
