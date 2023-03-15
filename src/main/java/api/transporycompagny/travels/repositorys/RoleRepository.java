package api.transporycompagny.travels.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import api.transporycompagny.travels.domains.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByLibelle(String libelle);

}
