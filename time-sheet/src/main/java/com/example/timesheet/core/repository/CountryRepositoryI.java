package com.example.timesheet.core.repository;

import com.example.timesheet.core.model.Country;
import org.springframework.stereotype.Component;

public interface CountryRepositoryI {
    public Country create(Country country);
    public Country getById(Long id);
}
