package com.example.timesheet.app.controller;

import com.example.timesheet.CustomMapper;
import com.example.timesheet.app.dto.NewProjectDTO;
import com.example.timesheet.app.dto.ProjectDTO;
import com.example.timesheet.app.dto.ProjectUpdateDTO;
import com.example.timesheet.app.dto.Projects;
import com.example.timesheet.app.security.RequestInterceptor;
import com.example.timesheet.core.model.Project;
import com.example.timesheet.core.model.UserInfo;
import com.example.timesheet.core.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/project")
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    @Autowired
    private CustomMapper mapper;

    @Autowired
    private RequestInterceptor requestInterceptor;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody NewProjectDTO newProject){
        Project project = mapper.newProjectDTOToProject(newProject);
        Project created = projectService.create(project);
        ProjectDTO response = mapper.projectToProjectDTO(created);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Project project = projectService.getById(id);
        ProjectDTO response = mapper.projectToProjectDTO(project);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Project> projects = projectService.getAll(requestInterceptor.getUserInfo());
        List<ProjectDTO> response = projects.stream()
                .map(mapper::projectToProjectDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(new Projects(response), HttpStatus.OK);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@RequestParam("id") Long id){
        projectService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@RequestBody ProjectUpdateDTO editing){
        Project project = projectService.update(mapper.projectUpdateDTOToProject(editing));
        ProjectDTO response = mapper.projectToProjectDTO(project);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/client")
    public ResponseEntity<?> getClientProjects(@RequestParam("clientId") Long clientId){
        List<Project> projects = projectService.getClientProjects(clientId);
        List<ProjectDTO> response = projects.stream()
                .map(mapper::projectToProjectDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(new Projects(response), HttpStatus.OK);
    }
}
