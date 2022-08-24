package com.infygo.applicationentity;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = Passenger.PASSENGER_TABLENAME)
public class Passenger {
	
	static final String PASSENGER_TABLENAME = "Passenger_Details";
	
	@Id
	@Column(name="passenger_id")
	private Integer passengerId;
	
	@Column(name="passenger_age", length=2)
	private String passengerAge;
	
	@Column(name="passenger_gender", length=1)
	private String passengerGender;
	
	@Column(name="passenger_name", length=25)
	private String passengerName;
	
	@ManyToOne(targetEntity = com.infygo.applicationentity.Ticket.class , fetch = FetchType.LAZY)
	@JoinColumn(name="pnr", nullable=false)
	private Long ticketPnr;
	//A number of passengers can be associated to a single ticket

	
	
	//Getters and Setters
	
	public Integer getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(Integer passengerId) {
		this.passengerId = passengerId;
	}

	public String getPassengerAge() {
		return passengerAge;
	}

	public void setPassengerAge(String passengerAge) {
		this.passengerAge = passengerAge;
	}

	public String getPassengerGender() {
		return passengerGender;
	}

	public void setPassengerGender(String passengerGender) {
		this.passengerGender = passengerGender;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public Long getTicketPnr() {
		return ticketPnr;
	}

	public void setTicketPnr(Long ticketPnr) {
		this.ticketPnr = ticketPnr;
	}

	public Passenger(Integer passengerId, String passengerAge, String passengerGender, String passengerName,
			Long ticketPnr) {
		super();
		this.passengerId = passengerId;
		this.passengerAge = passengerAge;
		this.passengerGender = passengerGender;
		this.passengerName = passengerName;
		this.ticketPnr = ticketPnr;
	}

	@Override
	public String toString() {
		return "Passenger [passengerId=" + passengerId + ", passengerAge=" + passengerAge + ", passengerGender="
				+ passengerGender + ", passengerName=" + passengerName + ", ticketPnr=" + ticketPnr + "]";
	}
	
public Passenger assignToPassenger(Passenger destination) {
		
		destination.passengerAge = this.passengerAge;
		destination.passengerGender = this.passengerGender;
		destination.passengerId = this.passengerId;
		destination.passengerName = this.passengerName;
		destination.ticketPnr = this.ticketPnr;
		
		return destination;
		
	}

public Passenger() {
	super();
}
	
	

}
