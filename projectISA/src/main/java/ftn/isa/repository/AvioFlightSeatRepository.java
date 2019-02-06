package ftn.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.isa.model.AvioFlightSeat;
@Repository
public interface AvioFlightSeatRepository extends JpaRepository<AvioFlightSeat, Long>{

}
