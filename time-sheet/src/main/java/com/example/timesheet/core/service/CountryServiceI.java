package com.example.timesheet.core.service;

import com.example.timesheet.core.model.Country;

public interface CountryServiceI {
    public Country create(Country country);
    public Country getById(Long id);
}
