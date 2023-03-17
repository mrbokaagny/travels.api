package api.transporycompagny.travels.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import api.transporycompagny.travels.domains.Line;

public interface LineRepository extends JpaRepository<Line , Long> {
    
}
