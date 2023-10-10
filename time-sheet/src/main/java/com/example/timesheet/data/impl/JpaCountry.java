package com.example.timesheet.data.impl;

import com.example.timesheet.core.model.Country;
import com.example.timesheet.core.repository.CountryRepositoryI;
import com.example.timesheet.data.entity.CountryEntity;
import com.example.timesheet.data.repository.CountryEntityRepositoryI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JpaCountry implements CountryRepositoryI {
    private final CountryEntityRepositoryI countryEntityRepository;
    private final ModelMapper mapper;
    @Autowired
    public JpaCountry(CountryEntityRepositoryI countryEntityRepository, ModelMapper mapper){
        this.countryEntityRepository = countryEntityRepository;
        this.mapper = mapper;
    }
    @Override
    public Country create(Country country) {
        CountryEntity newEntity = mapper.map(country, CountryEntity.class);
        return mapper.map(countryEntityRepository.save(newEntity), Country.class);
    }

    @Override
    public Country getById(Long id) {
        return  mapper.map(countryEntityRepository.findById(id), Country.class);
    }
}
