import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from '../../../node_modules/rxjs';
import { AvioCompany } from '../dto/avio-company.model';
import { AvioFlight } from '../dto/avio-flight.model';

@Injectable({
  providedIn: 'root'
})
export class AvioCompaniesService {

  constructor(private http: HttpClient) { }

  getAvioCompanies(): Observable<AvioCompany[]> {
    return this.http.get<AvioCompany[]>("http://localhost:9004/avio_companies", {responseType: 'json'});
  }

  getAvioCompany(id: number | string) : Observable<AvioCompany> {
    return this.http.get<AvioCompany>("http://localhost:9004/avio_companies/" + id, {responseType: 'json'});
  }

  getAvioFlights(id: number | string) : Observable<AvioFlight[]> {
    return this.http.get<AvioFlight[]>("http://localhost:9004/avio_companies/" + id + "/flights", {responseType: 'json'});
  }

  addAvioCompany(avioCompany: AvioCompany) {
    this.http.post<AvioCompany>("http://localhost:9004/avio_companies", avioCompany)
    .subscribe(data => {
      
    });
  }

}
