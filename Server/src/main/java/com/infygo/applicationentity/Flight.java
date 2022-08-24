package com.infygo.applicationentity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = Flight.FLIGHT_TABLENAME)
public class Flight {
	
	static final String FLIGHT_TABLENAME = "Flight_Details";
	
	@Id
	@NotEmpty
	@Column(name="flight_id", length=25)
	private String flightId;
	
	@Column(name="airlines", length=25)
	private String airlines;
	
	@Column(name="arrival_time", length=25)
	private String arrivalTime;
	
	@Column(name="departure_time", length=25)
	private String departureTime;
	
	@Column(name="destination", length=25)
	private String destination;
	
	@Column(name="fare", length=25)
	private Double fare;
	
	@Column(name="flight_available_date", length=25)
	private LocalDate flightAvailableDate;
	
	@Column(name="seat_count")
	private Integer seatCount;
	
	@Column(name="source", length=25)
	private String source;
	
	@Column(name="ticket")
	@OneToMany(mappedBy = "flightId")
	private List<Ticket> ticket;
	//A flight can have a number of tickets associated to it.
	
	
	//Getters and Setters
	
	public void setTicket(List<Ticket> ticket) {
		this.ticket = ticket;
	}
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	public String getAirlines() {
		return airlines;
	}
	public void setAirlines(String airlines) {
		this.airlines = airlines;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Double getFare() {
		return fare;
	}
	public void setFare(Double fare) {
		this.fare = fare;
	}
	public LocalDate getFlightAvailableDate() {
		return flightAvailableDate;
	}
	public void setFlightAvailableDate(LocalDate flightAvailableDate) {
		this.flightAvailableDate = flightAvailableDate;
	}
	public Integer getSeatCount() {
		return seatCount;
	}
	public void setSeatCount(Integer seatCount) {
		this.seatCount = seatCount;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Flight(String flightId, String airlines, String arrivalTime, String departureTime, String destination,
			Double fare, LocalDate flightAvailableDate, Integer seatCount, String source, List<Ticket> ticket) {
		super();
		this.flightId = flightId;
		this.airlines = airlines;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.destination = destination;
		this.fare = fare;
		this.flightAvailableDate = flightAvailableDate;
		this.seatCount = seatCount;
		this.source = source;
		this.ticket = ticket;
	}
	
	@Override
	public String toString() {
		return "Flight [flightId=" + flightId + ", airlines=" + airlines + ", arrivalTime=" + arrivalTime
				+ ", departureTime=" + departureTime + ", destination=" + destination + ", fare=" + fare
				+ ", flightAvailableDate=" + flightAvailableDate + ", seatCount=" + seatCount + ", source=" + source
				+ ", ticket=" + ticket + "]";
	}
	
	public Flight assignToFlight(Flight destination) {
		
		destination.airlines = this.airlines;
		destination.arrivalTime = this.arrivalTime;
		destination.departureTime = this.departureTime;
		destination.destination = this.destination;
		destination.fare = this.fare;
		destination.flightAvailableDate = this.flightAvailableDate;
		destination.flightId = this.flightId;
		destination.seatCount = this.seatCount;
		destination.source = this.source;
		
		destination.ticket = new ArrayList<Ticket>();
		
		if(this.ticket!=null) {
			for(Ticket obj : this.ticket) {
				destination.ticket.add(obj);
			}
		}
		
		return destination;
		
	}
	public Flight() {
		super();
	}
	
}
