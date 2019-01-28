import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { HttpClient } from '@angular/common/http';
import { LoginRequest } from '../dto/loginRequest.model';
import { Router} from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginRequest : LoginRequest = new LoginRequest();

  constructor(private http: HttpClient, private userService : UserService, private router : Router) {}

  ngOnInit() {}

  logIn() {
    this.userService.logInUser(this.loginRequest).subscribe((data) => {
      alert('Uspesno logovanje!');
      localStorage.setItem('userToken',data.accessToken);
      localStorage.setItem('username',this.loginRequest.username);
      //da li expires isto
      //localStorage.setItem('roles', data.), mozda novi http poziv za getRoles ili mozda ne treba pa svaki put zahtev
      
      //trigeri izgleda
      this.router.navigate(['home']);
    },
    (err: any) => { 
      console.log(err.status); console.log(err); 
      alert('Neuspesno logovanje');
    });
    
    console.log('Ulogovan korisnik ' + this.loginRequest.username + ' ' + this.loginRequest.password);  
  }
  
  onSubmit() {
     this.logIn();
  }


}
