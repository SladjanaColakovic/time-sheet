package com.example.timesheet.app.controller;

import com.example.timesheet.CustomMapper;
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
        return new ResponseEntity<>(mapper.countryToCountryDTO(country), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Country country = countryService.getById(id);
        return new ResponseEntity<>(mapper.countryToCountryDTO(country), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Country> countries = countryService.getAll();
        return new ResponseEntity<>(countries.stream()
                .map(mapper::countryToCountryDTO)
                .collect(Collectors.toList()), HttpStatus.OK
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
        return new ResponseEntity<>(mapper.countryToCountryDTO(country), HttpStatus.OK);
    }

}
