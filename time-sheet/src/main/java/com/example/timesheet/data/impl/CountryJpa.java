package com.example.timesheet.data.impl;

import com.example.timesheet.core.exception.ObjectNotFound;
import com.example.timesheet.core.model.Country;
import com.example.timesheet.core.repository.ICountryRepository;
import com.example.timesheet.data.entity.CountryEntity;
import com.example.timesheet.data.repository.CountryJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountryJpa implements ICountryRepository {
    private final CountryJpaRepository countryJpaRepository;
    private final ModelMapper mapper;
    @Autowired
    public CountryJpa(CountryJpaRepository countryJpaRepository, ModelMapper mapper){
        this.countryJpaRepository = countryJpaRepository;
        this.mapper = mapper;
    }
    @Override
    public Country create(Country country) {
        CountryEntity newEntity = mapper.map(country, CountryEntity.class);
        CountryEntity saved = countryJpaRepository.save(newEntity);
        return mapper.map(saved, Country.class);
    }

    @Override
    public Country getById(Long id) {
        CountryEntity country = countryJpaRepository.findById(id).orElse(null);
        if(country == null) throw new ObjectNotFound();
        return  mapper.map(country, Country.class);
    }
}
