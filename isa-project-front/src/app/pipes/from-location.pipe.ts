import { Pipe, PipeTransform } from '@angular/core';
import { Address } from '../dto/address.model';


@Pipe({
  name: 'fromLocation'
})
export class FromLocationPipe implements PipeTransform {

  transform(destinations: Address[], searchFrom: string): Address[] {
    if (!destinations || !searchFrom) {
      return destinations;
    }

    searchFrom = searchFrom.trim();
    searchFrom = searchFrom.split(" ").join();

    return destinations.filter(destination =>
      (destination.city.toLowerCase() + destination.country.toLowerCase() + destination.street.toLowerCase()).indexOf(searchFrom.toLowerCase()) !== -1
      );



  }

}
