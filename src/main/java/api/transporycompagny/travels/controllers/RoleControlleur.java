package api.transporycompagny.travels.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import api.transporycompagny.travels.domains.Role;
import api.transporycompagny.travels.repositorys.RoleRepository;
import api.transporycompagny.travels.utils.ResponseApi;

@RestController
@RequestMapping("/api/role")
public class RoleControlleur {

    @Autowired
    RoleRepository roleRepository;

    private ResponseApi apiResponse = new ResponseApi();

    @GetMapping("/listing")
    public ResponseEntity<?> getRoleList() {
        Iterable<Role> data = roleRepository.findAll();
        this.apiResponse.hydrateData("NONE", "List of all roles", "success",
                (List<Role>) data);
        return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.OK);
    }

    @PostMapping("/created")
    public ResponseEntity<?> createRole(@RequestBody Role role) {
        try {
            Role tmpData = roleRepository.findByLibelle(role.getLibelle());
            if (tmpData == null) {
                roleRepository.save(role);
                this.apiResponse.hydrateData("NONE", "Created role in successully .", "success",
                        null);
                return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.OK);
            } else {
                this.apiResponse.hydrateData("NONE", "This role already exist .", "error",
                        null);
                return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return this.apiResponse.builErrorJson(e);
        }
    }
}
