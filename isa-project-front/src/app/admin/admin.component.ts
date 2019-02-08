import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Hotel } from '../dto/hotel.model';
import { HotelsService } from '../all-hotels/hotels.service';
import { AvioCompaniesService } from '../all-avio-companies/avio-companies.service';
import { RentACarService } from '../all-rent-a-cars/rent-a-car.service';
import { AvioCompany } from '../dto/avio-company.model';
import { RentACar } from '../dto/rent-a-car.model';
import { UserDTO } from '../dto/user.model';
import { Address } from '../dto/address.model';
import { Router } from '../../../node_modules/@angular/router';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  len: number;
  username: string;
  avio: AvioCompany = new AvioCompany();
  hotel : Hotel = new Hotel();
  address: Address = new Address();
  admin : UserDTO = new UserDTO();
  car : RentACar = new RentACar();

  constructor(
    private userService: UserService,
    private hotelService: HotelsService,
    private avioCompanyService: AvioCompaniesService,
    private rentACarService: RentACarService,
    private router: Router) { }

  ngOnInit() {
    this.len = localStorage.length;
    this.username = localStorage.getItem('username');
    console.log(localStorage);
  }

  logOut() {
    this.userService.logOut();
    this.router.navigate['home'];
  }

  public addAvioCompany() {
    this.avio.address = this.address;
    if(!(this.avio.name == null) && !(this.avio.name === '')) {
      this.avioCompanyService.addAvioCompany(this.avio);
      alert("Successfully added avio company");
    } else {
      alert("Fileds with * are mandatory");
    } 
    this.address = new Address();
    this.avio = new AvioCompany();
  }

  public addHotel() {
    this.hotel.addressDTO = this.address;
    if(!(this.hotel.name == undefined) && !(this.avio.name === '')) {
      console.log(this.hotel);
      this.hotelService.addHotel(this.hotel);
      alert("Successfully added hotel");
    } else {
      alert("Fileds with * are mandatory");      
    }
    this.address = new Address();
    this.hotel = new Hotel();
  }

  public addRentACar() {
    this.car.addressDTO = this.address;
    if(!(this.car.name == undefined) && !(this.car.name === '')) {
      console.log(this.car);
      this.rentACarService.addRentACar(this.car);
    } else {
      alert("Fileds with * are mandatory");
    }
    this.address = new Address();
    this.car = new RentACar();
  }

  public addAdmin() {
    if(!(this.admin.username == undefined) && !(this.admin.username === '')) {
      if(!(this.admin.password == undefined) && !(this.admin.password === '')) {
        if(!(this.admin.email == undefined) && !(this.admin.email === '')) {
          if(!(this.admin.role == undefined)) {
            this.userService.addUser(this.admin);
            alert("Successfully added admin");
          }
        }
      }
    } else {
      alert("Fileds with * are mandatory");
    }
    this.admin = new UserDTO();
  }

  onSubmit() {
  }


}
