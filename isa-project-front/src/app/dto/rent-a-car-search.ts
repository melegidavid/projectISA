import { Address } from "./address.model";

export class RentACarSearchDTO {
    name: string;
    start: Date;
    end: Date;
    address : Address;

    constructor(name : string, start : Date, end : Date, address : Address) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.address = address;
    }
}