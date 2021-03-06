package ftn.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.isa.model.VehicleReservation;

@Repository
public interface VehicleReservationRepositorty extends JpaRepository<VehicleReservation, Long> {

	
}
