package com.infygo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.infygo.applicationentity.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger,Integer>{
	
}
