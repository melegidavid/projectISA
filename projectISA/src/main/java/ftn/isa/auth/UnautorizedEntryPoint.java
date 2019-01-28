package ftn.isa.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class UnautorizedEntryPoint implements AuthenticationEntryPoint {
	
	//Metoda koja se izvrsava ukoliko neautorizovani korisnik pokusa da
	//pristupi zasticenom REST servisu. Vraca 401 Unauthorized ukoliko postoji Login Page
	//u aplikaciji, pozeljno je da se korisnik redirektuje na tu stranicu
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2) throws IOException, ServletException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Zabranjen pristup!");
	}

}
