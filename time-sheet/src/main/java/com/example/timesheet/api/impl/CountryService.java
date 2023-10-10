package com.example.timesheet.api.impl;

import com.example.timesheet.core.model.Country;
import com.example.timesheet.core.repository.CountryRepositoryI;
import com.example.timesheet.core.service.CountryServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService implements CountryServiceI {
    private final CountryRepositoryI countryRepository;
    @Autowired
    public CountryService(CountryRepositoryI countryRepository){
        this.countryRepository = countryRepository;
    }
    @Override
    public Country create(Country country) {
        return countryRepository.create(country);
    }

    @Override
    public Country getById(Long id) {
        return countryRepository.getById(id);
    }
}
