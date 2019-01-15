package ftn.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ftn.isa.model.AvioCompany;

@Repository
public interface AvioCompanyRepository extends JpaRepository<AvioCompany, Long> {

}
