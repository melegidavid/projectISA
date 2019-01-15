package ftn.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ftn.isa.model.Address;

public interface AddressRepository extends JpaRepository<Address,Long> {

}
