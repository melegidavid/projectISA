import { Address } from "./address.model";

export class AvioflightSearchDTO {
    fromLocation: Address;
    toLocation: Address;
    departureDate: Date;
    returnDate: Date;
    travelers: number;
    classFlight: string;
    typeOfFlight: string;

    constructor(fromLocation: Address,
                toLocation: Address,
                departureDate: Date,
                typeOfFlight: string,
                travelers: number) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.departureDate = departureDate;
        this.typeOfFlight = typeOfFlight;
        this.travelers = travelers;

    }
}