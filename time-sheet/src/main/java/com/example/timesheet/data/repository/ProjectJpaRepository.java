package com.example.timesheet.data.repository;

import com.example.timesheet.data.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectJpaRepository extends JpaRepository<ProjectEntity, Long> {
    @Query("select p from ProjectEntity p where p.lead.id = :teamMemberId")
    List<ProjectEntity> getLeadingProjects(Long teamMemberId);
}
