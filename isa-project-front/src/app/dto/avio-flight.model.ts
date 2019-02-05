import { Address } from "./address.model";
import { AvioCompany } from "./avio-company.model";

export class AvioFlight {
    id: number;
    avioCompany: AvioCompany;
    dateTimeStart: Date;
    dateTimeFinish: Date;
    flightDuration: number;
    flightDistance: number;
    price: number;
    startLocation: Address;
    endLocation: Address;
    economyClassSeats: number;
    businessClassSeats: number;
    firstClassSeats: number;
    
}