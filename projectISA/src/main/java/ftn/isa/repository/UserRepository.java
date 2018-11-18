package ftn.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.isa.model.RegisteredUser;

@Repository
public interface UserRepository extends JpaRepository<RegisteredUser,Long> {

}
