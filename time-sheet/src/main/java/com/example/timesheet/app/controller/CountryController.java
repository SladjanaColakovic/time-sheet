package com.example.timesheet.app.controller;

import com.example.timesheet.app.dto.NewCountryDTO;
import com.example.timesheet.core.model.Country;
import com.example.timesheet.core.service.CountryServiceI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/country")
public class CountryController {
    private final CountryServiceI countryService;
    private final ModelMapper mapper;
    @Autowired
    public CountryController(CountryServiceI countryService, ModelMapper mapper){
        this.countryService = countryService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewCountryDTO newCountry){
        return new ResponseEntity<>(countryService.create(mapper.map(newCountry, Country.class)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Country country = countryService.getById(id);
        if(country == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

}
