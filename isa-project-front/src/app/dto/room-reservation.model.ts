import { UserDTO } from "./user.model";
import { HotelRoom } from "./hotel-room.model";

export class RoomReservation {
    id: number;
    startReservation: Date;
    endReservation: Date;
    username: string;
    price: number;
    room: HotelRoom;
    roomRating: number;
    hotelRating: number;

    constructor() {
        this.startReservation = new Date('2019-02-05');
        this.endReservation = new Date('2019-02-05');
    }
}