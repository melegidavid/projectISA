import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router} from '@angular/router'
import { RentACar } from '../dto/rent-a-car.model';
import { Observable } from '../../../node_modules/rxjs';
import { RentACarService } from './rent-a-car.service';
import { UserService } from '../user.service';

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
  }

  public getRentACars(): Observable<RentACar[]> {
    return this.rentACarSevice.getRentACars();
  }

  logOut() {
    console.log('usao u logout');
    this.userService.logOut();
    
    console.log('ostalo ' + localStorage.length);
    this.ngOnInit();
  }

}
