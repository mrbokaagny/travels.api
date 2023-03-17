package api.transporycompagny.travels.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.transporycompagny.travels.domains.StartALine;
import api.transporycompagny.travels.domains.Ticket;
import api.transporycompagny.travels.domains.User;
import api.transporycompagny.travels.dtos.TicketDto;
import api.transporycompagny.travels.repositorys.StartALineRepository;
import api.transporycompagny.travels.repositorys.TicketRepository;
import api.transporycompagny.travels.utils.ResponseApi;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    StartALineRepository startALineRepository;

    private ResponseApi apiResponse = new ResponseApi();

    @PostMapping("/buy-ticket")
    public ResponseEntity<?> buyTickey(@RequestBody TicketDto ticket) {
        try {
            Optional<StartALine> startaline = startALineRepository.findById(ticket.getStart_line_key());
            if (startaline.isEmpty()) {
                this.apiResponse.hydrateData("NOT_FOUND", "This line is not found, try again", "error", null);
                return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.NOT_FOUND);
            } else {
                List<Ticket> all_tickets = ticketRepository.findByStartALine(startaline.get());
                var current_number_place = startaline.get().getCar().getNumber_of_place();
                var maining_space = (int) current_number_place - (int) all_tickets.size();

                if (maining_space > 0) {
                    User user = new User();
                    Ticket ticket_saved = new Ticket(user, startaline.get(), "waiting");
                    ticketRepository.save(ticket_saved);
                    this.apiResponse.hydrateData("NONE", "Ticket Generated successully", "success",
                            null);
                    return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.OK);
                } else {
                    this.apiResponse.hydrateData("NONE", "You cannot take a ticket for this departure", "success",
                            null);
                    return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.NOT_FOUND);
                }
            }
        } catch (Exception e) {
            return this.apiResponse.builErrorJson(e);
        }
    }

}
