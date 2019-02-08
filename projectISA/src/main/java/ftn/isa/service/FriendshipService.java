package ftn.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import ftn.isa.model.Friendship;
import ftn.isa.repository.FriendshipRepository;

@Service
@Transactional(readOnly = true)
public class FriendshipService {

	@Autowired
	private FriendshipRepository friendshipRepository;
	
	public List<Friendship> getAllFriendships(){
		List<Friendship> list = new ArrayList<Friendship>();
		friendshipRepository.findAll().forEach(list::add);
		return list;
	}
	
	public Friendship findFriendship(Long id) {
		return friendshipRepository.getOne(id);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Friendship saveFriendship(Friendship friendship) {
		return friendshipRepository.save(friendship);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED)
	public void updateFriendship(Friendship friendship) {
		friendshipRepository.save(friendship);		
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void removeFriendship(Long id) {
		friendshipRepository.deleteById(id);
	}
}
