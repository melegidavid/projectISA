package ftn.isa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.isa.model.AvioCompany;
import ftn.isa.repository.AvioCompanyRepository;

@Service
public class AvioCompanyService {

	@Autowired 
	private AvioCompanyRepository avioCompanyRepository;
	
	//get all 
	public List<AvioCompany> getAllAvioCompanies() {
		List<AvioCompany> list = new ArrayList<AvioCompany>();
		avioCompanyRepository.findAll().forEach(list::add);
		return list;
	}
	
	//get one
	public AvioCompany findAvioCompany(Long id) {
		return avioCompanyRepository.getOne(id);
	}
	
	//save 
	public AvioCompany saveAvioCompany(AvioCompany avioCompany) {
		return avioCompanyRepository.save(avioCompany);
	}
	
	//update
	public AvioCompany updateAvioCompany(AvioCompany avioCompany) {
		return avioCompanyRepository.save(avioCompany);
	}
	
	//delete
	public void removeAvioComapny(Long id) {
		avioCompanyRepository.getOne(id).setDeleted(true);
		avioCompanyRepository.save(avioCompanyRepository.getOne(id));
		
	}
}
