import { Vehicle } from "./vehicle.model";

export class VehicleReservationDTO {
	username : string ;
	vehicle: Vehicle;  
	returnPlaceId : number;
	takePlaceId : number;
	startReservation : Date;
	endReservation : Date;
	vehicleRating : number;
	rentCarRating : number;
	id : number;
	price : number;
}