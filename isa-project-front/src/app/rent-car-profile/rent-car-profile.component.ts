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
  startDate : Date;
  endDate : Date;
  

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private rentACarService : RentACarService,
    private userService: UserService
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

    this.username = localStorage.getItem('username');
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
