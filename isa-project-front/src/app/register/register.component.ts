import { Component, OnInit, Input } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from '../../../node_modules/rxjs';
import { UserDTO } from '../dto/user.model';
import { UserService } from '../user.service';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user : UserDTO = new UserDTO();
  confPassword : string;
  colors : string[] = ['black','black','black','black','black','black','black','black'];

  constructor(private http: HttpClient, private userService : UserService) { }

  ngOnInit() {
    this.user.city = ""; 
    this.user.username = "";
    this.user.telephoneNumber = "";
    this.user.password = "";
    this.user.email = "";
    this.user.lastName = "";
    this.user.name = "";
    this.confPassword = "";

  }

  registerUser() {
    if(!(this.confPassword === this.user.password)) {
      alert("Password do not match");
    } else if (!this.validateEmpty()) {
      alert("Please fill in all required fields");
    } else if (!this.validateUsername()) {
      alert("Username length must be minimum 4!");
    } else if (!this.validatePassword()) {
      alert("Password length must be minimum 4!");
    }
    else {
      for (let i = 0 ; i < this.colors.length ; i++) {
         this.colors[i] = 'black';
      }
      this.userService.createUser(this.user);
      alert("Go to your email to activate your account");
      this.user = new UserDTO();
      this.confPassword = "";
    }
  }
 
  onSubmit() {
    //this.submitted = true;
    this.registerUser();
  }

  validateEmpty() {
     
    let success = true;

    if(this.user.name == "") {
      this.colors[0] = 'red';
      success = false;
    }
    this.colors[0] = 'black';

    if(this.user.lastName == "") {
      this.colors[1] = 'red';
      success = false;
    } else {
      this.colors[1] = 'black';
    }
    
    if(this.user.password == "") {
      this.colors[2] = 'red';
      success = false;
    } else {
      this.colors[2] = 'black';
    }

    if(this.confPassword == "") {
      this.colors[3] = 'red';
      success = false;
    } else {
      this.colors[3] = 'black';
    }
    
    if(this.user.email == "") {
      this.colors[4] = 'red';
      success = false;
    } else {
      this.colors[4] = 'black';
    }
    

    if(this.user.city == "") {
      this.colors[6] = 'red';
      success = false;
    } else {
      this.colors[6] = 'black';
    }
    

    if(this.user.username == "") {
      this.colors[7] = 'red';
      success = false;
    } else {
      this.colors[7] = 'black';
    }
    

    return success;
  }

  validateUsername() {
    if(this.user.username.length < 4) {
      this.colors[7] = 'red';
      return false;
    }

    this.colors[7] = 'black';
    return true;
  }

  validatePassword() {
    if(this.user.password.length < 4) {
      this.colors[2] = 'red';
      return false;
    }

    this.colors[4] = 'black';
    return true;
  }



}
