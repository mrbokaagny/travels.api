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

import api.transporycompagny.travels.domains.Company;
import api.transporycompagny.travels.domains.Destination;
import api.transporycompagny.travels.domains.Line;
import api.transporycompagny.travels.dtos.LineDto;
import api.transporycompagny.travels.repositorys.CompanyRepository;
import api.transporycompagny.travels.repositorys.DestinationRepository;
import api.transporycompagny.travels.repositorys.LineRepository;
import api.transporycompagny.travels.utils.ResponseApi;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/line")
public class LineController {

    @Autowired
    LineRepository lineRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    DestinationRepository destinationRepository;

    private ResponseApi apiResponse = new ResponseApi();

    @PostMapping("/created")
    public ResponseEntity<?> createLigne(@RequestBody LineDto line) {
        try {
            Optional<Company> company = companyRepository.findById(line.getCompany_key());
            if (company.isEmpty()) {
                this.apiResponse.hydrateData("NONE", "This company doesn't exist in this system .", "error",
                        null);
                return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.NOT_FOUND);
            } else {
                Line ligne_saved = new Line(line.getStarting_point(), line.getPoint_of_arrival());
                Line saved = lineRepository.save(ligne_saved);

                Destination destination = new Destination(line.getPrice(), saved, company.get());
                destinationRepository.save(destination);
                this.apiResponse.hydrateData("NONE", "Created destination is successully .", "success",
                        null);
                return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return this.apiResponse.builErrorJson(e);
        }
    }

}
