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
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long city_key;

    @Column(name = "libelle", nullable = false)
    private String libelle;

    @OneToOne
    @JoinColumn(name = "country_key", nullable = false)
    private Country country;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "city")
    private List<Company> company;


    public City() {
    }

    public City(String libelle, Country country_key) {
        this.libelle = libelle;
        this.country = country_key;
    }

    public Long getCity_key() {
        return city_key;
    }

    public void setCity_key(Long city_key) {
        this.city_key = city_key;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    // public Country getCountry() {
    //     return country;
    // }

    public void setCountry(Country country) {
        this.country = country;
    }

    // public List<Company> getCompany() {
    //     return company;
    // }

    public void setCompany(List<Company> company) {
        this.company = company;
    }

    

}
