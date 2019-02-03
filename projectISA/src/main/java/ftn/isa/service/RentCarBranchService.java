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
		//proveriti a li je obrisan
		return rentCarBranchRepository.getOne(id);
	}
	
	public List<RentCarBranch> findAll() {
		List<RentCarBranch> list = rentCarBranchRepository.findAll();
		List<RentCarBranch> result = new ArrayList<RentCarBranch>();
		
		for(RentCarBranch r : list) {
			if(!r.isDeleted()) {
				result.add(r);
			}
		}
		
		return result;
	}
	
	public RentCarBranch save(RentCarBranch branch) {
		return rentCarBranchRepository.save(branch);
	}
	
	public RentCarBranch update(RentCarBranch branch) {
		return rentCarBranchRepository.save(branch);
	}
	
	public void remove(Long id) {
		RentCarBranch rcb = this.findOne(id);
		rcb.setDeleted(true);
		this.update(rcb);
	}
	
}
