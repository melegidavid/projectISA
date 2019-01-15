package ftn.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.isa.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {

}
