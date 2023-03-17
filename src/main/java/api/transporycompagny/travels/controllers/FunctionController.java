package api.transporycompagny.travels.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.transporycompagny.travels.domains.Function;
import api.transporycompagny.travels.repositorys.FunctionRepository;
import api.transporycompagny.travels.utils.ResponseApi;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/function")
public class FunctionController {

    @Autowired
    FunctionRepository functionRepository;

    private ResponseApi apiResponse = new ResponseApi();

    @PostMapping("/created")
    public ResponseEntity<?> createFunction(@RequestBody Function function) {
        try {
            Optional<Function> tmp_data = functionRepository.findByLibelle(function.getLibelle());
            if (tmp_data.isEmpty()) {
                functionRepository.save(function);
                this.apiResponse.hydrateData("NONE", "Created function is successully .", "success",
                        null);
                return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.OK);
            } else {
                this.apiResponse.hydrateData("NONE", "This function already exist .", "error",
                        null);
                return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return this.apiResponse.builErrorJson(e);
        }
    }

}
