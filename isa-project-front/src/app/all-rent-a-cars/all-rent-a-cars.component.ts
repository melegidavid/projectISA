import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router} from '@angular/router'
import { RentACar } from '../dto/rent-a-car.model';
import { Observable } from '../../../node_modules/rxjs';
import { RentACarService } from './rent-a-car.service';
import { UserService } from '../user.service';
import { RentACarSearchDTO } from '../dto/rent-a-car-search';
import { Address } from '../dto/address.model';

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
  start : string = "";
  end : string = "";
  address : Address = new Address();

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
    console.log(localStorage);

    this.getRentACars().subscribe((data) => {
      this.rentACars = data;
    });

    this.restoreInputs(); 
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

    if(this.start == "") {
      startDate = null;
    } else {
      startDate = new Date(this.start);
    }

    let endDate : Date;
    if(this.end == "") {
      endDate = null;
    } else {
      endDate = new Date(this.end);
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
    this.end = "";
    this.start = "";
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
