package ftn.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import ftn.isa.model.Friendship;
import ftn.isa.repository.FriendshipRepository;

@Service
public class FriendshipService {

	@Autowired
	private FriendshipRepository friendshipRepository;
	
	public List<Friendship> getAllFriendships(){
		List<Friendship> list = new ArrayList<Friendship>();
		friendshipRepository.findAll().forEach(list::add);
		return list;
	}

	public void removeFriendship(Friendship friendship) {
		friendshipRepository.delete(friendship);
	}
}
