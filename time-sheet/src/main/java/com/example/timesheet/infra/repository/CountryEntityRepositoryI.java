package com.example.timesheet.infra.repository;

import com.example.timesheet.infra.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryEntityRepositoryI extends JpaRepository<CountryEntity, Long> {
}
