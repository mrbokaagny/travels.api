package api.transporycompagny.travels.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import api.transporycompagny.travels.domains.Function;

public interface FunctionRepository extends JpaRepository<Function, Long> {

    Optional<Function> findByLibelle(String libelle);
}
