package api.transporycompagny.travels.controllers;

import java.net.http.HttpClient;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.transporycompagny.travels.domains.Car;
import api.transporycompagny.travels.domains.Company;
import api.transporycompagny.travels.domains.Destination;
import api.transporycompagny.travels.domains.StartALine;
import api.transporycompagny.travels.dtos.StartALineDto;
import api.transporycompagny.travels.repositorys.CarRepository;
import api.transporycompagny.travels.repositorys.CompanyRepository;
import api.transporycompagny.travels.repositorys.DestinationRepository;
import api.transporycompagny.travels.repositorys.StartALineRepository;
import api.transporycompagny.travels.utils.ResponseApi;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/startaline")
public class StartALineController {

    @Autowired
    StartALineRepository startALineRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    DestinationRepository destinationRepository;

    private ResponseApi apiResponse = new ResponseApi();

    @PostMapping("/created")
    public ResponseEntity<?> createdStartALine(@RequestBody StartALineDto startALine) {
        try {
            Optional<Company> company = companyRepository.findById(startALine.getCompany_key());
            if (company.isEmpty()) {
                this.apiResponse.hydrateData("COMPANY_NOT_FOUND", "This company doesn't existit in this system",
                        "error", null);
                return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.NOT_FOUND);
            } else {
                Optional<Car> car = carRepository.findById(startALine.getCar_key());
                if (car.isEmpty()) {
                    this.apiResponse.hydrateData("CAR_NOT_FOUND", "This car doesn't existit in this company",
                            "error", null);
                    return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.NOT_FOUND);
                } else {
                    Optional<Destination> destination = destinationRepository.findById(startALine.getDestination_key());
                    if (destination.isEmpty()) {
                        this.apiResponse.hydrateData("DESTINATION_NOT_FOUND",
                                "This destination doesn't existit in this company",
                                "error", null);
                        return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.NOT_FOUND);
                    } else {
                        Optional<StartALine> tmp_data = startALineRepository.findByCarAndCompany(
                                startALine.getCar_key(),
                                startALine.getCompany_key(), "wainting");
                        if (tmp_data.isEmpty()) {
                            StartALine saved = new StartALine(car.get(), company.get(), "wainting", destination.get());
                            startALineRepository.save(saved);
                            this.apiResponse.hydrateData("NONE", "Created starting line is successully",
                                    "success", null);
                            return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.OK);
                        } else {
                            this.apiResponse.hydrateData("ACTION_NOT_POSSIBLE", "Created starting line is successully",
                                    "error", null);
                            return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.CONFLICT);
                        }
                    }
                }
            }
        } catch (Exception e) {
            return this.apiResponse.builErrorJson(e);
        }
    }
}
