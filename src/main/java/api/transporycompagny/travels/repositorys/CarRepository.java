package api.transporycompagny.travels.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import api.transporycompagny.travels.domains.Car;
import api.transporycompagny.travels.domains.Company;

public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByMatricule(String matricule);

    Optional<List<Car>> findByCompany(Company company_key);
}
