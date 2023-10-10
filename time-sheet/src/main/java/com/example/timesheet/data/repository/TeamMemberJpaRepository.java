package com.example.timesheet.data.repository;

import com.example.timesheet.data.entity.TeamMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamMemberJpaRepository extends JpaRepository<TeamMemberEntity, Long> {
}
