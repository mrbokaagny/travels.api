package api.transporycompagny.travels.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import api.transporycompagny.travels.domains.User;

public interface UserRepository extends JpaRepository<User , Long> {
    
}
