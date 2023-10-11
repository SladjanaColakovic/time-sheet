package com.example.timesheet.core.model;

import com.example.timesheet.core.enumeration.ProjectStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
public class Project {
    private Long id;
    private String name;
    private String description;
    private ProjectStatus status;
    private Client client;
    private TeamMember lead;
    //private Set<TeamMember> teamMembers;
}
