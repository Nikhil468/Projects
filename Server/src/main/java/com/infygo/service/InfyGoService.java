package com.infygo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import com.infygo.applicationentity.CreditCard;
import com.infygo.applicationentity.Flight;
import com.infygo.applicationentity.Passenger;
import com.infygo.applicationentity.User;
import com.infygo.applicationentity.Ticket;
import com.infygo.repository.CreditCardRepository;
import com.infygo.repository.FlightRepository;
import com.infygo.repository.PassengerRepository;
import com.infygo.repository.TicketRepository;
import com.infygo.repository.UserRepository;


@Service
//@EnableJpaRepositories("com.infygo.repository")
//@EntityScan("com.infygo.applicationentity.*")
//@ComponentScan(basePackages = { "com.infygo.repository.*" })
public class InfyGoService {
	@Autowired
	private CreditCardRepository cardRepo;
	@Autowired
	private FlightRepository flightRepo;
	@Autowired
	private PassengerRepository passengerRepo;
	@Autowired
	private TicketRepository ticketRepo;
	@Autowired
	private UserRepository userRepo;
	
	public int postUserDetails(User user) {
		
		try {
			userRepo.saveAndFlush(user);
		}
		catch(Exception e){
			return 0;
		}
		
		return 1;
	}
	
	public boolean fetchUserById(User user) {
		
		List<User> record= userRepo.findByUserId(user.getUserId());
		if(record.isEmpty()) {
			return false;
		}
		
		User obj = record.get(0);
		
		return obj.checkEquality(user);
		
	}
	
	public List<Flight> fetchAllFlightsGivenSource(String source){
		List<Flight> fromSource = new ArrayList<Flight>(); 
		
		fromSource = flightRepo.findBySourceLike(source);
		
		System.out.println(fromSource);
		return fromSource;
	}

	public List<Flight> fetchAllFlightsGivenDestination(String destination){
		List<Flight> fromDestination = new ArrayList<Flight>(); 
		
		fromDestination = flightRepo.findByDestinationLike("%"+destination+"%");
		
		return fromDestination;
	}
	
	public Flight insertFlightData(Flight flight) {
		
		flight = flightRepo.saveAndFlush(flight);
		
		return flight;
		
	}
	
	public List<Flight> fetchFlightsGivenSourceDestinationDate(String source,String destination){
		List<Flight> x = new ArrayList<Flight>();
		
		x=flightRepo.findBySourceLikeAndDestinationLike/*AndFlightAvailableDateLike*/(source, destination);
		
		
		
		return x;
	}
	
	public List<Flight> fetchFlightById(String flightId){
		return flightRepo.findByFlightId(flightId);
	}
	
	public void saveCardData(CreditCard card) {
		cardRepo.saveAndFlush(card);
	}
	
	public void saveFlightData(Flight flight) {
		flightRepo.saveAndFlush(flight);
	}
	
	public void bookTicketonPlane(String flightId , int numberOfSeats) {
		flightRepo.bookTickets(flightId,numberOfSeats);
	}
	
	public void bookTicket(Ticket ticket) {
		ticketRepo.saveAndFlush(ticket);
	}
	
	public void makePassenger(Passenger ps) {
		try{
			passengerRepo.saveAndFlush(ps);
		}
		catch(IllegalArgumentException e) {
			System.out.println("Caught");
		}
	}
	
	public boolean addCard(CreditCard card) {
		
		try {
			cardRepo.saveAndFlush(card);
		}
		catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
}
