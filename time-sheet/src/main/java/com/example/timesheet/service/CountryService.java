package com.example.timesheet.service;

import com.example.timesheet.core.model.Country;
import com.example.timesheet.core.repository.ICountryRepository;
import com.example.timesheet.core.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService implements ICountryService {

    @Autowired
    private ICountryRepository countryRepository;

    @Override
    public Country create(Country country) {
        return countryRepository.create(country);
    }

    @Override
    public Country getById(Long id) {
        return countryRepository.getById(id);
    }

    @Override
    public void delete(Long id) {
        countryRepository.delete(id);
    }

    @Override
    public List<Country> getAll() {
        return countryRepository.getAll();
    }

    @Override
    public Country update(Country country) {
        return countryRepository.update(country);
    }
}
