package ftn.isa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.isa.model.AvioCompany;
import ftn.isa.service.AvioCompanyService;

@RestController
public class AvioCompanyController {

	@Autowired
	private AvioCompanyService avioCompanyService;
	
	@RequestMapping(value="/avioCompanies", method = RequestMethod.GET)
	public List<AvioCompany> getAvioCompanies() {
		return avioCompanyService.getAllAvioCompanies();
	}
	
	@RequestMapping(value="/avioCompanies", method = RequestMethod.POST)
	public void addAvioCompany(@RequestBody AvioCompany avioCompany) {
		avioCompanyService.addAvioCompany(avioCompany);
	}
}
