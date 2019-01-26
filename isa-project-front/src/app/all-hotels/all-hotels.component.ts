import { Component, OnInit } from '@angular/core';
import { Observable } from '../../../node_modules/rxjs';
import { HttpClient } from '@angular/common/http';
import { Hotel } from '../dto/hotel.model';
import { HotelsService } from './hotels.service';

@Component({
  selector: 'app-all-hotels',
  templateUrl: './all-hotels.component.html',
  styleUrls: ['./all-hotels.component.css'],
  providers: [HotelsService]
})

export class AllHotelsComponent implements OnInit {
  
  hotels : Hotel[] = [];
  hotel: Hotel;
  
  constructor(private http: HttpClient, private hotelsService: HotelsService) {
  }
  
  ngOnInit() {
    this.getHotels().subscribe(data => {
      this.hotels = data;
    });
  }

  public getHotels(): Observable<Hotel[]> {
    return this.hotelsService.getHotels();
  }
}
