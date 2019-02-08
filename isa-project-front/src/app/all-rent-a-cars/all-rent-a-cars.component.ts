import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router} from '@angular/router'
import { RentACar } from '../dto/rent-a-car.model';
import { Observable } from '../../../node_modules/rxjs';
import { RentACarService } from './rent-a-car.service';
import { UserService } from '../user.service';
import { RentACarSearchDTO } from '../dto/rent-a-car-search';
import { Address } from '../dto/address.model';
import { AvioFlight } from '../dto/avio-flight.model';

@Component({
  selector: 'app-all-rent-a-cars',
  templateUrl: './all-rent-a-cars.component.html',
  styleUrls: ['./all-rent-a-cars.component.css'],
  providers: [RentACarService]
})

export class AllRentACarsComponent implements OnInit {

  len: number;
  username: string;
  rentACars: RentACar[] = [];
  name : string = "";
  start : Date;
  end : Date;
  address : Address = new Address();

  flight: AvioFlight;

  constructor(
    private http: HttpClient,
    private router: Router,
    private rentACarSevice: RentACarService,
    private userService: UserService) {
       this.router = router; 
   }

  ngOnInit() {
    this.len = localStorage.length;
    this.username = localStorage.getItem('username');
    this.flight = JSON.parse(localStorage.getItem('flight'));
    this.restoreInputs(); 

    if(this.flight != undefined) {
      this.start = JSON.parse(localStorage.getItem('startDate'));
      console.log("start date mi ispisi" + this.start);
      this.address.city = this.flight.endLocation.city;
      console.log("adres siti: " + this.address.city);
      this.address.country = this.flight.endLocation.country;
      console.log("adres kantri: " + this.address.country);
    }
    console.log(localStorage);

    this.getRentACars().subscribe((data) => {
      this.rentACars = data;
    });

    
  }

  public getRentACars(): Observable<RentACar[]> {
    return this.rentACarSevice.getRentACars();
  }

  public searchRentACars(searchValues : RentACarSearchDTO ): Observable<RentACar[]> {
    return this.rentACarSevice.searchRentACars(searchValues);
  }

  public search() {
    //validiraj formate datuma
    let startDate : Date;

    if(this.start == undefined) {
      startDate = null;
    } else {
      startDate = new Date(this.start);
    }

    let endDate : Date;
    if(this.end == undefined) {
      endDate = null;
    } else {
      endDate = this.end;
    }

    if(this.address.number == null) {
      this.address.number = 0;
    }

    this.address.postalCode = 0;
    this.address.id = 0;
    console.log(this.address.postalCode);
    console.log(this.address.id);

    console.log('Pokrenuta pretraga');
    console.log(this.name);
    console.log(startDate);
    console.log(endDate);
    console.log(this.address);
    
    this.searchRentACars(new RentACarSearchDTO(this.name,this.start,this.end, this.address)).subscribe((data) => {
      this.rentACars = data;
    });

    this.restoreInputs();
  }

  public restore() {
    console.log('restoring..');
    this.restoreInputs();
    
    this.getRentACars().subscribe((data) => {
      this.rentACars = data;
    });
  }

  public restoreInputs() {
    this.name = "";
    this.end = undefined;
    this.start = undefined;
    this.address.city = "";
    this.address.country = "";
    this.address.street = "";
    this.address.number = 0;
    this.address.id = 0;
    this.address.postalCode = 0;  
  }


  logOut() {
    console.log('usao u logout');
    this.userService.logOut();
    
    console.log('ostalo ' + localStorage.length);
    this.ngOnInit();
  }

}
