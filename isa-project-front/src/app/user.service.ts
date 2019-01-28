import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from '../../node_modules/rxjs';
import { LoginRequest } from './dto/loginRequest.model';
import { map } from 'rxjs/operators';
//import 'rxjs/Rx';
import { TokenState } from './dto/tokenState.model';


@Injectable()
export class UserService {

  private baseUrl = 'http://localhost:9004/users';
  private headers = {headers: new HttpHeaders({'Content-Type':  'application/json'})}
 
  constructor(private http: HttpClient) { }
 
  getUser(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }
 
  createUser(user: Object){
    console.log('usao u servis regitracije angulara');
    this.http.post(`${this.baseUrl}` + `/register`, user)
    .subscribe((data) => {
      console.log(data);
      console.log('aaaa');
    })
  }

  logInUser(logInRequest : LoginRequest) : Observable<TokenState>  {
    console.log('usao u servis login angulara');
    return this.http.post<TokenState>(`http://localhost:9004/auth/login`, logInRequest, this.headers);//mozda ne ;
  }

  logOut() {
    localStorage.removeItem('userToken');
    //localStorage.removeItem('userRoles');
  }


 
  updateUser(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }
 
  deleteUser(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }
 
  getUsersList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
 
  getUsersByAge(age: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/age/${age}`);
  }
 
  deleteAll(): Observable<any> {
    return this.http.delete(`${this.baseUrl}` + `/delete`, { responseType: 'text' });
  }
}
