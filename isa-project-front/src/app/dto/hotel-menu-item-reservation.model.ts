import { UserDTO } from "./user.model";
import { HotelMenuItem } from "./hotel-menu-item.model";

export class HotelMenuItemReservation {
    id: number;
    startReservation: Date;
    endReservation: Date;
    price: number;
    username: string;
    item: HotelMenuItem;

    constructor() {
        this.startReservation = new Date('2019-02-05');
        this.endReservation = new Date('2019-02-05');
    }
}