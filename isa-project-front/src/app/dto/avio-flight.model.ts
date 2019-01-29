import { Address } from "./address.model";

export class AvioFlight {
    id: number;
    dateTimeStart: string;
    dateTimeFinish: string;
    flightDuration: number;
    flightDistance: number;
    price: number;
    startLocation: Address;
    endLocation: Address
}