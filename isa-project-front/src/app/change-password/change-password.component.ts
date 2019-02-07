import { Component, OnInit } from '@angular/core';
import { UserDTO } from '../dto/user.model';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  username: string;
  oldPassword: string;
  newPassword: string;
  user: UserDTO;

  constructor(private userService: UserService,
    private router: Router) { }

  ngOnInit() {
    this.username = localStorage.getItem('username');
    if (this.username != undefined || this.username != null) {
      this.userService.getUserByUsername(this.username).subscribe(data => {
        this.user = data;
      });
    }
  }


  changePassword() {
    if (this.user != null) {
      this.userService.changePassword(this.user.id, this.oldPassword, this.newPassword);
    }
  }

}
