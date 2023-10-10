package com.example.timesheet.app.controller;

import com.example.timesheet.app.dto.CountryDTO;
import com.example.timesheet.app.dto.NewCountryDTO;
import com.example.timesheet.core.model.Country;
import com.example.timesheet.core.service.ICountryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/country")
public class CountryController {
    private final ICountryService countryService;
    private final ModelMapper mapper;
    @Autowired
    public CountryController(ICountryService countryService, ModelMapper mapper){
        this.countryService = countryService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewCountryDTO newCountry){
        Country country = countryService.create(mapper.map(newCountry, Country.class));
        return new ResponseEntity<>(mapper.map(country, CountryDTO.class), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Country country = countryService.getById(id);
        return new ResponseEntity<>(mapper.map(country, CountryDTO.class), HttpStatus.OK);
    }

}
