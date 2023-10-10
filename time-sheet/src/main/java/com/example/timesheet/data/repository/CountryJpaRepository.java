package com.example.timesheet.data.repository;

import com.example.timesheet.data.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryJpaRepository extends JpaRepository<CountryEntity, Long> {
}
