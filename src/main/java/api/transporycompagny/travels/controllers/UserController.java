package api.transporycompagny.travels.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.transporycompagny.travels.repositorys.UserRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    public UserController(UserRepository userRepository) {

    }

    @GetMapping("/listing")
    public String getListing() {
        return "hello geeks ";
    }

}
