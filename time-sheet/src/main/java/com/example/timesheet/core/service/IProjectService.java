package com.example.timesheet.core.service;

import com.example.timesheet.core.model.Project;

import java.util.List;

public interface IProjectService {
    Project create(Project project);
    Project getById(Long id);
    void delete(Long id);
    List<Project> getAll(Long teamMemberId, String role);
    Project update(Project project);

}
