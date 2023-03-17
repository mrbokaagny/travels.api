package api.transporycompagny.travels.domains;

import api.transporycompagny.travels.utils.AbstractDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tickets")
public class Ticket extends AbstractDateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ticket_key;

    @OneToOne
    @JoinColumn(name = "user_key")
    private User user;

    @OneToOne
    @JoinColumn(name = "start_line_key")
    private StartALine startALine;

    @Column(name = "status")
    private String status;

    public Ticket() {
    }

    public Ticket(User user, StartALine startALine, String status) {
        this.user = user;
        this.startALine = startALine;
        this.status = status;
    }

    public long getTicket_key() {
        return ticket_key;
    }

    public void setTicket_key(long ticket_key) {
        this.ticket_key = ticket_key;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public StartALine getStartALine() {
        return startALine;
    }

    public void setStartALine(StartALine startALine) {
        this.startALine = startALine;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
