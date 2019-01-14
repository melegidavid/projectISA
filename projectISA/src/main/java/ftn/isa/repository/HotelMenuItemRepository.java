package ftn.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.isa.model.HotelMenuItem;

public interface HotelMenuItemRepository extends JpaRepository<HotelMenuItem,Long> {

}
