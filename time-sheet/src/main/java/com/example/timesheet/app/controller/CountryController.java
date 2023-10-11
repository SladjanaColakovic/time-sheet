package com.example.timesheet.app.controller;

import com.example.timesheet.app.dto.CategoryUpdateDTO;
import com.example.timesheet.app.dto.CountryDTO;
import com.example.timesheet.app.dto.NewCountryDTO;
import com.example.timesheet.core.model.Country;
import com.example.timesheet.core.service.ICountryService;
import org.modelmapper.ModelMapper;
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

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Country> countries = countryService.getAll();
        return new ResponseEntity<>(countries.stream()
                .map(element -> mapper.map(element, CountryDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK
        );
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") Long id){
        countryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody CategoryUpdateDTO editing){
        Country country = countryService.update(mapper.map(editing, Country.class));
        return new ResponseEntity<>(mapper.map(country, CountryDTO.class), HttpStatus.OK);
    }

}
