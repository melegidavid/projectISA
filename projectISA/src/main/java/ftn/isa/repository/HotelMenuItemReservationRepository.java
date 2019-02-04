package ftn.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.isa.model.HotelMenuItemReservation;

@Repository
public interface HotelMenuItemReservationRepository extends JpaRepository<HotelMenuItemReservation, Long> {

}
