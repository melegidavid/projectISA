package ftn.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.isa.model.InviteForFlight;

@Repository
public interface InviteForFlightRepository extends JpaRepository<InviteForFlight, Long> {

}
