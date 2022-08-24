package com.infygo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infygo.applicationentity.Flight;
import com.infygo.applicationentity.Passenger;
import com.infygo.applicationentity.User;
import com.infygo.service.InfyGoService;

@RestController
public class InfyGoFlightController {

	@Autowired
	private InfyGoService service;
	
	static final String flightInsertionSuccess = "Flight Data inserted";
	
	//Flight Booking messages
	static final String flightBookingSuccess = "Flight booked for given passengers successfully";
	
	@RequestMapping(value="/infygo/flights/sources" , method=RequestMethod.GET)
	public List<Flight> getFlightsGivenSource(@RequestParam("source") String source) {
		
		List<Flight> resultSet = new ArrayList<Flight>();
		
		System.out.println(source);
		
		resultSet = service.fetchAllFlightsGivenSource(source);

		return resultSet;
	}
	
	@RequestMapping(value="/infygo/flights/destinations" , method=RequestMethod.GET)
	public List<Flight> getFlightsGivenDestination(@RequestParam("destination") String destination) {
		
		List<Flight> resultSet = new ArrayList<Flight>();
		
		resultSet = service.fetchAllFlightsGivenDestination(destination);
		
		return resultSet;
	}
	
	@RequestMapping(value = "/infygo/flights/{source}/{destination}/{journeydate}",method=RequestMethod.GET)
	public List<Flight> fetchFlightsGivenSourceDestinationDate(@MatrixVariable("source") String source,@MatrixVariable("destination") String destination,@MatrixVariable("journeydate") @DateTimeFormat(pattern= "yyyy-MM-dd") LocalDate date){
		System.out.println(source + " " + destination + " " + date.getClass());
		
		List<Flight> x = new ArrayList<Flight>();
		
		List<Flight> result = new ArrayList<Flight>();

		
		x = service.fetchFlightsGivenSourceDestinationDate(source,destination);

		for(Flight y : x) {
			if(y.getFlightAvailableDate().isEqual(date)) {
				result.add(y);
			}
		}
		
		return result;
	}
	
}
