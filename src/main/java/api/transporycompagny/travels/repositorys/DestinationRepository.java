package api.transporycompagny.travels.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import api.transporycompagny.travels.domains.Destination;

public interface DestinationRepository extends JpaRepository<Destination, Long> {

}
