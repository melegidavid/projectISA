import { Component, OnInit } from '@angular/core';
import { AdminAvioCompanyService } from './admin-avio-company.service';
import { AvioCompany } from '../dto/avio-company.model';
import { AvioCompaniesService } from '../all-avio-companies/avio-companies.service';
import { Router } from '../../../node_modules/@angular/router';
import { UserService } from '../user.service';
import { Observable } from 'rxjs';
import { Address } from '../dto/address.model';
import { AvioFlight } from '../dto/avio-flight.model';
import { DatePipe } from '@angular/common';
import { UserDTO } from '../dto/user.model';


@Component({
  selector: 'app-admin-avio-company',
  templateUrl: './admin-avio-company.component.html',
  styleUrls: ['./admin-avio-company.component.css']
})
export class AdminAvioCompanyComponent implements OnInit {

  len: number;
  username: string;
  id: number;

  admin: UserDTO;
  changedUser: UserDTO;
  inputUsername: string;
  inputNameAdmin: string;
  inputLastName: string;
  inputEmail: string;
  inputCityAdmin: string;
  inputTelephoneNumber: string;
  editAdmin = false;



  select: boolean = false;
  edit: boolean = false;
  editAvioCompany: boolean = false;

  avioCompanies: AvioCompany[] = [];
  avioCompany: AvioCompany = new AvioCompany;

  addressOfAvioCompany: Address = new Address;
  changedAvioComapny: AvioCompany = new AvioCompany;
  inputName: string;
  inputDescription: string;


  destinations: Address[] = [];
  destination: Address = new Address;
  newDestination: Address = new Address;
  addDestinationFlag: boolean = false;

  inputCountry: string;
  inputCity: string;
  inputPostalCode: number;
  inputStreet: string;
  inputNumber: number;





  flights: AvioFlight[] = [];
  flight: AvioFlight = new AvioFlight;
  changedFlight: AvioFlight = new AvioFlight;


  inputStartLocation: number;
  inputEndLocation: number;
  inputDateTimeStart: Date;
  inputDateTimeFinish: Date;
  inputDistance: number;
  inputDuration: number;
  inputPrice: number;
  inputEconomy: number;
  inputBusiness: number;
  inputFirst: number;

  editFlight: boolean = false;
  addFlightFlag: boolean = false;


  constructor(
    private userService: UserService,
    private avioCompanyService: AvioCompaniesService,
    private adminAvioCompanyService: AdminAvioCompanyService,
    private router: Router
  ) { }

  ngOnInit() {
    this.len = localStorage.length;
    this.username = localStorage.getItem('username');
    console.log(localStorage);

    if (this.len > 0) {
      this.getUserByUsername(this.username).subscribe(data => {
        this.admin = data;
        this.changedUser = this.admin;
        this.inputUsername = this.admin.username;
        this.inputNameAdmin = this.admin.name;
        this.inputLastName = this.admin.lastName;
        this.inputEmail = this.admin.email;
        this.inputCityAdmin = this.admin.city;
        this.inputTelephoneNumber = this.admin.telephoneNumber;
      });
      this.getAllAvioCompanies().subscribe(data => {
        this.avioCompanies = data;
      });
    }
  }

  saveChangedData() {
    this.changedUser.name = this.inputNameAdmin;
    this.changedUser.lastName = this.inputLastName;
    this.changedUser.email = this.inputEmail;
    this.changedUser.telephoneNumber = this.inputTelephoneNumber;
    this.changedUser.city = this.inputCityAdmin;
    this.editAdmin = false;
    this.userService.updateUser(this.admin.id, this.changedUser).subscribe(data => {
      this.admin = data;
    });
  }
  discardChangedData() {
    this.inputNameAdmin = this.changedUser.name;
    this.inputLastName = this.changedUser.lastName;
    this.inputEmail = this.changedUser.email;
    this.inputTelephoneNumber = this.changedUser.telephoneNumber;
    this.inputCityAdmin = this.changedUser.city;
    this.editAdmin = false;
  }

  setAvioCompany(id: number) {
    this.id = id;
    this.getAvioCompany(this.id).subscribe(data => {
      this.avioCompany = data;
      this.avioCompany = this.avioCompany;
      this.id = this.avioCompany.id;
      this.changedAvioComapny = this.avioCompany;
      this.inputName = this.changedAvioComapny.name;
      this.inputDescription = this.changedAvioComapny.description;
      this.addressOfAvioCompany = this.avioCompany.address;
    });

    this.getFlightsOfAvioComapny(this.id).subscribe(data => {
      this.flights = data;
    });

    this.getDestinationsOfAvioCompany(this.id).subscribe(data => {
      this.destinations = data;
    });
  }


  saveChangedAvioCompany() {
    this.changedAvioComapny.name = this.inputName;
    this.changedAvioComapny.description = this.inputDescription;
    this.editAvioCompany = false;
    this.adminAvioCompanyService.updateAvioCompany(this.id, this.changedAvioComapny).subscribe(data => {
      this.avioCompany = data;
      this.setAvioCompany(this.avioCompany.id);
    });
  }

  discardChangedAvioCompany() {
    this.inputName = this.changedAvioComapny.name;
    this.inputDescription = this.changedAvioComapny.description;
    this.editAvioCompany = false;

  }

