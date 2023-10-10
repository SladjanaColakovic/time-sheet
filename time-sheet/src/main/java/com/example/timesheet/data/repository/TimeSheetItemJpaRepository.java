package com.example.timesheet.data.repository;

import com.example.timesheet.data.entity.TimeSheetItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSheetItemJpaRepository extends JpaRepository<TimeSheetItemEntity, Long> {
}
