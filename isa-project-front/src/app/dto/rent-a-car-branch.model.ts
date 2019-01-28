import { Address } from "./address.model";
import { RentACar } from "./rent-a-car.model";

export class RentACarBranch{
    id: number;
    name: string;
    address: Address;
    rentACar: RentACar;
}