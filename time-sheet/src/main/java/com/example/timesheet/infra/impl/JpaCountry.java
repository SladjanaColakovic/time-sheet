package com.example.timesheet.infra.impl;

import com.example.timesheet.core.model.Country;
import com.example.timesheet.core.repository.CountryRepositoryI;
import com.example.timesheet.infra.entity.CountryEntity;
import com.example.timesheet.infra.repository.CountryEntityRepositoryI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JpaCountry implements CountryRepositoryI {

    @Autowired
    private CountryEntityRepositoryI countryEntityRepository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public Country create(Country country) {
        CountryEntity countryEntity = mapper.map(country, CountryEntity.class);
        CountryEntity saved = countryEntityRepository.save(countryEntity);
        return mapper.map(saved, Country.class);
    }

    @Override
    public Country getById(Long id) {
        return  mapper.map(countryEntityRepository.findById(id), Country.class);
    }
}
