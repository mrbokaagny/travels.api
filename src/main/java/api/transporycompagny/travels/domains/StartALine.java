package api.transporycompagny.travels.domains;

import java.util.List;

import api.transporycompagny.travels.utils.AbstractDateTime;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "startalines")
public class StartALine extends AbstractDateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long start_a_line_key;

    @OneToOne
    @JoinColumn(name = "car_key")
    private Car car;

    @OneToOne
    @JoinColumn(name = "company_key")
    private Company company;

    @OneToOne
    @JoinColumn(name = "destination_key")
    private Destination destination ;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "startALine")
    private List<Ticket> tickets;

    @Column(name = "status")
    private String status;

    public StartALine() {
    }

    public StartALine(Car car, Company company, String status , Destination destination) {
        this.car = car;
        this.company = company;
        this.status = status;
        this.destination = destination ;
    }

    public Long getStart_a_line_key() {
        return start_a_line_key;
    }

    public void setStart_a_line_key(Long start_a_line_key) {
        this.start_a_line_key = start_a_line_key;
    }

    public Destination getDestination(){
        return destination ;
    }

    public void setDestination(Destination destination){
        this.destination = destination ;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
