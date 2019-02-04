package ftn.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.isa.model.HotelRoom;

@Repository
public interface HotelRoomRepository extends JpaRepository<HotelRoom,Long>{

}
