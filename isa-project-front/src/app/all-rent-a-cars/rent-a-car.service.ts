import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from '../../../node_modules/rxjs';
import { RentACar } from '../dto/rent-a-car.model';

@Injectable({
  providedIn: 'root'
})
export class RentACarService {

  constructor(private http: HttpClient) { }

  getRentACars() : Observable<RentACar[]> {
    return this.http.get<RentACar[]>("http://localhost:9004/rent_a_cars/all", {responseType: 'json'});
  }

  getRentACar(id: number | string) : Observable<RentACar> {
    return this.http.get<RentACar>("http://localhost:9004/rent_a_cars/" + id, {responseType: 'json'});
  }
}
