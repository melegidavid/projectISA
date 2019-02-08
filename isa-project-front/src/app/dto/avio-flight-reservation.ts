import { AvioFlight } from "./avio-flight.model";
import { UserDTO } from "./user.model";
import { AvioFlightSeat } from "./avio-flight-seat.model";

export class AvioFlightReservation {
    id:number;
    avioFlight: AvioFlight;
    user: UserDTO;
    seat: AvioFlightSeat;
    flightRating: number;
    companyRating: number;
    isDeleted: boolean;
    
}