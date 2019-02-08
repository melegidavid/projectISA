import { UserDTO } from "./user.model";
import { AvioFlight } from "./avio-flight.model";

export class InviteForFlight{
    id: number;
    userDTO1: UserDTO;
    userDTO2: UserDTO;
    avioFlightDTO: AvioFlight;
    accepted: boolean;
}