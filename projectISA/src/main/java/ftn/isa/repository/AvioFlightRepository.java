package ftn.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ftn.isa.model.AvioFlight;

@Repository
public interface AvioFlightRepository extends JpaRepository<AvioFlight, Long> {

}
