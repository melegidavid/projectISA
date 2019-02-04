export class RoomSearch {
    startDate: Date;
    endDate: Date;
    startPrice: number;
    endPrice: number;
    guestsNumber: number;
    roomsNumber: number;

    constructor() {
        this.startDate = new Date('');
        this.endDate = new Date('');
    }
}