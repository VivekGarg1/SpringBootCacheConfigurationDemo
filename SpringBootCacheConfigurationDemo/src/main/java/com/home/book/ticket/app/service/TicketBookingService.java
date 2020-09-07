package com.home.book.ticket.app.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.home.book.ticket.app.dao.TicketBookingDao;
import com.home.book.ticket.app.entities.Ticket;

@Service
public class TicketBookingService {

	@Autowired
	private TicketBookingDao ticketBookingDao;
	
	public Ticket createTicket(Ticket ticket) {
		return ticketBookingDao.save(ticket);
	}
	
	@Cacheable(value="ticketsCache", key="#ticketId",unless = "#result==null")
	public Ticket getTicketById(Integer ticketId) {
		return ticketBookingDao.findOne(ticketId);
	}
	
	public List<Ticket> getAllBookedTickets() {
		return ticketBookingDao.findAll();
	}
	
	@CacheEvict(value="ticketsCache", key="#ticketId")
	public void deleteTicket(Integer ticketId) {
		ticketBookingDao.delete(ticketId);
	}
	
	@CachePut(value="ticketsCache", key="#ticketId")
	public Ticket updateTicket(Integer ticketId, String newEmail) {
		Ticket ticketFromDb = ticketBookingDao.getOne(ticketId);
		ticketFromDb.setEmail(newEmail);
		Ticket upadedTicket = ticketBookingDao.save(ticketFromDb);
		return upadedTicket;
	}
}
