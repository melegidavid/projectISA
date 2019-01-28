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


  constructor(private http: HttpClient, private userService : UserService) { }

  ngOnInit() { 
    
   }

  registerUser() {
    this.userService.createUser(this.user);
    this.user = new UserDTO();
  }
 
  onSubmit() {
    //this.submitted = true;
    this.registerUser();
  }



}
