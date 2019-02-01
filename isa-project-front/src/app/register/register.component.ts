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

  constructor(private http: HttpClient, private userService : UserService) { }

  ngOnInit() { 
    
  }

  registerUser() {
    if(!(this.confPassword === this.user.password)) {
      alert("Password do not match");
    } else {
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



}
