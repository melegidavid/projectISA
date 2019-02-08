import { Component, OnInit } from '@angular/core';
import { UserDTO } from '../dto/user.model';
import { UserService } from '../user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-admin-rent-car-profile',
  templateUrl: './admin-rent-car-profile.component.html',
  styleUrls: ['./admin-rent-car-profile.component.css']
})
export class AdminRentCarProfileComponent implements OnInit {
  len: number;
  id: number;
  username: string;
  user: UserDTO = new UserDTO();

  edit: boolean = false;
  changePassword: boolean = false;
  changedUser: UserDTO = new UserDTO();

  inputUsername: string;
  inputPassword: string;
  inputOldPassword: string;
  inputNewPassword: string;
  inputName: string;
  inputLastName: string;
  inputEmail: string;
  inputCity: string;
  inputTelephoneNumber: string;

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router,

  ) { }

  ngOnInit() {
    this.len = localStorage.length;
    this.username = localStorage.getItem('username');

    if (this.len > 0) {
      this.getUserByUsername(this.username).subscribe(data => {
        this.user = data;
        this.id = this.user.id;
        this.changedUser = this.user;
        this.inputUsername = this.user.username;
        this.inputPassword = this.user.password;
        console.log(this.inputPassword);
        this.inputName = this.user.name;
        this.inputLastName = this.user.lastName;
        this.inputEmail = this.user.email;
        this.inputCity = this.user.city;
        this.inputTelephoneNumber = this.user.telephoneNumber;
      });
    }
  }

  saveChangePassword() {
    this.userService.changePassword(this.user.id, this.inputOldPassword, this.inputNewPassword).subscribe(data => {
      this.changePassword = false;
      this.logOut();
      this.router.navigate(['auth/login']);
    });
  }

  discardChangePassword() {
    this.inputPassword = this.user.password;
    this.inputOldPassword = "";
    this.inputNewPassword = "";
    this.changePassword = false;
  }

  saveChangedData() {
    this.changedUser.name = this.inputName;
    this.changedUser.lastName = this.inputLastName;
    this.changedUser.email = this.inputEmail;
    this.changedUser.telephoneNumber = this.inputTelephoneNumber;
    this.changedUser.city = this.inputCity;
    this.edit = false;

    this.userService.updateUser(this.user.id, this.changedUser).subscribe(data => {
      this.user = data;
    });
  }
  discardChangedData() {
    this.inputName = this.changedUser.name;
    this.inputLastName = this.changedUser.lastName;
    this.inputEmail = this.changedUser.email;
    this.inputTelephoneNumber = this.changedUser.telephoneNumber;
    this.inputCity = this.changedUser.city;
    this.edit = false;
  }

  logOut() {
    console.log('usao u logout');
    this.userService.logOut();

    console.log('ostalo ' + localStorage.length);
    //this.ngOnInit();
  }

  public getUserByUsername(username: string): Observable<UserDTO> {
    return this.userService.getUserByUsername(username);
  }
}
