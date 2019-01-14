package ftn.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.isa.model.AvioCompany;
import ftn.isa.model.HotelRoom;

public interface HotelRoomRepository extends JpaRepository<HotelRoom,Long>{

}
