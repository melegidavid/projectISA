package ftn.isa.projectISA.constants;

import ftn.isa.model.AvioCompany;
import ftn.isa.model.AvioFlight;
import ftn.isa.model.AvioFlightReservation;

public class AvioCompanyConstants {
	
	public static AvioCompany getAvioCompany() {
		AvioCompany ac = new AvioCompany();
		ac.getFlights().add(getAvioFlight1());
		ac.getFlights().add(getAvioFlight2());
		return ac;
	}
	
	public static AvioCompany getAvioCompany2() {
		AvioCompany ac = new AvioCompany();
		ac.getFlights().add(getAvioFlight3());
		return ac;
	}
	
	public static AvioFlight getAvioFlight1() {
		AvioFlight a = new AvioFlight();
		a.getReseravations().add(getAvioFlightReservation1());
		return a;
	}
	
	public static AvioFlight getAvioFlight2() {
		AvioFlight a = new AvioFlight();
		a.getReseravations().add(getAvioFlightReservation2());
		return a;
	}
	
	public static AvioFlight getAvioFlight3() {
		AvioFlight a = new AvioFlight();
		a.getReseravations().add(getAvioFlightReservation3());
		return a;
	}
	
	public static AvioFlightReservation getAvioFlightReservation1() {
		AvioFlightReservation a = new AvioFlightReservation();
		a.setRatingCompany(3);
		return a;
	}
	
	public static AvioFlightReservation getAvioFlightReservation2() {
		AvioFlightReservation a = new AvioFlightReservation();
		a.setRatingCompany(4);
		return a;
	}
	
	public static AvioFlightReservation getAvioFlightReservation3() {
		AvioFlightReservation a = new AvioFlightReservation();
		a.setRatingCompany(-1);
		return a;
	}

}
