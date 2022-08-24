package com.infygo.controller;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infygo.applicationentity.CreditCard;
import com.infygo.applicationentity.Flight;
import com.infygo.applicationentity.User;
import com.infygo.service.InfyGoService;

@RestController
public class InfyGoAuthController {
	// For /infygo/register
	static final String successMessage = "User registered.";
	static final String failureMessage = "User registration failed.";
	static final int passwordMinLength = 9;
	static final String passwordCheckFailureMessage = "Password should contain a Lowercase,an Uppercase and a Special Character for better security.";
	static final String passwordLengthCheckFailureMessage = "Password should be minimum 9 characters.";
	
	//For infygo/login
	static final String userNotFoundMessage = "No user found.";
	static final String userAuthenticatedMessage = "User Authenticated.";
	
	//Initial Sample Data
	static final String dataInjectionSuccess = "Data Injected";
	static final String dataInjectionFailure = "No data Injected";
	
	@Autowired
	private InfyGoService service;
		
	@RequestMapping(value="/infygo/register" , method=RequestMethod.POST)
	public String postUser(@RequestBody User user) {

		if(user.getPassword()!=null) {
			String password = user.getPassword();

			if(password.length()>= passwordMinLength ) 
			{
				Pattern upper = Pattern.compile("[A-Z]");
				Pattern lower = Pattern.compile("[a-z]");
				Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");


				Matcher hasUpper = upper.matcher(password);
				Matcher hasLower = lower.matcher(password);
				Matcher hasSpecial = special.matcher(password);

				if (!(hasUpper.find() && hasLower.find() && hasSpecial.find())) {
					return passwordCheckFailureMessage;
				}
				
			}
			else {
				return passwordLengthCheckFailureMessage;
			}
			
		}

		User obj = new User();

		user.assignToUser(obj);

		int a = service.postUserDetails(user);

		if(a==1) {
			return successMessage;
		}
		else {
			return failureMessage;
		}

	}
	@RequestMapping(value="/infygo/login" , method=RequestMethod.POST)
	public String checkUser(@RequestBody User user) {

		User obj = new User();
		user.assignToUser(obj);
		
		System.out.println(obj);
		
		boolean result = service.fetchUserById(obj);
		
		if(!result) {
			return userNotFoundMessage;
		}
		
		return userAuthenticatedMessage;
		
	}
	
	@RequestMapping(value="/infygo/startup" , method=RequestMethod.POST)
	public String postData() {
		
		CreditCard card = new CreditCard();
		card.setApin("1234567898");
		card.setCardHolderName("Hello World");
		card.setCardNumber("84257294732894");
		card.setCvv("213");
		card.setExpiryMonth("05");
		card.setExpiryYear("2026");
		card.setTotalBill("500");
		
		service.saveCardData(card);
		
		CreditCard card1 = new CreditCard();
		card1.setApin("123456788");
		card1.setCardHolderName("Hello World1");
		card1.setCardNumber("842572947328");
		card1.setCvv("217");
		card1.setExpiryMonth("08");
		card1.setExpiryYear("2029");
		card1.setTotalBill("900");
		
		service.saveCardData(card1);
		
		Flight flight = new Flight();
		
		flight.setAirlines("IndiGo");
		flight.setArrivalTime("1400");
		flight.setDepartureTime("2350");
		flight.setDestination("Bombay");
		flight.setFare(6050.50);
		flight.setFlightAvailableDate(LocalDate.of(2022,12,31));
		flight.setFlightId("1");
		flight.setSeatCount(300);
		flight.setSource("Delhi");
		//flight.set
	    //public static LocalDate of(int year, Month month, int dayOfMonth) {
		service.saveFlightData(flight);

		Flight flight1 = new Flight();
		
		flight1.setAirlines("AirIndia");
		flight1.setArrivalTime("0800");
		flight1.setDepartureTime("2000");
		flight1.setDestination("Mysore");
		flight1.setFare(4869.50);
		flight1.setFlightAvailableDate(LocalDate.of(2022,12,31));
		flight1.setFlightId("2");
		flight1.setSeatCount(300);
		flight1.setSource("Delhi");
	
		service.saveFlightData(flight1);
		
		

		return dataInjectionSuccess;
	}
	
}
