package com.example.timesheet.core.service;

import com.example.timesheet.core.model.Project;

import java.util.List;

public interface IProjectService {
    Project create(Project project);
    Project getById(Long id);
    void delete(Long id);
    List<Project> getAll();
    Project update(Project project);
}
