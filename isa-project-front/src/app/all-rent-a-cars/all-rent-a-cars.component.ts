import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router} from '@angular/router'
import { RentACar } from '../dto/rent-a-car.model';
import { Observable } from '../../../node_modules/rxjs';
import { RentACarService } from './rent-a-car.service';

@Component({
  selector: 'app-all-rent-a-cars',
  templateUrl: './all-rent-a-cars.component.html',
  styleUrls: ['./all-rent-a-cars.component.css'],
  providers: [RentACarService]
})

export class AllRentACarsComponent implements OnInit {

  rentACars: RentACar[] = [];

  constructor(private http: HttpClient, private router: Router, private rentACarSevice: RentACarService) {
       this.router = router; 
   }

  ngOnInit() {
    this.getRentACars().subscribe((data) => {
      this.rentACars = data;
    });
  }

  public getRentACars(): Observable<RentACar[]> {
    return this.rentACarSevice.getRentACars();
  }

}
