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
@RequestMapping(value = "/country")
public class CountryController {

    @Autowired
    private CountryServiceI countryService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewCountryDTO newCountry){
        return new ResponseEntity<>(countryService.create(mapper.map(newCountry, Country.class)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return new ResponseEntity(countryService.getById(id), HttpStatus.OK);
    }

}
