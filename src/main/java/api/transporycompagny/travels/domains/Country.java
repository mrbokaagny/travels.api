package api.transporycompagny.travels.domains;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long country_key;

    @Column(name = "libelle", nullable = false)
    private String libelle;

    @Column(name = "country_code", nullable = false)
    private String country_code;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    private List<City> cities;

    public Country() {
    }

    public Country(String libelle, String country_code) {
        this.libelle = libelle;
        this.country_code = country_code;
    }

    public Long getCountry_key() {
        return country_key;
    }

    public void setCountry_key(Long country_key) {
        this.country_key = country_key;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    

}
