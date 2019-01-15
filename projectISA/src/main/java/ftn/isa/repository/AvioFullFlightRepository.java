package ftn.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ftn.isa.model.AvioFullFlight;

@Repository
public interface AvioFullFlightRepository extends JpaRepository<AvioFullFlight, Long> {

}
