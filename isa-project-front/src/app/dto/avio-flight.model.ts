import { Address } from "./address.model";

export class AvioFlight {
    id: number;
    dateTimeStart: Date;
    dateTimeFinish: Date;
    flightDuration: number;
    flightDistance: number;
    price: number;
    startLocation: Address;
    endLocation: Address
}