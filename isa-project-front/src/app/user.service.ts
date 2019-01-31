import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from '../../node_modules/rxjs';
import { LoginRequest } from './dto/loginRequest.model';
import { map } from 'rxjs/operators';
//import 'rxjs/Rx';
import { TokenState } from './dto/tokenState.model';
import { UserDTO } from './dto/user.model';



@Injectable()
export class UserService {

  private baseUrl = 'http://localhost:9004/users';
  private headers = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) }

  constructor(private http: HttpClient) { }

  getUser(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  //temp metoda za uzimanje preko username 
  getUserByUsername(username: string): Observable<UserDTO> {
    return this.http.get<UserDTO>("http://localhost:9004/users/" + username, { responseType: 'json' });
  }

  //ovo ako skontamo kako preko id
  getFriendsOfUser(id: number): Observable<any> {
    return this.http.get("http://localhost:9004/users/" + id + "/friends", { responseType: 'json' });
  }

  //do tad koristimo username
  getFriendsOfUserByUsername(username: string): Observable<any> {
    return this.http.get("http://localhost:9004/users/" + username + "/friendsList", { responseType: 'json' });
  }

  removeFriend(id: number, idFriend: number): Observable<any> {
    return this.http.delete("http://localhost:9004/users/" + id + "/friends/" + idFriend, { responseType: 'text' });
  }

  getUsersForSearch(username: string): Observable<any> {
    return this.http.get("http://localhost:9004/users/" + username + "/usersToSearch", { responseType: 'json' });
  }


  createUser(user: Object) {
    console.log('usao u servis regitracije angulara');
    this.http.post(`${this.baseUrl}` + `/register`, user)
      .subscribe((data) => {
        console.log(data);
      })
  }

  logInUser(logInRequest: LoginRequest): Observable<TokenState> {
    console.log('usao u servis login angulara');
    return this.http.post<TokenState>(`http://localhost:9004/auth/login`, logInRequest, this.headers);//mozda ne ;
  }

  logOut() {
    localStorage.removeItem('userToken');
    localStorage.removeItem('username');

    //this.http.post<TokenState>(`http://localhost:9004/auth/logout`, this.headers);
  }



  updateUser(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteUser(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getUsersList(): Observable<any> {
    return this.http.get(`${this.baseUrl}/all`);
  }

  getUsersByAge(age: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/age/${age}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(`${this.baseUrl}` + `/delete`, { responseType: 'text' });
  }

}
