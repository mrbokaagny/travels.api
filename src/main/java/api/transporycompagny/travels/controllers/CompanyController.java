package api.transporycompagny.travels.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

import api.transporycompagny.travels.domains.City;
import api.transporycompagny.travels.domains.Company;
import api.transporycompagny.travels.dtos.CompanyDto;
import api.transporycompagny.travels.repositorys.CityRepository;
import api.transporycompagny.travels.repositorys.CompanyRepository;
import api.transporycompagny.travels.utils.ResponseApi;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CityRepository cityRepository;

    private ResponseApi apiResponse = new ResponseApi();

    @GetMapping("/listing")
    public ResponseEntity<?> getAllCompany() {
        Iterable<Company> data = companyRepository.findAll();
        this.apiResponse.hydrateData("NONE", "List of all companies .", "success",
                (List<Company>) data);
        return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.OK);
    }

    @GetMapping("/city/compagnies/{city_key}")
    public ResponseEntity<?> getCompagnyOfCity(@PathVariable Long city_key) {
        try {
            Optional<City> tmp_data = cityRepository.findById(city_key);
            if (tmp_data.isEmpty()) {
                this.apiResponse.hydrateData("NONE", "This city doesn't exist in this system .", "error",
                        null);
                return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.NOT_FOUND);
            } else {
                Optional<List<Company>> companies = companyRepository.findByCity(tmp_data.get());
                if (companies.isEmpty()) {
                    this.apiResponse.hydrateData("NONE", "No company for this city .", "success",
                            companies.get());
                    return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.OK);
                } else {
                    this.apiResponse.hydrateData("NONE", "Listing company of city .", "success",
                            companies.get());
                    return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            return this.apiResponse.builErrorJson(e);
        }
    }

    @PostMapping("/created")
    public ResponseEntity<?> createdCompany(@RequestBody CompanyDto company) {
        try {
            Optional<City> tmp_data = cityRepository.findById(company.getCity_key());
            if (tmp_data.isEmpty()) {
                this.apiResponse.hydrateData("NONE", "The city to select is unknow in this system .", "error",
                        null);
                return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.NOT_FOUND);
            } else {
                UUID uuid = UUID.randomUUID();
                Company compagny_save = new Company(company.getName(), company.getNumber_of_car(),
                        company.getNumber_of_line(), tmp_data.get(), company.getLongitude(), company.getLatitude(),
                        uuid.toString());
                companyRepository.save(compagny_save);
                this.apiResponse.hydrateData("NONE", "Created city in successully .", "success",
                        null);
                return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return this.apiResponse.builErrorJson(e);
        }
    }

}
