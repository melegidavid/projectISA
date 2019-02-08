import { Component, OnInit } from '@angular/core';
import { AvioCompaniesService } from './avio-companies.service';
import { AvioCompany } from '../dto/avio-company.model';
import { Observable, from } from '../../../node_modules/rxjs';
import { UserService } from '../user.service';
import { Address } from '../dto/address.model';
import { AvioflightSearchDTO } from '../dto/avio-flight-search';
import { AvioFlight } from '../dto/avio-flight.model';
import { Router } from '../../../node_modules/@angular/router';
import { Authority } from '../dto/authority.model';
import { AuthorityDTO } from '../dto/authorityDTO.model';

@Component({
  selector: 'app-all-avio-companies',
  templateUrl: './all-avio-companies.component.html',
  styleUrls: ['./all-avio-companies.component.css'],
  providers: [AvioCompaniesService]
})
export class AllAvioCompaniesComponent implements OnInit {

  len: number;
  username: string;
  avioCompanies: AvioCompany[] = [];

  role: Authority = new Authority();
  roles: Authority[] = [];
  autoDTO: AuthorityDTO = new AuthorityDTO();


  user: boolean = false;
  adminHotel: boolean = false;
  adminRent: boolean = false;
  searchFlight: AvioflightSearchDTO;

  roundTrip: boolean = false;
  oneWay: boolean = true;
  multiCity: boolean = false;
  typeOfFlight: string = "oneWay";

  selectedFromLocation: boolean = false;
  selectedToLocation: boolean = false;

  searchFrom: string;
  fromLocation: Address = new Address;

  searchTo: string;
  toLocation: Address = new Address;

  departureDate: Date;
  returnDate: Date;

  travelers: number;

  destinations: Address[] = [];
  destination: Address = new Address;

  classFlight: string = "economy";

  searchResult: boolean = false;
  searchFlightsResult: AvioFlight[] = [];

  alreadyExistingFlight: AvioFlight;

  constructor(
    private avioCompanyService: AvioCompaniesService,
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit() {
    this.len = localStorage.length;
    this.username = localStorage.getItem('username');
    this.alreadyExistingFlight = JSON.parse(localStorage.getItem('flight'));
    console.log(localStorage);

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


    this.getAvioCompanies().subscribe(data => {
      this.avioCompanies = data;
    });

    this.getAllDestinations().subscribe(data => {
      this.destinations = data;
    });
  }

  setTypeOfFlight() {
    if (this.oneWay == true) {
      this.typeOfFlight = "oneWay";
    } else if (this.roundTrip == true) {
      this.typeOfFlight = "roundTrip";
    } else if (this.multiCity == true) {
      this.typeOfFlight = "multiCity";
    }
  }

  print() {
    if (this.fromLocation != undefined) {
      if (this.toLocation != undefined) {
        if (this.departureDate != undefined) {
          if (this.travelers != undefined) {
            this.searchFlight = new AvioflightSearchDTO(this.fromLocation,
              this.toLocation,
              this.departureDate,
              this.typeOfFlight,
              this.travelers);

            if (this.typeOfFlight == "roundTrip" && this.returnDate != null) {
              this.searchFlight.returnDate = this.returnDate;
            }
            this.searchFlight.classFlight = this.classFlight;
            this.searchFlights(this.searchFlight).subscribe(data => {
              this.searchResult = true;
              this.searchFlightsResult = data;
            });
          }
        }
      }
    }
  }

  discardSearch() {
    this.searchFlightsResult = [];
    this.searchResult = false;
    this.searchFrom = "";
    this.searchTo = "";
    this.fromLocation = new Address;
    this.toLocation = new Address;
    this.typeOfFlight = "economy";
    this.travelers = 1;
  }

  reservationFlight(flight: AvioFlight) {
    console.log(flight.startLocation.city);
    console.log(flight.endLocation.city);
    console.log(this.username);
    localStorage.setItem('flight', JSON.stringify(flight));
    localStorage.setItem('fligthClass', this.classFlight);
    localStorage.setItem('numberOfTravelers', JSON.stringify(this.travelers));
    localStorage.setItem('startDate', JSON.stringify(this.departureDate));

    this.router.navigate(['flight', flight.id, 'seats']);

  }


  setFromLocation(fromLocation: Address) {
    this.fromLocation = fromLocation;
    this.searchFrom = fromLocation.street + "," + fromLocation.city + "," + fromLocation.country;
  }

  setToLocation(toLocation: Address) {
    this.toLocation = toLocation;
    this.searchTo = toLocation.street + "," + toLocation.city + "," + toLocation.country;
  }

  logOut() {
    this.roles = [];
    this.userService.logOut();
    this.adminHotel = false;
    this.adminRent = false;
    this.user = false;
    this.router.navigate['home'];
  }

  public getAvioCompanies(): Observable<AvioCompany[]> {
    return this.avioCompanyService.getAvioCompanies();
  }

  public getAllDestinations() {
    return this.avioCompanyService.getAllDestinations();
  }

  public searchFlights(tempSearch: AvioflightSearchDTO): Observable<any> {
    return this.avioCompanyService.searchFlights(tempSearch);
  }

  public getRoles(username: string): Observable<AuthorityDTO> {
    return this.userService.getRoles(username);
  }
}
