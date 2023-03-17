package api.transporycompagny.travels.domains;

import java.util.List;

import api.transporycompagny.travels.utils.AbstractDateTime;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "lines")
public class Line extends AbstractDateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long line_key;

    @Column(name = "starting_point")
    private String starting_point;

    @Column(name = "point_of_arrival")
    private String point_of_arrival;

    @OneToMany(mappedBy = "line", cascade = CascadeType.ALL)
    private List<Destination> destinations;

    public Line() {
    }

    public Line(String starting_point, String point_of_arrival) {
        this.starting_point = starting_point;
        this.point_of_arrival = point_of_arrival;
    }

    public Long getLine_key() {
        return line_key;
    }

    public void setLine_key(Long line_key) {
        this.line_key = line_key;
    }

    public String getStarting_point() {
        return starting_point;
    }

    public void setStarting_point(String starting_point) {
        this.starting_point = starting_point;
    }

    public String getPoint_of_arrival() {
        return point_of_arrival;
    }

    public void setPoint_of_arrival(String point_of_arrival) {
        this.point_of_arrival = point_of_arrival;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }

}
