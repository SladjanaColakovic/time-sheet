package com.example.timesheet.core.service;

import com.example.timesheet.core.model.Country;

public interface ICountryService {
    Country create(Country country);
    Country getById(Long id);
}
