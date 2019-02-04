import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AvioCompany } from '../dto/avio-company.model';
import { AvioFlight } from '../dto/avio-flight.model';
import { Address } from '../dto/address.model';

@Injectable()
export class AdminAvioCompanyService {

  constructor(private http: HttpClient) { }

  getDestinationsOfAvioCompany(id: number): Observable<any> {
    return this.http.get("http://localhost:9004/avio_companies/" + id + "/destinations", { responseType: 'json' });
  }

  updateAvioCompany(id: number, value: AvioCompany): Observable<any> {
    return this.http.post("http://localhost:9004/avio_companies/" + id + "/update", value, { responseType: 'json' });
  }

  addDestination(id: number, value: Address): Observable<any> {
    return this.http.post("http://localhost:9004/avio_companies/" + id + "/destination", value, { responseType: 'json' });
  }

  removeDestination(id: number, idDestination: number): Observable<any> {
    return this.http.delete("http://localhost:9004/avio_companies/" + id + "/removeDestination/" + idDestination, { responseType: 'text' });
  }

  addFlight(id: number, value: AvioFlight): Observable<any> {
    return this.http.post("http://localhost:9004/avio_companies/" + id + "/flights", value, { responseType: 'json' });

  }

  updateFlight(id: number, idFlight: number, value: AvioFlight): Observable<any> {
    return this.http.post("http://localhost:9004/avio_companies/" + id + "/flights/" + idFlight, value, { responseType: 'json' });
  }

  removeFlight(id: number, idFlight: number): Observable<any> {
    return this.http.delete("http://localhost:9004/avio_companies/" + id + "/flights/" + idFlight, { responseType: 'text' });
  }

}
