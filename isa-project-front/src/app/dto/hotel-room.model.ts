import { Hotel } from "./hotel.model";

export class HotelRoom {
    id: number;
    number: string;
    free: boolean; //ovo koristim samo da vidim da li je soba selektovana pri korisnickom izboru za rezervaciju
    hotel: Hotel;
    description: string;
    price: number;
    bedNumber: number;
}