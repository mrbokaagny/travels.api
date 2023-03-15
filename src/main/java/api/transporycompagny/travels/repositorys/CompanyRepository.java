package api.transporycompagny.travels.repositorys;


import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import api.transporycompagny.travels.domains.City;
import api.transporycompagny.travels.domains.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<List<Company>> findByCity(City city_key);
}
