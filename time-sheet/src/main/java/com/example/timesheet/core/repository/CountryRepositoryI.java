package com.example.timesheet.core.repository;

import com.example.timesheet.core.model.Country;

public interface CountryRepositoryI {
    Country create(Country country);
    Country getById(Long id);
}
