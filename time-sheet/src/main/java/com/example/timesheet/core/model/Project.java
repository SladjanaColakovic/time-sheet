package com.example.timesheet.core.model;

import com.example.timesheet.core.enumeration.ProjectStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



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


    public Project(Long id, String name, String description, Client client, TeamMember lead) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.client = client;
        this.lead = lead;
    }

    public Project(String name, String description, Client client, TeamMember lead) {
        this.name = name;
        this.description = description;
        this.client = client;
        this.lead = lead;
    }
}
