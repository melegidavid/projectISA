import { Component, OnInit } from '@angular/core';
import { AvioFlightSeat } from '../dto/avio-flight-seat.model';
import * as $ from 'jquery';
import { Observable } from '../../../node_modules/rxjs';
import { AvioCompaniesService } from '../all-avio-companies/avio-companies.service';
import { ActivatedRoute, Router } from '../../../node_modules/@angular/router';

@Component({
  selector: 'app-avio-flight-seats',
  templateUrl: './avio-flight-seats.component.html',
  styleUrls: ['./avio-flight-seats.component.css']
})
export class AvioFlightSeatsComponent implements OnInit {
  
  id:number;
  username: string;
  sub: any;
  classOfFlight: string;
  numberOfTravelers: number;
  
  seats: AvioFlightSeat[] = [];
  selectedSeats: AvioFlightSeat[] = [];

  numOfRows: number;
  numOfColumns: number = 6;

  clickedCounter = 0;

  constructor(
    private avioCompaniesService: AvioCompaniesService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.username = localStorage.getItem('username');
    this.classOfFlight = localStorage.getItem('fligthClass');
    this.numberOfTravelers = JSON.parse(localStorage.getItem('numberOfTravelers'));
    console.log("broj putnika: " + this.numberOfTravelers);
    
    this.sub = this.route.params.subscribe(params => { //uzimanje parametara iz url-a
      this.id = + params['id'];
    });
    
    this.getAvioFlightSeats(this.id).subscribe(data => {
      this.seats = data;
      this.numOfRows = this.seats.length/this.numOfColumns;
      console.log(this.seats);
      console.log(this.numOfRows);
    });
    

  }

  public getAvioFlightSeats(id: number | string) : Observable<AvioFlightSeat[]> {
    return this.avioCompaniesService.getAvioFlightSeats(id);
  }

  public selectSeat(s: AvioFlightSeat) {
    if(s.clicked == undefined) {
      if(this.clickedCounter >= this.numberOfTravelers) {
        alert("You already select " + this.numberOfTravelers +" seats");
      } else {
        s.clicked = true;
        this.selectedSeats.push(s);
        this.clickedCounter++;
        console.log(this.selectedSeats);
      }
    } else {
      s.clicked = undefined;
      let newSeats: AvioFlightSeat[] = [];
      this.selectedSeats.forEach(seat => {
        if(seat.id != s.id) {
          newSeats.push(seat);
        }
      });
      this.selectedSeats = newSeats;
      this.clickedCounter--;  
      console.log(this.selectedSeats);
    }
  }

}
