import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { HttpClient } from '@angular/common/http';
import { LoginRequest } from '../dto/loginRequest.model';
import { Router} from '@angular/router';
import { Observable } from '../../../node_modules/rxjs';
import { AuthorityDTO } from '../dto/authorityDTO.model';
import { Authority } from '../dto/authority.model';
import { forEach } from '../../../node_modules/@angular/router/src/utils/collection';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  autoDTO : AuthorityDTO;
  roles: Authority[];

  loginRequest : LoginRequest = new LoginRequest();

  constructor(private http: HttpClient, private userService : UserService, private router : Router) {}

  ngOnInit() {}

  logIn() {
    this.userService.logInUser(this.loginRequest).subscribe((data) => {
      alert('Successful login!');
      localStorage.setItem('userToken',data.accessToken);
      localStorage.setItem('username',this.loginRequest.username);
      
      //da li expires isto
      //localStorage.setItem('roles', data.), mozda novi http poziv za getRoles ili mozda ne treba pa svaki put zahtev
      this.getRoles(this.loginRequest.username)
      .subscribe(data => {
          this.autoDTO = data;
          this.roles = this.autoDTO.authorities;
          

          console.log(this.roles); 
         
          this.router.navigate(['home']);
          this.roles.forEach((role) => {
            console.log('Uloga u local storage-u je : ');
            console.log(role.authority);
            localStorage.setItem('role',role.authority);

            if(role.authority === 'SERVICE_ADMIN') {
                this.router.navigate(['admin']);
            }else if(role.authority === 'AVIO_COMPANY_ADMIN'){
              this.router.navigate(['admin-avio-company'])
            }
          });
         
      });

      //trigeri izgleda
    },
    (err: any) => { 
      console.log(err.status); console.log(err); 
      alert('Unsuccessful login. Please check your username and password!');
    });
    
    console.log('Ulogovan korisnik ' + this.loginRequest.username + ' ' + this.loginRequest.password);  
  }
  
  onSubmit() {
     this.logIn();
  }

  public getRoles(username: string): Observable<AuthorityDTO> {
    return this.userService.getRoles(username);
  }

}
