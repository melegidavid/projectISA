package ftn.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.isa.model.AvioFlightReservation;
@Repository
public interface AvioFlightReservationRepository extends JpaRepository<AvioFlightReservation, Long> {

}
