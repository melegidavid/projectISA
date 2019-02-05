import { AvioFlight } from "./avio-flight.model";
import { UserDTO } from "./user.model";

export class AvioFlightReservation {
    id:number;
    avioFlight: AvioFlight;
    user: UserDTO;
    flightRating: number;
    companyRating: number;
    
}