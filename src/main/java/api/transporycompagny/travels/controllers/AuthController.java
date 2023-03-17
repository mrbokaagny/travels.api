package api.transporycompagny.travels.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.transporycompagny.travels.configs.jwtService;
import api.transporycompagny.travels.domains.Function;
import api.transporycompagny.travels.domains.Login;
import api.transporycompagny.travels.domains.Role;
import api.transporycompagny.travels.domains.User;
import api.transporycompagny.travels.dtos.UserDto;
import api.transporycompagny.travels.repositorys.FunctionRepository;
import api.transporycompagny.travels.repositorys.LoginRepository;
import api.transporycompagny.travels.repositorys.RoleRepository;
import api.transporycompagny.travels.repositorys.UserRepository;
import api.transporycompagny.travels.utils.ResponseApi;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoginRepository loginRepository;

    private ResponseApi apiResponse = new ResponseApi();

    @Autowired
    jwtService jwtService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    FunctionRepository functionRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDto user) {
        try {
            Optional<Role> role = roleRepository.findById(user.getRole_key());
            if (role.isEmpty()) {
                this.apiResponse.hydrateData("NONE", "This role doesn't exist in this system .", "error",
                        null);
                return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.NOT_FOUND);
            } else {
                Optional<Login> login = loginRepository.findByLogin(user.getLogin());
                if (login.isEmpty()) {
                    Optional<Function> tmp_function = functionRepository.findById(user.getFunction_key());
                    if (tmp_function.isEmpty()) {
                        this.apiResponse.hydrateData("NONE", "This function doesn't exist in this system .", "error",
                                null);
                        return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.NOT_FOUND);
                    } else {
                        User user_save = new User(user.getName(), user.getSurname(), user.getNumber());
                        User saved = userRepository.save(user_save);

                        Login login_saved = new Login(passwordEncoder.encode(user.getPassword()), user.getLogin(),
                                true,
                                saved,
                                role.get(), tmp_function.get());

                        loginRepository.save(login_saved);

                        this.apiResponse.hydrateData("NONE", "Created user is successully .", "success",
                                null);
                        return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.OK);
                    }
                } else {
                    this.apiResponse.hydrateData("NONE", "This login as already taken .", "error",
                            null);
                    return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.CONFLICT);
                }
            }
        } catch (Exception e) {
            return this.apiResponse.builErrorJson(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto user) {
        try {
            Optional<Login> login = loginRepository.findByLogin(user.getLogin());
            if (login.isEmpty()) {
                this.apiResponse.hydrateData("NONE", "The login or password is no found .", "error",
                        null);
                return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.NOT_FOUND);
            } else {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));

                var jwtToken = jwtService.generateToken(login.get());
                this.apiResponse.hydrateData("NONE", jwtToken, "success",
                        null);
                return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return this.apiResponse.builErrorJson(e);
        }
    }

}
