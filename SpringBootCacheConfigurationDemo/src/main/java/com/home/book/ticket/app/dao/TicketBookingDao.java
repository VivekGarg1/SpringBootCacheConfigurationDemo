package com.home.book.ticket.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.home.book.ticket.app.entities.Ticket;

public interface TicketBookingDao extends JpaRepository<Ticket, Integer>{

}
