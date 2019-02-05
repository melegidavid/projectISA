import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/user.service';
import { VehicleReservationDTO } from 'src/app/dto/vehicleReservationDTO';

@Component({
  selector: 'app-user-history',
  templateUrl: './user-history.component.html',
  styleUrls: ['./user-history.component.css']
})
export class UserHistoryComponent implements OnInit {

  vehicleReservations : VehicleReservationDTO[] = [];
  //ostalih 2 isto ovde

  constructor(private userService: UserService) { }//injektuj servis za rezervaciju

  ngOnInit() {
   
    this.getVehicleReservations();
 
   // document.querySelector("h1").style.background = "#fff";
  }

  getVehicleReservations() {
    this.userService.getVehicleReservations(localStorage.getItem('username'))
    .subscribe(data => {
         this.vehicleReservations = data;
         console.log('Doslo jeeee: ');
         console.log(this.vehicleReservations);
    });
  }

  //vehicleReservation da ima rentCarRating i vehicleRating
  //kad stisne na ocenu, updateuje se vehicleReservation, ponovo pozoves onInit
  //data sql, dto i model za obe ocene na beku, dto na phrontu za ocene 


  updateRentCarRating(idReservation : number, newValue : number) {
      console.log('Usao u update ocene rent a cara');
      console.log('Id rezervacije : ' + idReservation);
      console.log('Nova ocena : ' + newValue);

      this.userService.updateRentCarRating(idReservation,newValue).subscribe (data => {
        this.vehicleReservations = data;
      });
  }

  updateVehicleRating(idReservation : number, newValue : number) {
    console.log('Usao u update ocene vehicla');
    console.log('Id rezervacije : ' + idReservation);
    console.log('Nova ocena : ' + newValue);

    this.userService.updateVehicleRating(idReservation,newValue).subscribe (data => {
      this.vehicleReservations = data;
    });
  }

}
