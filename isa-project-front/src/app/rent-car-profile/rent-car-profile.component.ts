import { Component, OnInit } from '@angular/core';
import { RentACarService } from '../all-rent-a-cars/rent-a-car.service';
import { RentACar } from '../dto/rent-a-car.model';
import { Router, ActivatedRoute } from '../../../node_modules/@angular/router';
import { Observable } from '../../../node_modules/rxjs';
import { RentACarMenuItem } from '../dto/rent-a-car-menu-item.model';
import { RentACarBranch } from '../dto/rent-a-car-branch.model';
import { Vehicle } from '../dto/vehicle.model';
import { UserService } from '../user.service';
import { DateRange } from '../dto/date-range';
import { RentCarReport } from '../dto/rent-car-report';
import { DomSanitizer, SafeUrl } from '../../../node_modules/@angular/platform-browser';
import { Address } from '../dto/address.model';
import { RentACarSearchDTO } from '../dto/rent-a-car-search';
import { VehicleReservationDTO } from '../dto/vehicleReservationDTO';
import { VehicleSearch } from '../dto/vehicle-search.model';
import { AvioFlight } from '../dto/avio-flight.model';


@Component({
  selector: 'app-rent-car-profile',
  templateUrl: './rent-car-profile.component.html',
  styleUrls: ['./rent-car-profile.component.css'],
  providers: [RentACarService]
})

export class RentCarProfileComponent implements OnInit {

  rentACar: RentACar;
  menu: RentACarMenuItem[] = [];
  branches: RentACarBranch[] = [];
  vehicles: Vehicle[] = [];
  private sub: any;
  id: number;
  len: number;
  username: string;
  avgRatingRentCar : number;
  showAdminControls : boolean;
  dateRange : DateRange;
  report : RentCarReport;
  showReport : boolean;

  period: any;
  startDate : Date;
  endDate : Date;

  rentCarSearch : RentACarSearchDTO;
  startDateSearch: Date;
  endDateSearch: Date;
  vehicleTypeSearch: string;
  numberOfPassengersSearch: number;

  addressUrl: string;
  trustedUrl: SafeUrl;
  
  searchClicked : boolean = false;
  flight: AvioFlight;
  

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private rentACarService : RentACarService,
    private userService: UserService,
    private sanitizer: DomSanitizer
  ) { 
    this.router = router;
  }

  ngOnInit() {
    this.len = localStorage.length;
    this.avgRatingRentCar = 1;
    this.dateRange = new DateRange();
    this.startDate = new Date();
    this.endDate = new Date();
    this.showReport = false;
    this.vehicleTypeSearch = "CAR";

    this.username = localStorage.getItem('username');
    this.flight = JSON.parse(localStorage.getItem('flight'));
    console.log(localStorage);

    let x = localStorage.getItem('role');
    if(x == undefined || x == null || x !=  "RENT_CAR_ADMIN") {
          this.showAdminControls = false;
    } else {
      this.showAdminControls = true;
    }

    this.sub = this.route.params.subscribe(params => { //uzimanje parametara iz url-a
      this.id = + params['id'];
    });

    this.rentACarService.getRentACarAvgRating(this.id).subscribe(data => {
      this.avgRatingRentCar = data;
      console.log('vratio ' + this.avgRatingRentCar);
    });

    this.getRentACar(this.id).subscribe(data => {
      this.rentACar = data;
      this.makeAddressUrl(this.rentACar.addressDTO);
      this.trustedUrl = this.sanitizer.bypassSecurityTrustResourceUrl(this.addressUrl);
    });

    this.getRentACarMenu(this.id).subscribe(data => {
      this.menu = data;
    });

    this.getRentACarBranches(this.id).subscribe(data => {
      this.branches = data;
    });

    this.getVehicles(this.id).subscribe(data => {
      this.vehicles = data;
    });

  }

  public search() {
    let vehicleSearch: VehicleSearch = new VehicleSearch();
    vehicleSearch.idRentCar = this.id;
    vehicleSearch.takePlace = this.id;
    vehicleSearch.returnPlace = this.id;
    vehicleSearch.takeDate = this.startDateSearch;
    vehicleSearch.returnDate = this.endDateSearch;
    vehicleSearch.numberOfPassengers = this.numberOfPassengersSearch;
    vehicleSearch.vehicleType = this.vehicleTypeSearch;

    if(this.startDate == undefined || this.endDate == undefined || this.numberOfPassengersSearch == undefined) {
      alert("Please enter parameters in all fields");
    } else {
      this.rentACarService.getFreeVehicles(vehicleSearch).subscribe(data => {
        this.vehicles = data;
        this.searchClicked = true;
      });
    }
  }

  public makeReservation(vehicle: Vehicle) {
    let reservation : VehicleReservationDTO = new VehicleReservationDTO();
    reservation.vehicle = vehicle;
    reservation.username = localStorage.getItem('username');
    reservation.startReservation = this.startDateSearch;
    reservation.endReservation = this.endDateSearch;
    reservation.takePlaceId = this.id;
    reservation.returnPlaceId = this.id;
    reservation.price = vehicle.price;
    reservation.rentCarRating = -1;
    reservation.vehicleRating = -1;

    
    this.rentACarService.makeReservation(reservation).subscribe(data => {
    });

    //alert()
    // this.router.navigate(['home']);
    localStorage.setItem("vehicle", JSON.stringify(vehicle));
    
  }

  public makeAddressUrl(address: Address) {
    this.addressUrl = 'https://maps.google.com/maps?q=';
    let grad = address.city.split(" ");
    let ulica = address.street.split(" ");

    for(let i = 0; i<grad.length; i++) {
      if(i == 0) {
        this.addressUrl += grad[i];
      } else {
        this.addressUrl += '%20' + grad[i];
      }
    }

    for(let i = 0; i<ulica.length; i++) {
      this.addressUrl += '%20' + ulica[i];
    }
    this.addressUrl += '%20' + address.number;
    this.addressUrl += '&t=&z=13&ie=UTF8&iwloc=&output=embed';
  }


  public getRentACar(id: number | string) : Observable<RentACar> {
    return this.rentACarService.getRentACar(id);
  }

  public getRentACarMenu(id: number | string) : Observable<RentACarMenuItem[]> {
    return this.rentACarService.getRentACarMenu(id);
  }

  public getRentACarBranches(id: number | string) : Observable<RentACarBranch[]> {
    return this.rentACarService.getRentACarBranches(id);
  }

  public getVehicles(id: number | string) : Observable<Vehicle[]> {
    return this.rentACarService.getVehicles(id);
  }

  logOut() {
    console.log('usao u logout');
    this.userService.logOut();
    
    console.log('ostalo ' + localStorage.length);
    this.ngOnInit();
  }

  generateReport() {

    this.dateRange = new DateRange();
    this.dateRange.startDate = this.startDate;
    this.dateRange.endDate = this.endDate;

    this.userService.generateReport(this.id,this.dateRange).subscribe(data => {
      console.log(data);
      this.report = data;
      this.showReport = true;
    });

  }

}
