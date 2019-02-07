import { UserDTO } from "./user.model";
import { AvioFlight } from "./avio-flight.model";

export class InviteForFlight{
    id: number;
    user1: UserDTO;
    user2: UserDTO;
    avioFlight: AvioFlight;
    accepted: boolean;
}