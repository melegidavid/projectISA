import { Pipe, PipeTransform } from '@angular/core';
import { Hotel } from '../dto/hotel.model';

@Pipe({
  name: 'hotelPipe'
})
export class HotelPipePipe implements PipeTransform {

  transform(hotels: Hotel[], nameSearch: string, countrySearch: string, citySearch:string): Hotel[] {
    if(hotels && hotels.length) {
      return hotels.filter(hotel => {
        if(nameSearch && hotel.name.toLowerCase().indexOf(nameSearch.toLowerCase()) === -1) {
          return false;
        }
        if(countrySearch && hotel.addressDTO.country.toLowerCase().indexOf(countrySearch.toLowerCase()) === -1) {
          return false;
        }
        if(citySearch && hotel.addressDTO.city.toLowerCase().indexOf(citySearch.toLowerCase()) === -1) {
          return false;
        }
        return true;
      })
    } else {
      return hotels;
    }
  }

}
