import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router} from '@angular/router'

@Component({
  selector: 'app-all-rent-a-cars',
  templateUrl: './all-rent-a-cars.component.html',
  styleUrls: ['./all-rent-a-cars.component.css']
})
export class AllRentACarsComponent implements OnInit {

  rentACars: any;

  constructor(private http: HttpClient, private router: Router) {
       this.router = router; 
   }

  ngOnInit() {
    this.http.get("http://localhost:9004/rent_a_cars/all")
    .subscribe((data) => {
      this.rentACars = data;
      console.log(this.rentACars);
    })

    


  }

}
