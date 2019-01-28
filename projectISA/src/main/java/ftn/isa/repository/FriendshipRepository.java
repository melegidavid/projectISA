package ftn.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.isa.model.Friendship;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship,Long> {

}
