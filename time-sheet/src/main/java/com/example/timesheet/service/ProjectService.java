package com.example.timesheet.service;

import com.example.timesheet.core.model.Project;
import com.example.timesheet.core.repository.IProjectRepository;
import com.example.timesheet.core.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService implements IProjectService {

    private final IProjectRepository projectRepository;

    @Autowired
    public ProjectService(IProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }
    @Override
    public Project create(Project project) {
        return projectRepository.create(project);
    }

    @Override
    public Project getById(Long id) {
        return projectRepository.getById(id);
    }
}
