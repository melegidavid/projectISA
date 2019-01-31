import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { UserDTO } from '../dto/user.model';
import { Observable, fromEventPattern } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css'],
  providers: [UserService]

})
export class UserProfileComponent implements OnInit {

  len: number;
  username: string;
  user: UserDTO;
  friend: UserDTO;
  friends: UserDTO[] = [];
  private sub: any;
  id: number;
  friendId: number;

  users: UserDTO[] = [];
  firstNameText: string;
  lastNameText: string;

  usersToSearch: UserDTO[] = [];

  constructor(
    private userService: UserService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.len = localStorage.length;
    this.username = localStorage.getItem('username');

    if (this.len > 0) {
      this.getUserByUsername(this.username).subscribe(data => {
        this.user = data;
      });

      this.getUserFriendsByUsername(this.username).subscribe(data => {
        this.friends = data;
      });

      this.getUserList().subscribe(data => {
        this.users = data;
      });

      this.getUsersToSearch(this.username).subscribe(data => {
        this.usersToSearch = data;
      });

    }

  }

  removeFriend(id: number, idFriend: number) {
    this.id = id;
    this.friendId = idFriend;
    this.userService.removeFriend(this.id, this.friendId).subscribe(data => {
      this.getUserFriendsByUsername(this.username).subscribe(data => {
        this.friends = data;
        console.log(this.friends.length);

      });
      this.getUsersToSearch(this.username).subscribe(data => {
        this.usersToSearch = data;
        console.log(this.usersToSearch);
      });

    });

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

  public getUserFriendsByUsername(username: string): Observable<any> {
    return this.userService.getFriendsOfUserByUsername(username);
  }

  public getUserList(): Observable<any> {
    return this.userService.getUsersList();
  }

  public getUsersToSearch(username: string): Observable<any> {
    return this.userService.getUsersForSearch(username);
  }
}
