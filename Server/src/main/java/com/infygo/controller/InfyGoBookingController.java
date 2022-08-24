package com.infygo.controller;

import java.time.LocalDate;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infygo.applicationentity.Flight;
import com.infygo.applicationentity.Passenger;
import com.infygo.applicationentity.Ticket;
import com.infygo.service.InfyGoService;

@RestController
public class InfyGoBookingController {
	
	static final Long maximum = 10000000l;
	static final Long minimum = 1l;
	static final String flightNotFoundMessage = "No flight with given FlightID found.";
	static final String notEnoughSeats = "Required number of seats not available for specified plane.";
	static final String bookingSuccessMessage = "Seats booked successfully";

	
	@Autowired
	private InfyGoService service;

	@RequestMapping(value = "infygo/book/{flightid}/{userid}",method=RequestMethod.POST)
	public String bookTickets(@MatrixVariable("flightid") String flightId,@MatrixVariable("userid") String userId,@RequestBody List<Passenger> passengerList)  {
		
		Long pnr = minimum + (long) (Math.random() * (maximum - minimum));
		
		List<Flight> scheduledFlight = service.fetchFlightById(flightId);
		
		if(scheduledFlight.isEmpty()) {
			return flightNotFoundMessage;
		}
		
		Flight booked = scheduledFlight.get(0);
		
		if(booked.getSeatCount()<passengerList.size()) {
			return notEnoughSeats;
		}
		
		for(Passenger ps:passengerList) {
			
			Passenger temp = new Passenger();
			ps.assignToPassenger(temp);
			
			
			temp.setTicketPnr(pnr);
			System.out.println(temp);
			service.makePassenger(temp);
		}
		
		service.bookTicketonPlane(booked.getFlightId(),passengerList.size());
		
		Ticket common = new Ticket();
		
		common.setPnr(pnr);
		common.setBookingDate(LocalDate.now());
		common.setDepartureDate(null);
		common.setFlightId(booked.getFlightId());
		common.setNumberOfSeats(passengerList.size());
		common.setPassenger(passengerList);
		common.setTotalFare(passengerList.size() * 1.0 * booked.getFare());
		common.setUserId(userId);
		
		
		service.bookTicket(common);
		
		return bookingSuccessMessage + " with ticket number " + pnr;
	}
	
}
