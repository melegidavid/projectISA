import { Component, OnInit } from '@angular/core';
import { UserDTO } from '../dto/user.model';
import { UserService } from '../user.service';
import { Observable } from '../../../node_modules/rxjs';

@Component({
  selector: 'app-invite-friends',
  templateUrl: './invite-friends.component.html',
  styleUrls: ['./invite-friends.component.css']
})
export class InviteFriendsComponent implements OnInit {

  friends: UserDTO[] = [];
  username: string;
  user: UserDTO;

  selectedFriends: UserDTO[] = [];

  numberOfTravelers: number;
  numberOfFriends: number;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.username = localStorage.getItem('username');

    this.getUser(this.username).subscribe(data => {
      this.user = data;
      this.getFriends(this.user.id).subscribe(data => {
        this.friends = data;
      });
    });

    this.numberOfTravelers = JSON.parse(localStorage.getItem('numberOfTravelers'));
    this.numberOfFriends = this.numberOfTravelers - 1;
    console.log("broj prijatelja: " + this.numberOfFriends)
  }

  public getUser(username: string) : Observable<UserDTO> {
    return this.userService.getUserByUsername(username);
  }

  public getFriends(id: number) : Observable<UserDTO[]> {
    return this.userService.getFriendsOfUser(id);
  }

  public inviteFriend(friend: UserDTO) {
    if(friend.selected == undefined) {
      friend.selected = true;
      this.selectedFriends.push(friend);
    }
    console.log(this.selectedFriends.length);
  }

  public removeFriend(friend: UserDTO) {
    if(friend.selected == true) {
      friend.selected = undefined;
      let newFriends: UserDTO[] = [];
      this.selectedFriends.forEach(f => {
        if(f.id != friend.id) {
          newFriends.push(f);
        }
      });
      this.selectedFriends = newFriends;
      console.log(this.selectedFriends);
    }
  }

  

}
