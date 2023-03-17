package api.transporycompagny.travels.domains;

import java.util.List;

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
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long car_key;

    @Column(name = "matricule", nullable = false)
    private String matricule;

    @Column(name = "nombre_place", nullable = false)
    private Number number_of_place;

    @Column(name = "libelle", nullable = false)
    private String libelle;

    @OneToOne()
    @JoinColumn(name = "company_key")
    private Company company;

    @OneToMany(mappedBy = "car" , cascade = CascadeType.ALL)
    private List<StartALine> start_a_line;

    public Car() {
    }

    public Car(String matricule, Number number_of_place, String libelle, Company company) {
        this.matricule = matricule;
        this.number_of_place = number_of_place;
        this.libelle = libelle;
        this.company = company;
    }

    public Long getCar_key() {
        return car_key;
    }

    public void setCar_key(Long car_key) {
        this.car_key = car_key;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Number getNumber_of_place() {
        return number_of_place;
    }

    public void setNumber_of_place(Number number_of_place) {
        this.number_of_place = number_of_place;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
