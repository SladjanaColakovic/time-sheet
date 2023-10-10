package com.example.timesheet.data.repository;

import com.example.timesheet.data.entity.ProjectSheetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectSheetJpaRepository extends JpaRepository<ProjectSheetEntity, Long> {
}
