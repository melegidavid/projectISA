import { Component, OnInit } from '@angular/core';
import { Hotel } from '../dto/hotel.model';
import { HotelRoom } from '../dto/hotel-room.model';
import { HotelMenuItem } from '../dto/hotel-menu-item.model';
import { UserService } from '../user.service';
import { HotelsService } from '../all-hotels/hotels.service';
import { ActivatedRoute, Router } from '../../../node_modules/@angular/router';
import { Authority } from '../dto/authority.model';
import { AuthorityDTO } from '../dto/authorityDTO.model';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-hotel-edit',
  templateUrl: './hotel-edit.component.html',
  styleUrls: ['./hotel-edit.component.css']
})
export class HotelEditComponent implements OnInit {

  private sub: any;
  id: number;

  role: Authority = new Authority();
  roles: Authority[] = [];
  autoDTO: AuthorityDTO = new AuthorityDTO();
  username: string;

  user: boolean = false;
  adminHotel: boolean = false;
  adminRent: boolean = false;


  hotel: Hotel = new Hotel();
  rooms: HotelRoom[] = [];
  menu: HotelMenuItem[] = [];

  roomsToRemove: HotelRoom[] = [];
  itemsToRemove: HotelMenuItem[] = [];

  roomToAdd: HotelRoom = new HotelRoom();
  itemToAdd: HotelMenuItem = new HotelMenuItem();

  roomToUpdate: HotelRoom = new HotelRoom();
  itemToUpdate: HotelMenuItem = new HotelMenuItem();

  showAddDiv: boolean = false;
  showUpdateDiv: boolean = false;

  showAddItemDiv: boolean = false;
  showUpdateItemDiv: boolean = false;


  constructor(
    private userService: UserService,
    private hotelService: HotelsService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.hotel.name = "";
    this.hotel.description = "";
    this.username = localStorage.getItem('username');

    if (this.username) {
      this.getRoles(this.username).subscribe(data => {
        this.autoDTO = data;
        this.roles = this.autoDTO.authorities;

        if (this.roles[0].authority === 'HOTEL_ADMIN') {
          this.user = false;
          this.adminHotel = true;
          this.adminRent = false;
        } else if (this.roles[0].authority === 'RENT_CAR_ADMIN') {
          this.user = false;
          this.adminHotel = false;
          this.adminRent = true;
        } else if (this.roles[0].authority === 'REGISTERED_USER') {
          this.user = true;
          this.adminHotel = false;
          this.adminRent = false;
        }
      });
    }


    this.restoreAddRoom();
    this.restoreItem(this.itemToUpdate);
    this.restoreItem(this.itemToAdd);

    this.sub = this.route.params.subscribe(params => {
      this.id = + params['id'];
    });

    this.hotelService.getHotel(this.id).subscribe(data => {
      this.hotel = data;
    });

    this.hotelService.getHotelRooms(this.id).subscribe(data => {
      this.rooms = data;
    });

    this.hotelService.getHotelMenu(this.id).subscribe(data => {
      this.menu = data;
    });
  }

  toggleAdd() {
    this.showAddDiv = !this.showAddDiv;
    this.restoreAddRoom();

  }

  toggleAddItem() {
    this.showAddItemDiv = !this.showAddItemDiv;
    this.restoreItem(this.itemToAdd);

  }

  toggleUpdate(id: number) {
    this.showUpdateDiv = !this.showUpdateDiv;
    if (this.showUpdateDiv == true) {
      this.hotelService.getHotelRoom(id).subscribe(data => {
        this.roomToUpdate = data;
      });
    }
  }

  toggleUpdateItem(id: number) {
    this.showUpdateItemDiv = !this.showUpdateItemDiv;
    if (this.showUpdateItemDiv == true) {
      this.hotelService.getHotelMenuItem(this.id, id).subscribe(data => {
        this.itemToUpdate = data;
      });
    }
  }

  restoreAddRoom() {
    this.roomToAdd.number = "";
    this.roomToAdd.description = "";
    this.roomToAdd.price = null;
    this.roomToAdd.bedNumber = null;
  }

  restoreItem(item: HotelMenuItem) {
    item.serviceName = "";
    item.price = null;
    item.description = "";
    item.id = null;

    item.hotel = new Hotel();
    item.hotel.id = this.id;
  }

  addRoom() {
    this.hotelService.addHotelRoom(this.id, this.roomToAdd).subscribe(data => {
      this.restoreAddRoom();
      this.hotelService.getHotelRooms(this.id).subscribe(data => {
        this.rooms = data;
      });
      this.showAddDiv = !this.showAddDiv;
    })
  }

  addItem() {
    this.hotelService.addHotelMenuItem(this.id, this.itemToAdd).subscribe(data => {

      this.hotelService.getHotelMenu(this.id).subscribe(data => {
        this.menu = data;
        this.showAddItemDiv = !this.showAddItemDiv;
        this.restoreItem(this.itemToAdd);
      });
    });
  }

  updateRoom() {
    this.hotelService.updateHotelRoom(this.id, this.roomToUpdate.id, this.roomToUpdate).subscribe(data => {

      this.hotelService.getHotelRooms(this.id).subscribe(data => {
        this.rooms = data;
        this.showUpdateDiv = !this.showUpdateDiv;
      });
    });
  }

  updateItem() {
    this.hotelService.updateHotelMenuItem(this.id, this.itemToUpdate.id, this.itemToUpdate).subscribe(data => {

      this.hotelService.getHotelMenu(this.id).subscribe(data => {
        this.menu = data;
        this.showUpdateItemDiv = !this.showUpdateItemDiv;
      });
    });
  }

  onSubmit() {
    this.doUpdateHotel();
    this.removeRooms();
    this.removeItems();

    alert('Uspesno izvrsene promene');
    this.router.navigate(['hotels', this.id]);
  }

  doUpdateHotel() {
    this.hotelService.updateHotel(this.hotel);
  }

  hideRoom(idRoom: any) {
    for (let x of this.rooms) {
      if (x.id == idRoom) {
        const index = this.rooms.indexOf(x, 0);
        if (index > -1) {
          this.roomsToRemove.push(x);
          this.rooms.splice(index, 1);
          break;
        }
      }
    }
  }

  hideItem(idItem: any) {
    for (let x of this.menu) {
      if (x.id == idItem) {
        const index = this.menu.indexOf(x, 0);
        if (index > -1) {
          this.itemsToRemove.push(x);
          this.menu.splice(index, 1);
          break;
        }
      }
    }
  }

  removeRooms() {
    for (let r of this.roomsToRemove) {
      this.hotelService.deleteHotelRoom(r.id);
    }
  }

  removeItems() {
    for (let i of this.itemsToRemove) {
      this.hotelService.deleteHotelMenuItem(i.id);
    }
  }

  logOut() {
    this.roles = [];
    this.userService.logOut();
    this.adminHotel = false;
    this.adminRent = false;
    this.user = false;
    this.router.navigate['home'];
  }

  public getRoles(username: string): Observable<AuthorityDTO> {
    return this.userService.getRoles(username);
  }

}
