import { Component, OnInit } from '@angular/core';
import { RentACarService } from '../all-rent-a-cars/rent-a-car.service';
import { RentACar } from '../dto/rent-a-car.model';
import { Router, ActivatedRoute } from '../../../node_modules/@angular/router';
import { Observable } from '../../../node_modules/rxjs';
import { RentACarMenuItem } from '../dto/rent-a-car-menu-item.model';
import { RentACarBranch } from '../dto/rent-a-car-branch.model';
import { Vehicle } from '../dto/vehicle.model';

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

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private rentACarService : RentACarService
  ) { 
    this.router = router;
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => { //uzimanje parametara iz url-a
      this.id = + params['id'];
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
}
