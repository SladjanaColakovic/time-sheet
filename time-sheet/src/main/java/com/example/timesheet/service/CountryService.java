package com.example.timesheet.service;

import com.example.timesheet.core.model.Country;
import com.example.timesheet.core.repository.ICountryRepository;
import com.example.timesheet.core.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService implements ICountryService {
    private final ICountryRepository countryRepository;
    @Autowired
    public CountryService(ICountryRepository countryRepository){
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
