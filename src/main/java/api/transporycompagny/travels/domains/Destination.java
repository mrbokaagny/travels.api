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
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;

@Entity
@Table(name = "destinations")
public class Destination extends AbstractDateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long destination_key;

    @Column(name = "price")
    private Number price;

    @OneToOne
    @JoinColumn(name = "line_key")
    private Line line;

    @OneToOne
    @JoinColumn(name = "company_key")
    private Company company;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    private List<StartALine> startalines;

    public Destination() {
    }

    public Destination(Number price, Line line, Company company) {
        this.price = price;
        this.line = line;
        this.company = company;
    }

    public long getDestination_key() {
        return destination_key;
    }

    public void setDestination_key(long destination_key) {
        this.destination_key = destination_key;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(Number price) {
        this.price = price;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
