package com.infygo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.infygo.applicationentity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Integer>{
	
	public List<Flight> findBySourceLike(String pattern);
	
	public List<Flight> findByDestinationLike(String pattern);
	
	public List<Flight> findBySourceLikeAndDestinationLike/*AndFlightAvailableDateLike*/(String source,String destination);
	
	public List<Flight> findByFlightId(String flightId);

	@Modifying
	@Query("update Flight set seatCount = seatCount - :inc Where flightId = :id")
	public int bookTickets(@Param("id") String flightId,@Param("inc") Integer seats);

}
