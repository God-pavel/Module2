package com.mentoring.module2.controller;

import com.mentoring.module2.facade.BookingFacade;
import com.mentoring.module2.model.Ticket;
import com.mentoring.module2.model.impl.EventImpl;
import com.mentoring.module2.model.impl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private BookingFacade bookingFacade;

    //TODO check
    private final SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");

    @PostMapping
    public Ticket bookTicket(@RequestParam("userId") final long userId,
                             @RequestParam("eventId") final long eventId,
                             @RequestParam("place") final int place,
                             @RequestParam("category") final String category) {
        return bookingFacade.bookTicket(userId, eventId, place, Ticket.Category.valueOf(category));
    }

    @GetMapping(params = {"user", "size", "number"})
    public List<Ticket> getBookedTickets(@RequestParam("user") final UserImpl user,
                                         @RequestParam("size") final int size,
                                         @RequestParam("number") final int number) {
        return bookingFacade.getBookedTickets(user, size, number);
    }

    @GetMapping(params = {"event", "size", "number"})
    public List<Ticket> getBookedTickets(@RequestParam("event") final EventImpl event,
                                         @RequestParam("size") final int size,
                                         @RequestParam("number") final int number) {
        return bookingFacade.getBookedTickets(event, size, number);
    }

    @DeleteMapping("/{id}")
    public boolean cancelTicket(@PathVariable("id") final long id) {
        return bookingFacade.cancelTicket(id);
    }
}