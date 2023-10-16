package com.example.timesheet.app.controller;

import com.example.timesheet.CustomMapper;
import com.example.timesheet.app.dto.NewProjectDTO;
import com.example.timesheet.app.dto.ProjectDTO;
import com.example.timesheet.app.dto.ProjectUpdateDTO;
import com.example.timesheet.app.dto.Projects;
import com.example.timesheet.app.security.token.TokenUtils;
import com.example.timesheet.core.model.Project;
import com.example.timesheet.core.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private TokenUtils tokenUtils;

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
    public ResponseEntity<?> getAll(@RequestHeader (name="Authorization") String token){

        Long userId = tokenUtils.getUserIdFromToken(token.substring(7));
        String role = tokenUtils.getRoleFromToken(token.substring(7));

        List<Project> projects = projectService.getAll(userId, role);
        List<ProjectDTO> response = projects.stream()
                .map(mapper::projectToProjectDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(new Projects(response), HttpStatus.OK);
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
