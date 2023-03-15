package api.transporycompagny.travels.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import api.transporycompagny.travels.domains.Country;

public interface CountryRespository extends JpaRepository<Country, Long> {
    
    Country findByLibelle(String libelle);
}
