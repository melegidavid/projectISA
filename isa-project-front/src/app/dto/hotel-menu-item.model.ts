import { Hotel } from "./hotel.model";

export class HotelMenuItem {
    id: number;
    serviceName: string;
    price: number;
    description: string;
    hotel: Hotel;
}