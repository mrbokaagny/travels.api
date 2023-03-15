package api.transporycompagny.travels.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import api.transporycompagny.travels.domains.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {

    Optional<Login> findByLogin(String login);

}
