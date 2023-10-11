package com.example.timesheet.data.impl;

import com.example.timesheet.core.exception.ObjectNotFoundException;
import com.example.timesheet.core.model.Country;
import com.example.timesheet.core.repository.ICountryRepository;
import com.example.timesheet.data.entity.CountryEntity;
import com.example.timesheet.data.repository.CountryJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
        if(country == null) throw new ObjectNotFoundException();
        return  mapper.map(country, Country.class);
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
                .map(element -> mapper.map(element, Country.class))
                .collect(Collectors.toList());
    }

    @Override
    public Country update(Country country) {
        CountryEntity editing = mapper.map(country, CountryEntity.class);
        CountryEntity saved = countryJpaRepository.save(editing);
        return mapper.map(saved, Country.class);
    }
}
