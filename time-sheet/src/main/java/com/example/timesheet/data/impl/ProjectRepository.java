package com.example.timesheet.data.impl;

import com.example.timesheet.CustomMapper;
import com.example.timesheet.core.exception.ObjectNotFoundException;
import com.example.timesheet.core.model.Project;
import com.example.timesheet.core.repository.IProjectRepository;
import com.example.timesheet.data.entity.ProjectEntity;
import com.example.timesheet.data.repository.ProjectJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectRepository implements IProjectRepository {

    @Autowired
    private ProjectJpaRepository projectJpaRepository;

    @Autowired
    private CustomMapper mapper;


    @Override
    public Project create(Project project) {
        ProjectEntity newEntity = mapper.projectToProjectEntity(project);
        ProjectEntity saved = projectJpaRepository.save(newEntity);
        return mapper.projectEntityToProject(saved);
    }

    @Override
    public Project getById(Long id) {
        ProjectEntity project = projectJpaRepository.findById(id).orElse(null);
        if(project == null) throw new ObjectNotFoundException();
        return mapper.projectEntityToProject(project);
    }

    @Override
    public void delete(Long id) {
        ProjectEntity project = projectJpaRepository.findById(id).orElse(null);
        if(project == null) throw new ObjectNotFoundException();
        projectJpaRepository.delete(project);
    }

    @Override
    public List<Project> getAll() {
        List<ProjectEntity> projects = projectJpaRepository.findAll();
        return projects
                .stream()
                .map(mapper::projectEntityToProject)
                .collect(Collectors.toList());
    }

    @Override
    public Project update(Project project) {
        ProjectEntity editing = projectJpaRepository.findById(project.getId()).orElse(null);
        if(editing == null) throw new ObjectNotFoundException();
        mapper.projectToProjectEntityUpdate(project, editing);
        ProjectEntity saved = projectJpaRepository.save(editing);
        return mapper.projectEntityToProject(saved);
    }

    @Override
    public List<Project> getLeadingProjects(Long teamMemberId) {
        List<ProjectEntity> projects = projectJpaRepository.getLeadingProjects(teamMemberId);
        return projects
                .stream()
                .map(mapper::projectEntityToProject)
                .collect(Collectors.toList());
    }

    @Override
    public List<Project> getClientProjects(Long clientId) {
        List<ProjectEntity> projects = projectJpaRepository.getClientProjects(clientId);
        return projects
                .stream()
                .map(mapper::projectEntityToProject)
                .collect(Collectors.toList());
    }
}
