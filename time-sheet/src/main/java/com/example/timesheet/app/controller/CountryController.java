package com.example.timesheet.app.controller;

import com.example.timesheet.CustomMapper;
import com.example.timesheet.app.dto.Countries;
import com.example.timesheet.app.dto.CountryDTO;
import com.example.timesheet.app.dto.CountryUpdateDTO;
import com.example.timesheet.app.dto.NewCountryDTO;
import com.example.timesheet.core.model.Country;
import com.example.timesheet.core.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/country")
public class CountryController {
    private final ICountryService countryService;
    private final CustomMapper mapper;
    @Autowired
    public CountryController(ICountryService countryService, CustomMapper mapper){
        this.countryService = countryService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewCountryDTO newCountry){
        Country country = countryService.create(mapper.newCountryDTOToCountry(newCountry));
        CountryDTO response = mapper.countryToCountryDTO(country);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Country country = countryService.getById(id);
        CountryDTO response = mapper.countryToCountryDTO(country);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Country> countries = countryService.getAll();
        List<CountryDTO> response = countries.stream()
                .map(mapper::countryToCountryDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(new Countries(response), HttpStatus.OK
        );
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") Long id){
        countryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody CountryUpdateDTO editing){
        Country country = countryService.update(mapper.countryUpdateDTOToCountry(editing));
        CountryDTO response = mapper.countryToCountryDTO(country);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
