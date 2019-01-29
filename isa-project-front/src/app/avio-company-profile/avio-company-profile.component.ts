import { Component, OnInit } from '@angular/core';
import { AvioCompaniesService } from '../all-avio-companies/avio-companies.service';
import { ActivatedRoute, Router } from '../../../node_modules/@angular/router';
import { AvioCompany } from '../dto/avio-company.model';
import { Observable } from '../../../node_modules/rxjs';
import { UserService } from '../user.service';
import { AvioFlight } from '../dto/avio-flight.model';

@Component({
  selector: 'app-avio-company-profile',
  templateUrl: './avio-company-profile.component.html',
  styleUrls: ['./avio-company-profile.component.css']
})
export class AvioCompanyProfileComponent implements OnInit {

  len: number;
  username: string;
  avioCompany: AvioCompany;
  flights: AvioFlight[] = [];
  sub: any;
  id: number;
  
  constructor(private avioCompaniesService: AvioCompaniesService, 
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService) {
      this.router = router;
    }

  ngOnInit() {
    this.len = localStorage.length;
    this.username = localStorage.getItem('username');
    console.log(localStorage);

    this.sub = this.route.params.subscribe(params => { //uzimanje parametara iz url-a
      this.id = + params['id'];
    });

    this.getAvioCompany(this.id).subscribe(data => {
      this.avioCompany = data;
    });

    this.getAvioFlights(this.id).subscribe(data => {
      this.flights = data;
    });
  }

  public getAvioCompany(id: number | string): Observable<AvioCompany> {
    return this.avioCompaniesService.getAvioCompany(id);
  }

  public getAvioFlights(id: number | string): Observable<AvioFlight[]> {
    return this.avioCompaniesService.getAvioFlights(id);
  }

  logOut() {
    console.log('usao u logout');
    this.userService.logOut();
    
    console.log('ostalo ' + localStorage.length);
    this.ngOnInit();
  }
}
