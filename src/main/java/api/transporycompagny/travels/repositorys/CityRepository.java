package api.transporycompagny.travels.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import api.transporycompagny.travels.domains.City;
import api.transporycompagny.travels.domains.Country;

public interface CityRepository extends JpaRepository<City, Long> {
    City findByLibelle(String libelle);

    Optional<List<City>> findByCountry(Country country_key) ;
}
