package com.example.timesheet.core.repository;

import com.example.timesheet.core.model.Project;

public interface IProjectRepository {
    Project create(Project project);
    Project getById(Long id);
}
