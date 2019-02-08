package ftn.isa.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.isa.dto.DateRange;
import ftn.isa.dto.HotelReport;
import ftn.isa.model.Hotel;
import ftn.isa.model.HotelRoom;
import ftn.isa.model.RoomReservation;
import ftn.isa.repository.HotelRepository;

@Service
public class HotelService {

	@Autowired
	private HotelRepository hotelRepository;
	
	public List<Hotel> getAllHotels() {
		List<Hotel> list = new ArrayList<Hotel>();
		hotelRepository.findAll().forEach(list::add);
		return list;
	}
	
	public Hotel findHotel(Long id) {
		return hotelRepository.getOne(id); 
	}
	
	public Hotel saveHotel(Hotel hotel) {
		return hotelRepository.save(hotel);
	}
	
	public Hotel updateHotel(Hotel hotel) {
		return hotelRepository.save(hotel);
	}
	
	public void removeHotel(Hotel hotel) {
		hotelRepository.delete(hotel);
	}
	
	public double getAvgRating(Long id) {
		
		Hotel hotel = this.findHotel(id);
		
		double sum = 0;
		double count = 0;
		
		List<HotelRoom> rooms = hotel.getRooms();
		
		List<RoomReservation> reservations = new ArrayList<>();
		
		for(HotelRoom hr : rooms) {
			reservations.addAll(hr.getReseravations());
		}
		
		for(RoomReservation rr : reservations) {
			if(rr.getHotelRating() > 0) {
				sum += rr.getHotelRating();
				count++;
			}
		}
		
		if(count == 0) {
			return 0;
		}
		
		
		double result = sum/count;
		DecimalFormat df = new DecimalFormat("#.##");      
		result = Double.valueOf(df.format(result));

		return result;
	}
	
	public HotelReport generateReport(Long id, DateRange dateRange) {
		
		Hotel hotel = this.findHotel(id);
		HotelReport report = new HotelReport(hotel);
		
		report.setAvgRating(this.getAvgRating(id));
		report.generateRoomReports();
		report.generatePrihod(dateRange);
		
		return report;
	}

	
	
	
}
