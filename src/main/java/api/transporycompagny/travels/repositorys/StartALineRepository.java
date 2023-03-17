package api.transporycompagny.travels.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import api.transporycompagny.travels.domains.StartALine;

public interface StartALineRepository extends JpaRepository<StartALine, Long> {

    @Query("SELECT startline FROM StartALine startline WHERE startline.car=?1 AND startline.company=?2 AND startline.status=?3")
    Optional<StartALine> findByCarAndCompany(Long car_key, Long company_key, String status);
}
