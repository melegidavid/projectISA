import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/user.service';
import { VehicleReservationDTO } from 'src/app/dto/vehicleReservationDTO';
import { RoomReservation } from 'src/app/dto/room-reservation.model';
import { AvioFlightReservation } from 'src/app/dto/avio-flight-reservation';
import { DatePipe } from '@angular/common';


@Component({
  selector: 'app-user-history',
  templateUrl: './user-history.component.html',
  styleUrls: ['./user-history.component.css']
})
export class UserHistoryComponent implements OnInit {

  vehicleReservations : VehicleReservationDTO[] = [];
  roomReservations : RoomReservation[] = [];
  avioReservations : AvioFlightReservation[] = [];

  constructor(private userService: UserService) { }//injektuj servis za rezervaciju

  ngOnInit() {
   
    this.getVehicleReservations();
    this.getHotelReservations();
    this.getAvioReservations();
  }

  yetToCome(startReservation : Date) : boolean {
    let dateParts : string[];
    let dateString = startReservation.toString();
    dateParts = dateString.split('T');
  
    let dateComponents : string[];
    dateComponents = dateParts[0].split('-');
 
    let day = dateComponents[2];
    let month = dateComponents[1];
    let year = dateComponents[0];


    let myDate = new Date();
    let datePipe = new DatePipe("en-US");
    let myDateString = datePipe.transform(myDate,'yyyy-MM-dd');
    
    let currentDateComponents = myDateString.split('-');
    
    let currentDay = currentDateComponents[2];
    let currentMonth = currentDateComponents[1];
    let currentYear = currentDateComponents[0];


    if(currentYear > year) {
      return false;
    } else if (currentYear < year) {
      return false;
    } else {
      if(currentMonth > month) {
        return false;
      } else if (currentMonth < month) {
        return true;
      } else {
        if(currentDay > day) {
          return false;
        } else if (currentDay < day) {
          return true;
        } else {
          return false;
        }
      }
    }

  }

  cancelVehicleReservation(id : number) {

    this.userService.cancelVehicleReservation(id).subscribe(data => {
      this.vehicleReservations = data;
     });
  }

  cancelHotelReservation(id : number) {
    console.log('usao u otkazivanje hotelskih rezervacija');
    this.userService.cancelHotelReservation(id).subscribe(data => {
      this.roomReservations = data;
     });
  }

  cancelAvioReservation(id : number) {
    console.log('usao u otkazivanje avio rezervacija');
    this.userService.cancelAvioReservation(id).subscribe(data => {
      this.avioReservations = data;
     });
  }

  getVehicleReservations() {
    this.userService.getVehicleReservations(localStorage.getItem('username'))
    .subscribe(data => {
         this.vehicleReservations = data;
         console.log(this.vehicleReservations);
    });
  }

  getHotelReservations() {
    this.userService.getHotelReservations(localStorage.getItem('username'))
    .subscribe(data => {
         this.roomReservations = data;
         console.log(this.roomReservations);
    });
  }

  getAvioReservations() {
    this.userService.getAvioReservations(localStorage.getItem('username'))
    .subscribe(data => {
         this.avioReservations = data;
         console.log(this.avioReservations);
    });
  }

  updateRentCarRating(idReservation : number, newValue : number, startReservation : Date) {
      
    if(!this.yetToCome(startReservation)) {
        this.userService.updateRentCarRating(idReservation,newValue).subscribe (data => {
          this.vehicleReservations = data;
        });
      } else {
        alert('You can\'t  set rating to upcoming reservation!');
      }
      

      
      
  }

  updateHotelRating(idReservation : number, newValue : number, startReservation : Date) {
    if(!this.yetToCome(startReservation)) {
      this.userService.updateHotelRating(idReservation,newValue).subscribe (data => {
        this.roomReservations = data;
      });
    } else {
      alert('You can\'t  set rating to upcoming reservation!');
    }
    
  }

  updateAvioRating(idReservation : number, newValue : number, startReservation : Date) {
    if(!this.yetToCome(startReservation)) {
      this.userService.updateAvioRating(idReservation,newValue).subscribe (data => {
        this.avioReservations = data;
      });
    
    } else {
      alert('You can\'t  set rating to upcoming reservation!');
    }
    
  }

  updateVehicleRating(idReservation : number, newValue : number, startReservation : Date) {
    
    if(!this.yetToCome(startReservation)) { 
      this.userService.updateVehicleRating(idReservation,newValue).subscribe (data => {
        this.vehicleReservations = data;
      });
    
    } else {
      alert('You can\'t  set rating to upcoming reservation!');
    }
    
    
  }

  updateRoomRating(idReservation : number, newValue : number, startReservation : Date) {
    
    if(!this.yetToCome(startReservation)) { 
      this.userService.updateRoomRating(idReservation,newValue).subscribe (data => {
        this.roomReservations = data;
      });
    
    } else {
      alert('You can\'t  set rating to upcoming reservation!');
    }
    
    
  }

  updateFlightRating(idReservation : number, newValue : number, startReservation : Date) {

    if(!this.yetToCome(startReservation)) { 
      this.userService.updateFlightRating(idReservation,newValue).subscribe (data => {
        this.avioReservations = data;
      });    
    } else {
      alert('You can\'t  set rating to upcoming reservation!');
    }
    
  }

}
