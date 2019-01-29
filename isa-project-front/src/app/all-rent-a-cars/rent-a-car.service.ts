import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from '../../../node_modules/rxjs';
import { RentACar } from '../dto/rent-a-car.model';
import { RentACarMenuItem } from '../dto/rent-a-car-menu-item.model';
import { RentACarBranch } from '../dto/rent-a-car-branch.model';
import { Vehicle } from '../dto/vehicle.model';

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

  getRentACarMenu(id: number | string) : Observable<RentACarMenuItem[]> {
    return this.http.get<RentACarMenuItem[]>("http://localhost:9004/rent_a_cars/" + id + "/menu", {responseType: 'json'});
  }

  getRentACarBranches(id: number | string) : Observable<RentACarBranch[]> {
    return this.http.get<RentACarBranch[]>("http://localhost:9004/rent_car_branches/branchesOf/" + id, {responseType: 'json'});
  }

  getVehicles(id: number | string) : Observable<Vehicle[]> {
    return this.http.get<Vehicle[]>("http://localhost:9004/rent_a_cars/" + id + "/vehicles", {responseType: 'json'});
  }


}