package ftn.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.isa.model.RentCarBranch;
import ftn.isa.repository.RentCarBranchRepository;

@Service
public class RentCarBranchService {

	@Autowired
	private RentCarBranchRepository rentCarBranchRepository;
	
	
	
	public RentCarBranch findOne(Long id) {
		return rentCarBranchRepository.getOne(id);
	}
	
	public List<RentCarBranch> findAll() {
		List<RentCarBranch> list = new ArrayList<RentCarBranch>();
		rentCarBranchRepository.findAll().forEach(list::add);
		return list;
	}
	
	public RentCarBranch save(RentCarBranch branch) {
		return rentCarBranchRepository.save(branch);
	}
	
	public RentCarBranch update(RentCarBranch branch) {
		return rentCarBranchRepository.save(branch);
	}
	
	public void remove(Long id) {
		rentCarBranchRepository.deleteById(id);
	}
	
}
