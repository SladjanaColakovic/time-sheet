package com.example.timesheet.core.service;

import com.example.timesheet.core.model.Country;

import java.util.List;

public interface ICountryService {
    Country create(Country country);
    Country getById(Long id);
    void delete(Long id);
    List<Country> getAll();
    Country update(Country country);
}
