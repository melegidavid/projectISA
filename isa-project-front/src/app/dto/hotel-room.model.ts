import { Hotel } from "./hotel.model";

export class HotelRoom {
    id: number;
    number: string;
    free: boolean;
    hotel: Hotel;
    description: string;
    price: number;
    bedNumber: number;
}