package ftn.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.isa.model.Hotel;
import ftn.isa.model.User;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long>  {

}
