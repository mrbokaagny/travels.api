package api.transporycompagny.travels.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import api.transporycompagny.travels.domains.StartALine;
import api.transporycompagny.travels.domains.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByStartALine(StartALine start_a_line_key);

}
