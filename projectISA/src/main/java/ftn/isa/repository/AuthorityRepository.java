package ftn.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.isa.model.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {

}
