package com.example.timesheet.data.impl;

import com.example.timesheet.CustomMapper;
import com.example.timesheet.core.exception.ObjectNotFoundException;
import com.example.timesheet.core.model.Country;
import com.example.timesheet.core.repository.ICountryRepository;
import com.example.timesheet.data.entity.CountryEntity;
import com.example.timesheet.data.repository.CountryJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CountryRepository implements ICountryRepository {

    @Autowired
    private CountryJpaRepository countryJpaRepository;

    @Autowired
    private CustomMapper mapper;

    @Override
    public Country create(Country country) {
        CountryEntity newEntity = mapper.countryToCountryEntity(country);
        CountryEntity saved = countryJpaRepository.save(newEntity);
        return mapper.countryEntityToCountry(saved);
    }

    @Override
    public Country getById(Long id) {
        CountryEntity country = countryJpaRepository.findById(id).orElse(null);
        if(country == null) throw new ObjectNotFoundException();
        return  mapper.countryEntityToCountry(country);
    }

    @Override
    public void delete(Long id) {
        CountryEntity country = countryJpaRepository.findById(id).orElse(null);
        if(country == null) throw new ObjectNotFoundException();
        countryJpaRepository.delete(country);
    }

    @Override
    public List<Country> getAll() {
        List<CountryEntity> countries = countryJpaRepository.findAll();
        return countries
                .stream()
                .map(mapper::countryEntityToCountry)
                .collect(Collectors.toList());
    }

    @Override
    public Country update(Country country) {
        CountryEntity editing = countryJpaRepository.findById(country.getId()).orElse(null);
        if(editing == null) throw new ObjectNotFoundException();
        mapper.countryToCountryEntityUpdate(country, editing);
        CountryEntity saved = countryJpaRepository.save(editing);
        return mapper.countryEntityToCountry(saved);
    }
}
