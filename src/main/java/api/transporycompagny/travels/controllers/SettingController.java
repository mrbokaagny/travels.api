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
import api.transporycompagny.travels.domains.City;
import api.transporycompagny.travels.domains.Country;
import api.transporycompagny.travels.dtos.CityDto;
import api.transporycompagny.travels.repositorys.CityRepository;
import api.transporycompagny.travels.repositorys.CountryRespository;
import api.transporycompagny.travels.utils.ResponseApi;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/setting")
public class SettingController {

    @Autowired
    CountryRespository countryResponsitory;

    @Autowired
    CityRepository cityRepository;

    private ResponseApi apiResponse = new ResponseApi();

    // country api
    @GetMapping("/country/listing")
    public ResponseEntity<?> getCountryList() {
        Iterable<Country> data = countryResponsitory.findAll();
        this.apiResponse.hydrateData("NONE", "List of all countries .", "success",
                (List<Country>) data);
        return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.OK);
    }

    @GetMapping("/country/cities/{country_key}")
    public ResponseEntity<?> getCityfCountry(@PathVariable Long country_key) {
        try {
            Optional<Country> current_country = countryResponsitory.findById(country_key);
            if (current_country.isEmpty()) {
                this.apiResponse.hydrateData("NONE", "This country doesn't exist in this system .", "error",
                        null);
                return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.NOT_FOUND);
            } else {
                Optional<List<City>> cities = cityRepository.findByCountry(current_country.get());
                if (cities.isEmpty()) {
                    this.apiResponse.hydrateData("NONE", "No city for this country .", "success",
                            cities.get());
                    return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.OK);
                } else {
                    this.apiResponse.hydrateData("NONE", "Listing city of country .", "success",
                            cities.get());
                    return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            return this.apiResponse.builErrorJson(e);
        }
    }

    @PostMapping("/country/creating")
    public ResponseEntity<?> createCountry(@RequestBody Country country) {
        try {
            Country tmp_data = countryResponsitory.findByLibelle(country.getLibelle());
            if (tmp_data == null) {
                countryResponsitory.save(country);
                this.apiResponse.hydrateData("NONE", "Created country is successully .", "success",
                        null);
                return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.OK);
            } else {
                this.apiResponse.hydrateData("NONE", "This country already exist .", "error",
                        null);
                return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return this.apiResponse.builErrorJson(e);
        }
    }

    // city api
    @GetMapping("/city/listing")
    public ResponseEntity<?> getCityList() {
        Iterable<City> data = cityRepository.findAll();
        this.apiResponse.hydrateData("NONE", "List of all cities .", "success",
                (List<City>) data);
        return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.OK);
    }

    @PostMapping("/city/creating")
    public ResponseEntity<?> createCity(@RequestBody CityDto city) {
        try {
            City tmp_data = cityRepository.findByLibelle(city.getLibelle());
            if (tmp_data == null) {
                Optional<Country> current_country = countryResponsitory.findById(city.getCountry_key());

                if (current_country.isEmpty()) {
                    this.apiResponse.hydrateData("NONE", "The country to select is unknow in this system .", "error",
                            null);
                    return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.NOT_FOUND);
                } else {
                    City city_save = new City(city.getLibelle(), current_country.get());
                    cityRepository.save(city_save);
                    this.apiResponse.hydrateData("NONE", "Created city is successully .", "success",
                            null);
                    return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.OK);
                }
            } else {
                this.apiResponse.hydrateData("NONE", "This city already exist .", "error",
                        null);
                return new ResponseEntity<>(this.apiResponse.buildJson(), HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return this.apiResponse.builErrorJson(e);
        }
    }

}
