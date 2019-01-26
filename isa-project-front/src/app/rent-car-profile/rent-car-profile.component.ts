import { Component, OnInit } from '@angular/core';
import { RentACarService } from '../all-rent-a-cars/rent-a-car.service';
import { RentACar } from '../dto/rent-a-car.model';
import { Router, ActivatedRoute } from '../../../node_modules/@angular/router';
import { Observable } from '../../../node_modules/rxjs';

@Component({
  selector: 'app-rent-car-profile',
  templateUrl: './rent-car-profile.component.html',
  styleUrls: ['./rent-car-profile.component.css'],
  providers: [RentACarService]
})

export class RentCarProfileComponent implements OnInit {

  rentACar: RentACar;
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
  }

  public getRentACar(id: number | string) : Observable<RentACar> {
    return this.rentACarService.getRentACar(id);
  }

}
