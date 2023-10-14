package com.example.timesheet.app.controller;

import com.example.timesheet.CustomMapper;
import com.example.timesheet.app.dto.NewProjectDTO;
import com.example.timesheet.app.dto.ProjectDTO;
import com.example.timesheet.app.dto.ProjectUpdateDTO;
import com.example.timesheet.app.dto.Projects;
import com.example.timesheet.core.model.Project;
import com.example.timesheet.core.service.IProjectService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/project")
public class ProjectController {

    private final IProjectService projectService;

    private final CustomMapper mapper;

    @Autowired
    public ProjectController(IProjectService projectService, CustomMapper mapper) {
        this.projectService = projectService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewProjectDTO newProject){
        Project project = mapper.newProjectDTOToProject(newProject);
        Project created = projectService.create(project);
        ProjectDTO response = mapper.projectToProjectDTO(created);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Project project = projectService.getById(id);
        ProjectDTO response = mapper.projectToProjectDTO(project);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or @customUserSecurity.hasAccess(authentication,#username)")
    public ResponseEntity<?> getAll(@RequestParam(required = false) String username){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Project> projects;
        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            projects = projectService.getAll();
            List<ProjectDTO> response = projects.stream()
                    .map(mapper::projectToProjectDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(new Projects(response), HttpStatus.OK);
        }
        else{
            projects = projectService.getLeadingProjects(username);
            List<ProjectDTO> response = projects.stream()
                    .map(mapper::projectToProjectDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(new Projects(response), HttpStatus.OK);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") Long id){
        projectService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ProjectUpdateDTO editing){
        Project project = projectService.update(mapper.projectUpdateDTOToProject(editing));
        ProjectDTO response = mapper.projectToProjectDTO(project);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
