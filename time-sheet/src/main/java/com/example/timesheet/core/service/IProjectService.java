package com.example.timesheet.core.service;

import com.example.timesheet.core.model.Project;

public interface IProjectService {
    Project create(Project project);
    Project getById(Long id);
}
