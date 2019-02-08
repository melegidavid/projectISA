package ftn.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ftn.isa.model.InviteForFlight;
import ftn.isa.repository.InviteForFlightRepository;

@Service
@Transactional(readOnly = true)
public class InviteForFlightService {

	@Autowired
	private InviteForFlightRepository inviteRepository;

	public List<InviteForFlight> getAllInvites(){
		List<InviteForFlight> list = new ArrayList<InviteForFlight>();
		inviteRepository.findAll().forEach(list::add);
		return list;
	}
	
	public InviteForFlight findInvite(Long id) {
		return inviteRepository.getOne(id);
	}
	
	@Transactional(readOnly = false)
	public InviteForFlight saveInvite(InviteForFlight invite) {
		return inviteRepository.save(invite);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED)
	public InviteForFlight updateInvite(InviteForFlight invite) {
		return inviteRepository.save(invite);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void removeInvite(Long id) {
		System.out.println("OBIRSAO");
		inviteRepository.deleteById(id);
	}
	
	
}
