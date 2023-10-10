package com.example.timesheet.data.impl;

import com.example.timesheet.core.model.Project;
import com.example.timesheet.core.repository.IProjectRepository;
import com.example.timesheet.data.entity.ProjectEntity;
import com.example.timesheet.data.repository.ProjectJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectJpa implements IProjectRepository {

    private final ProjectJpaRepository projectJpaRepository;

    private final ModelMapper mapper;

    @Autowired
    public ProjectJpa(ProjectJpaRepository projectJpaRepository, ModelMapper mapper){
        this.projectJpaRepository = projectJpaRepository;
        this.mapper = mapper;
    }
    @Override
    public Project create(Project project) {
        ProjectEntity newEntity = mapper.map(project, ProjectEntity.class);
        ProjectEntity saved = projectJpaRepository.save(newEntity);
        return mapper.map(saved, Project.class);
    }

    @Override
    public Project getById(Long id) {
        ProjectEntity project = projectJpaRepository.findById(id).orElse(null);
        return mapper.map(project, Project.class);
    }
}
