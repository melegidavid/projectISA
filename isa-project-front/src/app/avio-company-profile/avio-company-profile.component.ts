import { Component, OnInit } from '@angular/core';
import { AvioCompaniesService } from '../all-avio-companies/avio-companies.service';
import { ActivatedRoute, Router } from '../../../node_modules/@angular/router';
import { AvioCompany } from '../dto/avio-company.model';
import { Observable } from '../../../node_modules/rxjs';
import { UserService } from '../user.service';
import { AvioFlight } from '../dto/avio-flight.model';
import { AvioReport } from '../dto/avio-report';
import { DateRange } from '../dto/date-range';
import { Address } from '../dto/address.model';
import { DomSanitizer, SafeUrl } from '../../../node_modules/@angular/platform-browser';
import { Authority } from '../dto/authority.model';
import { AuthorityDTO } from '../dto/authorityDTO.model';

@Component({
  selector: 'app-avio-company-profile',
  templateUrl: './avio-company-profile.component.html',
  styleUrls: ['./avio-company-profile.component.css']
})
export class AvioCompanyProfileComponent implements OnInit {

  role: Authority = new Authority();
  roles: Authority[] = [];
  autoDTO: AuthorityDTO = new AuthorityDTO();


  user: boolean = false;
  adminHotel: boolean = false;
  adminRent: boolean = false;
  len: number;
  username: string;
  avioCompany: AvioCompany;
  flights: AvioFlight[] = [];
  sub: any;
  id: number;
  avgRatingAvio: number;
  report: AvioReport;
  showReport: boolean;
  dateRange: DateRange;
  startDateMy: Date;
  endDateMy: Date;
  showAdminControls: boolean;

  addressUrl: string;
  trustedUrl: SafeUrl;

  constructor(private avioCompaniesService: AvioCompaniesService,
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService,
    private sanitizer: DomSanitizer) {
    this.router = router;
  }

  ngOnInit() {
    this.len = localStorage.length;

    this.username = localStorage.getItem('username');

    if (this.username) {
      this.getRoles(this.username).subscribe(data => {
        this.autoDTO = data;
        this.roles = this.autoDTO.authorities;

        if (this.roles[0].authority === 'HOTEL_ADMIN') {
          this.user = false;
          this.adminHotel = true;
          this.adminRent = false;
        } else if (this.roles[0].authority === 'RENT_CAR_ADMIN') {
          this.user = false;
          this.adminHotel = false;
          this.adminRent = true;
        } else if (this.roles[0].authority === 'REGISTERED_USER') {
          this.user = true;
          this.adminHotel = false;
          this.adminRent = false;
        }
      });
    }

    let x = localStorage.getItem('role');
    if (x == undefined || x == null || x != "AVIO_COMPANY_ADMIN") {
      this.showAdminControls = false;
    } else {
      this.showAdminControls = true;
    }

    this.avgRatingAvio = 1;
    this.showReport = false;
    this.dateRange = new DateRange();
    this.startDateMy = new Date();
    this.endDateMy = new Date();

    this.sub = this.route.params.subscribe(params => { //uzimanje parametara iz url-a
      this.id = + params['id'];
    });

    this.avioCompaniesService.getAvioAvgRating(this.id).subscribe(data => {
      this.avgRatingAvio = data;
    });

    this.getAvioCompany(this.id).subscribe(data => {
      this.avioCompany = data;
      this.makeAddressUrl(this.avioCompany.address);
      this.trustedUrl = this.sanitizer.bypassSecurityTrustResourceUrl(this.addressUrl);
    });

    this.getAvioFlights(this.id).subscribe(data => {
      this.flights = data;
    });
  }

  public makeAddressUrl(address: Address) {
    this.addressUrl = 'https://maps.google.com/maps?q=';
    let grad = address.city.split(" ");
    let ulica = address.street.split(" ");

    for (let i = 0; i < grad.length; i++) {
      if (i == 0) {
        this.addressUrl += grad[i];
      } else {
        this.addressUrl += '%20' + grad[i];
      }
    }

    for (let i = 0; i < ulica.length; i++) {
      this.addressUrl += '%20' + ulica[i];
    }
    this.addressUrl += '%20' + address.number;
    this.addressUrl += '&t=&z=13&ie=UTF8&iwloc=&output=embed';
  }

  public getAvioCompany(id: number | string): Observable<AvioCompany> {
    return this.avioCompaniesService.getAvioCompany(id);
  }

  public getAvioFlights(id: number | string): Observable<AvioFlight[]> {
    return this.avioCompaniesService.getAvioFlights(id);
  }

  logOut() {
    this.roles = [];
    this.userService.logOut();
    this.adminHotel = false;
    this.adminRent = false;
    this.user = false;
    this.router.navigate['home'];
  }

  generateReport() {

    this.dateRange = new DateRange();
    this.dateRange.startDate = this.startDateMy;
    this.dateRange.endDate = this.endDateMy;

    this.userService.generateReportAvio(this.id, this.dateRange).subscribe(data => {
      this.report = data;
      this.showReport = true;
    });

  }

  public getRoles(username: string): Observable<AuthorityDTO> {
    return this.userService.getRoles(username);
  }

}
