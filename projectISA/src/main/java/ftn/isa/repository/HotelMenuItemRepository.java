package ftn.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.isa.model.HotelMenuItem;

@Repository
public interface HotelMenuItemRepository extends JpaRepository<HotelMenuItem,Long> {

}
