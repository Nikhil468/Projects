package com.infygo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.infygo.applicationentity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer>{

}
