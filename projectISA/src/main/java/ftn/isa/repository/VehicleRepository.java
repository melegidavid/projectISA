package ftn.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.isa.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {

}
