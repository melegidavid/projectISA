import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router'
import { RentACar } from '../dto/rent-a-car.model';
import { Observable } from '../../../node_modules/rxjs';
import { RentACarService } from './rent-a-car.service';
import { UserService } from '../user.service';
import { RentACarSearchDTO } from '../dto/rent-a-car-search';
import { Address } from '../dto/address.model';
import { AvioFlight } from '../dto/avio-flight.model';
import { Authority } from '../dto/authority.model';
import { AuthorityDTO } from '../dto/authorityDTO.model';

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
  name: string = "";
  start: Date;
  end: Date;
  address: Address = new Address();

  flight: AvioFlight;

  role: Authority = new Authority();
  roles: Authority[] = [];
  autoDTO: AuthorityDTO = new AuthorityDTO();


  userFlag: boolean = false;
  adminHotel: boolean = false;
  adminRent: boolean = false;


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

    if (this.username) {
      this.getRoles(this.username).subscribe(data => {
        this.autoDTO = data;
        this.roles = this.autoDTO.authorities;

        if (this.roles[0].authority === 'HOTEL_ADMIN') {
          this.userFlag = false;
          this.adminHotel = true;
          this.adminRent = false;
        } else if (this.roles[0].authority === 'RENT_CAR_ADMIN') {
          this.userFlag = false;
          this.adminHotel = false;
          this.adminRent = true;
        } else if (this.roles[0].authority === 'REGISTERED_USER') {
          this.userFlag = true;
          this.adminHotel = false;
          this.adminRent = false;
        }
      });
    }


    if (this.flight != undefined) {
      this.start = JSON.parse(localStorage.getItem('startDate'));
      this.address.city = this.flight.endLocation.city;
      this.address.country = this.flight.endLocation.country;
    }

    this.getRentACars().subscribe((data) => {
      this.rentACars = data;
    });


  }

  public getRentACars(): Observable<RentACar[]> {
    return this.rentACarSevice.getRentACars();
  }

  public searchRentACars(searchValues: RentACarSearchDTO): Observable<RentACar[]> {
    return this.rentACarSevice.searchRentACars(searchValues);
  }

  public search() {
    //validiraj formate datuma
    let startDate: Date;

    if (this.start == undefined) {
      startDate = null;
    } else {
      startDate = new Date(this.start);
    }

    let endDate: Date;
    if (this.end == undefined) {
      endDate = null;
    } else {
      endDate = this.end;
    }

    if (this.address.number == null) {
      this.address.number = 0;
    }

    this.address.postalCode = 0;
    this.address.id = 0;

    this.searchRentACars(new RentACarSearchDTO(this.name, this.start, this.end, this.address)).subscribe((data) => {
      this.rentACars = data;
    });

    this.restoreInputs();
  }

  public restore() {
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
    this.roles = [];
    this.userService.logOut();
    this.adminHotel = false;
    this.adminRent = false;
    this.userFlag = false;
    this.router.navigate['home'];
  }
  public getRoles(username: string): Observable<AuthorityDTO> {
    return this.userService.getRoles(username);
  }


}
