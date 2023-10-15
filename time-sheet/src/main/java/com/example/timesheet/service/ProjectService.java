package com.example.timesheet.service;

import com.example.timesheet.core.model.Project;
import com.example.timesheet.core.repository.IProjectRepository;
import com.example.timesheet.core.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    private  IProjectRepository projectRepository;

    @Override
    public Project create(Project project) {
        return projectRepository.create(project);
    }

    @Override
    public Project getById(Long id) {
        return projectRepository.getById(id);
    }

    @Override
    public void delete(Long id) {
        projectRepository.delete(id);
    }

    @Override
    public List<Project> getAll() {
        return projectRepository.getAll();
    }

    @Override
    public Project update(Project project) {
        return projectRepository.update(project);
    }

    @Override
    public List<Project> getLeadingProjects(String username) {
        return projectRepository.getLeadingProjects(username);
    }
}
