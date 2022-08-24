package com.infygo.applicationentity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = Ticket.TICKET_TABLENAME)
public class Ticket {
	
	static final String TICKET_TABLENAME = "Ticket_Details"; 
	
	@Id
	@Column(name="pnr", length=25)
	private Long pnr;
	
	@Column(name="booking_date", length=25)
	private LocalDate bookingDate;
	
	@Column(name="departure_date", length=25)
	private LocalDate departureDate;
	@Column(name="departure_time", length=25)
	private String departureTime;
	
	@ManyToOne(targetEntity = com.infygo.applicationentity.Flight.class , fetch = FetchType.LAZY)
    @JoinColumn(name="flight_id", nullable=false)
	private String flightId;
	
	@Column(name="number_of_seats", length=25)
	private Integer numberOfSeats;
	
	@Column(name="total_fare", length=25)
	private Double totalFare;
	
	@ManyToOne(targetEntity = com.infygo.applicationentity.User.class, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
	private String userId;
	//A number of users can be associated to a single ticket
	
	@Column(name="passenger")
	@OneToMany(mappedBy = "ticketPnr",cascade = CascadeType.ALL)
	private List<Passenger> passenger;
	//A ticket can be associated to a number of passengers

	
	
	//Getters and Setters
	
	public void setPassenger(List<Passenger> passenger) {
		this.passenger = passenger;
	}

	public Long getPnr() {
		return pnr;
	}

	public void setPnr(Long pnr) {
		this.pnr = pnr;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public Integer getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public Double getTotalFare() {
		return totalFare;
	}

	public void setTotalFare(Double totalFare) {
		this.totalFare = totalFare;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Passenger> getPassenger() {
		return passenger;
	}

	public Ticket(Long pnr, LocalDate bookingDate, LocalDate departureDate, String departureTime, String flightId,
			Integer numberOfSeats, Double totalFare, String userId, List<Passenger> passenger) {
		super();
		this.pnr = pnr;
		this.bookingDate = bookingDate;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.flightId = flightId;
		this.numberOfSeats = numberOfSeats;
		this.totalFare = totalFare;
		this.userId = userId;
		this.passenger = passenger;
	}

	@Override
	public String toString() {
		return "Ticket [pnr=" + pnr + ", bookingDate=" + bookingDate + ", departureDate=" + departureDate
				+ ", departureTime=" + departureTime + ", flightId=" + flightId + ", numberOfSeats=" + numberOfSeats
				+ ", totalFare=" + totalFare + ", userId=" + userId + ", passenger=" + passenger + "]";
	}
	
public Ticket assignToTicket(Ticket destination) {
		
		destination.bookingDate = this.bookingDate;
		destination.pnr = this.pnr;
		destination.departureDate = this.departureDate;
		destination.departureTime = this.departureTime;
		destination.flightId = this.flightId;
		destination.numberOfSeats = this.numberOfSeats;
		destination.totalFare = this.totalFare;
		destination.userId = this.userId;
		
		destination.passenger = new ArrayList<Passenger>();
		
		for(Passenger obj : this.passenger) {
			destination.passenger.add(obj);
		}
		
		return destination;
		
	}

public Ticket() {
	super();
}
	
	
	
}
