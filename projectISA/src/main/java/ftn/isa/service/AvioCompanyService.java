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
	
	public List<AvioCompany> getAllAvioCompanies() {
		List<AvioCompany> list = new ArrayList<AvioCompany>();
		avioCompanyRepository.findAll().forEach(list::add);
		return list;
	}
	
	public void addAvioCompany(AvioCompany avioCompany) {
		avioCompanyRepository.save(avioCompany);
	}
}
