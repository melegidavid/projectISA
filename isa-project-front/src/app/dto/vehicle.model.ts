import { RentACarBranch } from "./rent-a-car-branch.model";
import { RentACar } from "./rent-a-car.model";

export class Vehicle {
    id: number;
    type: string;
    seatsNumber: number;
    name: string;
    mark: string;
    model: string;
    yearProduced: number;
    rentCar : RentACar;
}