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
@Table(name = "companies")
public class Company extends AbstractDateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long company_key;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "number_of_car", nullable = false)
    private Number number_of_car;

    @Column(name = "number_of_line", nullable = false)
    private Number number_of_line;

    @Column(name = "is_actived")
    private boolean is_actived;

    @Column(name = "reference", nullable = false)
    private String reference;

    @Column(name = "cover_link")
    private String cover_link;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "latitude")
    private String latitude;

    @OneToOne
    @JoinColumn(name = "city_key", nullable = false)
    private City city;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<Car> car;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<StartALine> start_a_line;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Destination> destinations;

    public Company() {
    }

    public Company(String name, Number number_of_car, Number number_of_line, City city, String longitude,
            String latitude, String reference) {
        this.name = name;
        this.is_actived = true;
        this.number_of_car = number_of_car;
        this.number_of_line = number_of_line;
        this.reference = reference;
        this.cover_link = "company.jpg";
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
    }

    public Long getCompany_key() {
        return company_key;
    }

    public void setCompany_key(Long company_key) {
        this.company_key = company_key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getNumber_of_car() {
        return number_of_car;
    }

    public void setNumber_of_car(Number number_of_car) {
        this.number_of_car = number_of_car;
    }

    public Number getNumber_of_line() {
        return number_of_line;
    }

    public void setNumber_of_line(Number number_of_line) {
        this.number_of_line = number_of_line;
    }

    public boolean getIs_actived() {
        return is_actived;
    }

    public void setIs_actived(boolean is_actived) {
        this.is_actived = is_actived;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCover_link() {
        return cover_link;
    }

    public void setCover_link(String cover_link) {
        this.cover_link = cover_link;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    // public List<Car> getCar() {
    // return car;
    // }

    public void setCar(List<Car> car) {
        this.car = car;
    }

}