  addDestination() {
    this.addDestinationFlag = false
    this.newDestination.country = this.inputCountry;
    this.newDestination.city = this.inputCity;
    this.newDestination.postalCode = this.inputPostalCode;
    this.newDestination.street = this.inputStreet;
    this.newDestination.number = this.inputNumber;

    this.adminAvioCompanyService.addDestination(this.id, this.newDestination).subscribe(data => {
      this.getDestinationsOfAvioCompany(this.id).subscribe(data => {
        this.destinations = data;
      });
    });


  }

  removeDestination(idDestination: number) {
    this.adminAvioCompanyService.removeDestination(this.id, idDestination).subscribe(data => {
      this.getFlightsOfAvioComapny(this.id).subscribe(data => {
        this.flights = data;
      });
      this.getDestinationsOfAvioCompany(this.id).subscribe(data => {
        this.destinations = data;
      });
    });
  }

  saveChangedFlight() {
    for (let dest of this.destinations) {
      if (dest.id == this.inputStartLocation) {
        this.changedFlight.startLocation = dest;
      }
    }
    for (let dest of this.destinations) {
      if (dest.id == this.inputEndLocation) {
        this.changedFlight.endLocation = dest;
      }
    }
    this.changedFlight.dateTimeStart = this.inputDateTimeStart;
    this.changedFlight.dateTimeFinish = this.inputDateTimeFinish;
    this.changedFlight.flightDistance = this.inputDistance;
    this.changedFlight.flightDuration = this.inputDuration;
    this.changedFlight.price = this.inputPrice;

    this.adminAvioCompanyService.updateFlight(this.id, this.changedFlight.id, this.changedFlight).subscribe(data => {
      this.flight = data;
      this.editFlight = false;
      this.getFlightsOfAvioComapny(this.id).subscribe(data => {
        this.flights = data;
      });
    });
  }

  discardChangedFlight() {
    this.editFlight = false;
  }

  resetInput() {
    this.inputStartLocation = 0;
    this.inputEndLocation = 0;
    this.inputDateTimeFinish = new Date;
    this.inputDateTimeStart = new Date;
    this.inputDuration = 0;
    this.inputDistance = 0;
    this.inputPrice = 0;
    this.inputEconomy = 0;
    this.inputBusiness = 0;
    this.inputFirst = 0;
    this.addFlightFlag = true;
    this.editFlight = false;
  }


  addFlight() {
    for (let dest of this.destinations) {
      if (dest.id == this.inputStartLocation) {
        this.changedFlight.startLocation = dest;
      }
    }
    for (let dest of this.destinations) {
      if (dest.id == this.inputEndLocation) {
        this.changedFlight.endLocation = dest;
      }
    }
    this.changedFlight.dateTimeStart = this.inputDateTimeStart;
    this.changedFlight.dateTimeFinish = this.inputDateTimeFinish;
    this.changedFlight.flightDistance = this.inputDistance;
    this.changedFlight.flightDuration = this.inputDuration;
    this.changedFlight.price = this.inputPrice;
    this.changedFlight.economyClassSeats = this.inputEconomy;
    this.changedFlight.businessClassSeats = this.inputBusiness;
    this.changedFlight.firstClassSeats = this.inputFirst;

    this.adminAvioCompanyService.addFlight(this.id, this.changedFlight).subscribe(data => {
      this.flight = data;
      this.addFlightFlag = false;
      this.getFlightsOfAvioComapny(this.id).subscribe(data => {
        this.flights = data;
      });
    });
  }

  removeFlight(idFlight: number) {
    this.adminAvioCompanyService.removeFlight(this.id, idFlight).subscribe(data => {
      this.getFlightsOfAvioComapny(this.id).subscribe(data => {
        this.flights = data;
      });
    });
  }




  setflight(flight: AvioFlight) {
    this.flight = flight;
    this.changedFlight = flight;
    this.inputStartLocation = this.changedFlight.startLocation.id;
    this.inputEndLocation = this.changedFlight.endLocation.id;
    this.inputDateTimeFinish = this.changedFlight.dateTimeFinish;
    this.inputDateTimeStart = this.changedFlight.dateTimeStart;
    this.inputDuration = this.changedFlight.flightDuration;
    this.inputDistance = this.changedFlight.flightDistance;
    this.inputPrice = this.changedFlight.price;
    this.inputEconomy = this.changedFlight.economyClassSeats;
    this.inputBusiness = this.changedFlight.businessClassSeats;
    this.inputFirst = this.changedFlight.firstClassSeats;
  }

  logOut() {
    this.userService.logOut();
    //this.ngOnInit();
    this.router.navigate['home'];
  }

  public getAllAvioCompanies(): Observable<any> {
    return this.avioCompanyService.getAvioCompanies();
  }
  public getAvioCompany(id: number): Observable<any> {
    return this.avioCompanyService.getAvioCompany(id);
  }

  public getFlightsOfAvioComapny(id: number): Observable<any> {
    return this.avioCompanyService.getAvioFlights(id);
  }

  public getDestinationsOfAvioCompany(id: number): Observable<any> {
    return this.adminAvioCompanyService.getDestinationsOfAvioCompany(id);
  }

  public getUserByUsername(username: string): Observable<UserDTO> {
    return this.userService.getUserByUsername(username);
  }

}
