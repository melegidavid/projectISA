import { Component, OnInit } from '@angular/core';
import { Observable } from '../../../node_modules/rxjs';
import { HttpClient } from '@angular/common/http';

class Hotel {
  id: string;
  name: string;
  address: Address;
  description: string;
}

class Address {
  country: string;
  city: string;
  postalCode: number;
  street: string;
  number: number;
}

@Component({
  selector: 'app-all-hotels',
  templateUrl: './all-hotels.component.html',
  styleUrls: ['./all-hotels.component.css']
})
export class AllHotelsComponent implements OnInit {
  
  hotelsObservable : Observable<Hotel[]>;
  hotels: any;
  hotel: Hotel;
  //response: any;

  constructor(private http: HttpClient) {

   }

  ngOnInit() {
    this.http.get("http://localhost:9004/hotels")
    .subscribe((data) => {
      this.hotels = data; //promeniti da se umesto tipa any prima bas tip hotel.
    })

    this.getHotel().subscribe(data => {
      this.hotel = data;
      console.log(data);
      console.log(this.hotel);
    });

  }

  getHotels() : Observable<Hotel[]> {
    return this.http.get<Hotel[]>("http://localhost:9004/hotels", {responseType: 'json'})
  }

  getHotel() : Observable<Hotel>{
    return this.http.get<Hotel>("http://localhost:9004/hotels/1", {responseType: 'json'});
  }
}
