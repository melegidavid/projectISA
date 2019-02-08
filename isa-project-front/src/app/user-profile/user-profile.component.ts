import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { UserDTO } from '../dto/user.model';
import { Observable, fromEventPattern } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { InviteForFlight } from '../dto/inviteForFlight';
import { AvioCompaniesService } from '../all-avio-companies/avio-companies.service';
import { AvioFlight } from '../dto/avio-flight.model';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css'],
  providers: [UserService]

})
export class UserProfileComponent implements OnInit {

  len: number;
  username: string;
  user: UserDTO = new UserDTO();
  friend: UserDTO = new UserDTO();
  friends: UserDTO[] = [];
  private sub: any;
  id: number;
  friendId: number;

  users: UserDTO[] = [];
  firstNameText: string;
  lastNameText: string;

  usersToSearch: UserDTO[] = [];

  newFriend: UserDTO = new UserDTO();
  requests: UserDTO[] = [];
  request: UserDTO;
  numberOfRequests: number;

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

  invites: InviteForFlight[] = [];
  numberOfInvites: number;
  invite: InviteForFlight = new InviteForFlight();
  tempFlight: AvioFlight = new AvioFlight();
  inviteID: number;



  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router,
    private avioService: AvioCompaniesService
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

      this.getUserFriendsByUsername(this.username).subscribe(data => {
        this.friends = data;

      });

      this.getUserList().subscribe(data => {
        this.users = data;
      });

      this.getUsersToSearch(this.username).subscribe(data => {
        this.usersToSearch = data;
      });

      this.getUsersRequests(this.username).subscribe(data => {
        this.requests = data;
        this.numberOfRequests = this.requests.length;
      });

      this.getInvites(this.username).subscribe(data => {
        this.invites = data;
        console.log(this.invites);
        this.numberOfInvites = this.invites.length;
        console.log(this.numberOfInvites);
      });

    }

  }

  addFriend(newFriend: UserDTO) {
    this.newFriend = newFriend;
    this.userService.addFriend(this.user.id, this.newFriend).subscribe(data => {
      this.getUsersToSearch(this.username).subscribe(data => {
        this.usersToSearch = data;
      });

    });
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

  acceptFriendship(idFriend: number) {
    this.friendId = idFriend;
    this.userService.acceptFriendship(this.user.id, this.friendId).subscribe(data => {
      console.log(data);
      this.getUsersRequests(this.username).subscribe(data => {
        this.requests = data;
      });

      this.getUserFriendsByUsername(this.username).subscribe(data => {
        this.friends = data;
      });

      this.getUsersToSearch(this.username).subscribe(data => {
        this.usersToSearch = data;
      });

      this.getUsersRequests(this.username).subscribe(data => {
        this.requests = data;
        this.numberOfRequests = this.requests.length;
      });

    })
  }

  declineFriendship(idFriend: number) {
    this.friendId = idFriend;
    this.userService.declineFriedship(this.id, this.friendId).subscribe(data => {
      console.log(data);
      this.getUsersToSearch(this.username).subscribe(data => {
        this.usersToSearch = data;
      });

      this.getUsersRequests(this.username).subscribe(data => {
        this.requests = data;
        this.numberOfRequests = this.requests.length;
      });
    });
  }

  acceptInvite(invite: InviteForFlight) {
    console.log(invite);
    this.userService.acceptInvite(this.id, invite).subscribe(data => {
      console.log(data);
      this.getInvites(this.username).subscribe(data => {
        this.invites = data;
        this.numberOfInvites = this.invites.length;
      });
    });

    
  }

  declineInvite(invite: InviteForFlight) {
    this.inviteID = invite.id;
    this.tempFlight = invite.avioFlightDTO;
    this.avioService.declineInvite(this.tempFlight.id, this.id, this.inviteID).subscribe(data =>{
      console.log("DECLINE: ");
      console.log(data);
      this.getInvites(this.username).subscribe(data => {
        this.invites = data;
        console.log(data);
        this.numberOfInvites = this.invites.length;
      });
    });
  }

  showRequests(){
    this.getUsersRequests(this.username).subscribe(data => {
      this.requests = data;
      console.log(data);
      this.numberOfRequests = this.requests.length;
    });
  }

  showInvites(){
    this.getInvites(this.username).subscribe(data=>{
      this.invites = data;
      console.log("INVITES");
      console.log(data);
      
      this.numberOfInvites = this.invites.length;
    });
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

  public getUserFriendsByUsername(username: string): Observable<any> {
    return this.userService.getFriendsOfUserByUsername(username);
  }

  public getUserList(): Observable<any> {
    return this.userService.getUsersList();
  }

  public getUsersToSearch(username: string): Observable<any> {
    return this.userService.getUsersForSearch(username);
  }

  public getUsersRequests(username: string): Observable<any> {
    return this.userService.getUsersRequest(username);
  }

  public getInvites(username: string): Observable<InviteForFlight[]> {
    return this.userService.getInvites(username);
  }

}
