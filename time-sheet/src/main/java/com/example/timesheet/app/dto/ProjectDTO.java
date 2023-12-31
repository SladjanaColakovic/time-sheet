package com.example.timesheet.app.dto;

import com.example.timesheet.app.enumeration.ProjectStatus;

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
    private ClientDTO client;
    private TeamMemberDTO lead;
    private Set<TeamMemberDTO> teamMembers;

}
