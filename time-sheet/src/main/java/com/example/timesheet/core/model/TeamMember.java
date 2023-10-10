package com.example.timesheet.core.model;

import com.example.timesheet.core.enumeration.Role;
import com.example.timesheet.core.enumeration.TeamMemberStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class TeamMember {
    private long id;
    private String name;
    private String username;
    private String email;
    private float hoursPerWeek;
    private TeamMemberStatus status;
    private Role role;
    private boolean isDeleted;
    private Set<Project> leadingProjects;
    private Set<ProjectSheet> projectSheets;
}
