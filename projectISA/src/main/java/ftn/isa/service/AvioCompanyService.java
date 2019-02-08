package ftn.isa.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.isa.dto.AvioReport;
import ftn.isa.dto.DateRange;
import ftn.isa.model.AvioCompany;
import ftn.isa.model.AvioFlight;
import ftn.isa.model.AvioFlightReservation;
import ftn.isa.repository.AvioCompanyRepository;

@Service
public class AvioCompanyService {

	@Autowired
	private AvioCompanyRepository avioCompanyRepository;

	// get all
	public List<AvioCompany> getAllAvioCompanies() {
		List<AvioCompany> list = new ArrayList<AvioCompany>();
		avioCompanyRepository.findAll().forEach(list::add);
		return list;
	}

	// get one
	public AvioCompany findAvioCompany(Long id) {
		return avioCompanyRepository.getOne(id);
	}

	// save
	public AvioCompany saveAvioCompany(AvioCompany avioCompany) {
		return avioCompanyRepository.save(avioCompany);
	}

	// update
	public AvioCompany updateAvioCompany(AvioCompany avioCompany) {
		return avioCompanyRepository.save(avioCompany);
	}

	// delete
	public void removeAvioComapny(Long id) {
		avioCompanyRepository.getOne(id).setDeleted(true);
		avioCompanyRepository.save(avioCompanyRepository.getOne(id));
	}
	
    public double getAvgRating(Long id) {

    	

    	AvioCompany company = this.findAvioCompany(id);
		
    	System.out.println("Usao da trazi za kompaniju " + company.getName());
    	
		double sum = 0;
		double count = 0;
		
		List<AvioFlight> letovi = company.getFlights();
		
		System.out.println("Broj letova: " + letovi.size());
		
		List<AvioFlightReservation> reservations = new ArrayList<>();
		
		for(AvioFlight a : letovi) {
			reservations.addAll(a.getReseravations());
		}
		
		System.out.println("Broj rezervacija: " + reservations.size());
		
		for(AvioFlightReservation ar : reservations) {
			if(ar.getRatingCompany() > 0) {
				sum += ar.getRatingCompany();
				count++;
			}
		}
		
		System.out.println("Count "+ count);
		System.out.println("Sum "+ sum);
		
		if(count == 0) {
			return 0;
		}
		
		
		double result = sum/count;
		DecimalFormat df = new DecimalFormat("#.##");      
		result = Double.valueOf(df.format(result));
		
		
		System.out.println(result);

		return result;
	}
    
    public AvioReport generateReport(Long id, DateRange dateRange) {
		
		AvioCompany company = this.findAvioCompany(id);
		AvioReport report = new AvioReport(company);
		
		report.setAvgRating(this.getAvgRating(id));
		report.generateFlightReports();
		report.generatePrihod(dateRange);
		
		return report;
	}


}
