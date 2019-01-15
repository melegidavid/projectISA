package ftn.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.isa.model.RentCar;

public interface RentCarRepository extends JpaRepository<RentCar,Long> {
	

}
