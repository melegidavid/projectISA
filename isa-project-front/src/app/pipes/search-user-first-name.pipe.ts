import { Pipe, PipeTransform } from '@angular/core';
import { UserService } from '../user.service';
import { UserDTO } from '../dto/user.model';

@Pipe({
  name: 'searchUserFirstName'
})
export class SearchUserFirstNamePipe implements PipeTransform {


  transform(users: UserDTO[], searchText: string): UserDTO[] {
    if (!users || !searchText) {
      return users;
    }

    searchText = searchText.trim();
    searchText = searchText.split(" ").join("");
    return users.filter(user =>
      (user.name.toLowerCase() + user.lastName.toLowerCase()).indexOf(searchText.toLowerCase()) !== -1
    );

  }
}