package com.example.timesheet.core.repository;

import com.example.timesheet.core.model.Project;

import java.util.List;

public interface IProjectRepository {
    Project create(Project project);
    Project getById(Long id);
    void delete(Long id);
    List<Project> getAll();
    Project update(Project project);
    List<Project> getLeadingProjects(String username);
}
