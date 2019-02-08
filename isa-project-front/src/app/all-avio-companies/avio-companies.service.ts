import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from '../../../node_modules/rxjs';
import { AvioCompany } from '../dto/avio-company.model';
import { AvioFlight } from '../dto/avio-flight.model';
import { AvioflightSearchDTO } from '../dto/avio-flight-search';
import { AvioFlightSeat } from '../dto/avio-flight-seat.model';
import { AvioFlightReservation } from '../dto/avio-flight-reservation';


@Injectable({
  providedIn: 'root'
})
export class AvioCompaniesService {

  constructor(private http: HttpClient) { }

  getAvioCompanies(): Observable<AvioCompany[]> {
    return this.http.get<AvioCompany[]>("http://localhost:9004/avio_companies", { responseType: 'json' });
  }

  getAvioCompany(id: number | string): Observable<AvioCompany> {
    return this.http.get<AvioCompany>("http://localhost:9004/avio_companies/" + id, { responseType: 'json' });
  }

  getAvioFlights(id: number | string): Observable<AvioFlight[]> {
    return this.http.get<AvioFlight[]>("http://localhost:9004/avio_companies/" + id + "/flights", { responseType: 'json' });
  }

  addAvioCompany(avioCompany: AvioCompany) {
    this.http.post<AvioCompany>("http://localhost:9004/avio_companies", avioCompany)
      .subscribe(data => {

      });
  }

  getAllDestinations(): Observable<any> {
    return this.http.get("http://localhost:9004/avio_companies/destinations", { responseType: 'json' });
  }

  searchFlights(search: AvioflightSearchDTO): Observable<any> {
    return this.http.post("http://localhost:9004/avio_companies/search", search, { responseType: 'json' });
  }

  getAvioFlightSeats(flightId: number | string) : Observable<AvioFlightSeat[]> {
    return this.http.get<AvioFlightSeat[]>("http://localhost:9004/avio_companies/flight/" + flightId + "/seats", {responseType: 'json'});
  }

  getAvioAvgRating(id: number) : Observable<number> {
    return this.http.get<number>("http://localhost:9004/avio_companies/" + id + "/avgAvioRating");
  }

  makeReservation(reservation: AvioFlightReservation) :Observable<any>{
    return this.http.post("http://localhost:9004/avio_companies/flight/reservation", reservation, {responseType: 'json'});
  }

  declineInvite(idFlight: number, idUser: number, idInvite: number){
    return this.http.delete("http://localhost:9004/avio_companies/"+ idFlight + "/flight/" +idUser + "/declineReservation/" + idInvite);
  }

}
