package api.transporycompagny.travels.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.transporycompagny.travels.domains.Car;
import api.transporycompagny.travels.domains.Company;
import api.transporycompagny.travels.dtos.CarDto;
import api.transporycompagny.travels.repositorys.CarRepository;
import api.transporycompagny.travels.repositorys.CompanyRepository;
import api.transporycompagny.travels.utils.ResponseApi;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/car")
public class CarController {

    @Autowired
    CarRepository carRepository;

    @Autowired
    CompanyRepository companyRepository;

    private ResponseApi apiResponse = new ResponseApi();

    @GetMapping("/listing")
    public ResponseEntity<?> getAllCar() {
        Iterable<Car> data = carRepository.findAll();
        this.apiResponse.hydrateData("NONE", "List of all cars .", "success",
                (List<Car>) data);
        return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.OK);
    }

    @GetMapping("/company/cars/{company_key}")
    public ResponseEntity<?> getCarofCompany(@PathVariable Long company_key) {
        try {
            Optional<Company> tmp_data = companyRepository.findById(company_key);
            if (tmp_data.isEmpty()) {
                this.apiResponse.hydrateData("NONE", "This company doesn't exist in this system .", "error",
                        null);
                return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.NOT_FOUND);
            } else {
                Optional<List<Car>> companies = carRepository.findByCompany(tmp_data.get());
                if (companies.isEmpty()) {
                    this.apiResponse.hydrateData("NONE", "No car company for this city .", "success",
                            companies.get());
                    return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.OK);
                } else {
                    this.apiResponse.hydrateData("NONE", "Listing car of company .", "success",
                            companies.get());
                    return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            return this.apiResponse.builErrorJson(e);
        }
    }

    @PostMapping("/created")
    public ResponseEntity<?> createdCar(@RequestBody CarDto car) {
        try {
            Optional<Company> tmp_data = companyRepository.findById(car.getCompany_key());
            if (tmp_data.isEmpty()) {
                this.apiResponse.hydrateData("NONE", "This company doesn't not exist in this system .", "error",
                        null);
                return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.NOT_FOUND);
            } else {
                Optional<Car> tmp_car = carRepository.findByMatricule(car.getMatricule());
                if (tmp_car.isEmpty()) {
                    Car car_save = new Car(car.getMatricule(), car.getNumber_of_place(), car.getLibelle(),
                            tmp_data.get());
                    carRepository.save(car_save);
                    this.apiResponse.hydrateData("NONE", "Created car is successfully .", "success",
                            null);
                    return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.OK);
                } else {
                    this.apiResponse.hydrateData("NONE", "This matricule is already taken .", "error",
                            null);
                    return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.CONFLICT);
                }
            }
        } catch (Exception e) {
            return this.apiResponse.builErrorJson(e);
        }
    }
}
