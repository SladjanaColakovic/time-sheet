package com.example.timesheet.app.controller;

import com.example.timesheet.app.dto.NewProjectDTO;
import com.example.timesheet.app.dto.ProjectDTO;
import com.example.timesheet.core.enumeration.ProjectStatus;
import com.example.timesheet.core.model.Project;
import com.example.timesheet.core.service.IProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/project")
public class ProjectController {

    private final IProjectService projectService;

    private final ModelMapper mapper;

    @Autowired
    public ProjectController(IProjectService projectService, ModelMapper mapper) {
        this.projectService = projectService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewProjectDTO newProject){
        Project project = mapper.map(newProject, Project.class);
        project.setStatus(ProjectStatus.ACTIVE);
        Project created = projectService.create(project);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Project project = projectService.getById(id);
        if(project == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(mapper.map(project, ProjectDTO.class), HttpStatus.OK);
    }


}
