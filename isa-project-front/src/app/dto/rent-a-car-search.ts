import { Address } from "./address.model";

export class RentACarSearchDTO {
    name: string;
    start: string;
    end: string;
    address : Address;

    constructor(name : string, start : string, end : string, address : Address) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.address = address;
    }
}