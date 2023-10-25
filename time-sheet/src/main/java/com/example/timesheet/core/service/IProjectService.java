package com.example.timesheet.core.service;

import com.example.timesheet.core.model.Project;
import com.example.timesheet.core.model.UserInfo;

import java.util.List;

public interface IProjectService {
    Project create(Project project);
    Project getById(Long id);
    void delete(Long id);
    List<Project> getAll(UserInfo userInfo);
    Project update(Project project);
    List<Project> getClientProjects(Long clientId);

}
