package com.example.timesheet.app.controller;

import com.example.timesheet.app.dto.NewProjectDTO;
import com.example.timesheet.app.dto.ProjectDTO;
import com.example.timesheet.app.dto.ProjectUpdateDTO;
import com.example.timesheet.core.model.Project;
import com.example.timesheet.core.service.IProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        Project created = projectService.create(project);
        return new ResponseEntity<>(mapper.map(created, ProjectDTO.class), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Project project = projectService.getById(id);
        return new ResponseEntity<>(mapper.map(project, ProjectDTO.class), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Project> projects = projectService.getAll();
        return new ResponseEntity<>(projects.stream()
                .map(element -> mapper.map(element, ProjectDTO.class))
                .collect(Collectors.toList()), HttpStatus.OK
        );
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") Long id){
        projectService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ProjectUpdateDTO editing){
        Project project = projectService.update(mapper.map(editing, Project.class));
        return new ResponseEntity<>(mapper.map(project, ProjectDTO.class), HttpStatus.OK);
    }


}
