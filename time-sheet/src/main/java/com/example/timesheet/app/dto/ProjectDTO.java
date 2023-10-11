package com.example.timesheet.app.dto;

import com.example.timesheet.app.enumeration.ProjectStatus;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter@NoArgsConstructor
public class ProjectDTO {
    private Long id;
    private String name;
    private String description;
    private ProjectStatus status;
    private boolean isDeleted;
    private ClientDTO client;
    private TeamMemberDTO lead;
    private Set<ProjectSheetDTO> projectSheets;

}
