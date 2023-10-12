package com.example.timesheet.core.model;

import com.example.timesheet.core.enumeration.Role;
import com.example.timesheet.core.enumeration.TeamMemberStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Setter
@Getter
@NoArgsConstructor
public class TeamMember {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private Float hoursPerWeek;
    private TeamMemberStatus status;
    private Role role;
    private Timestamp lastPasswordChangeDate;
    //private Set<Project> leadingProjects;
    //private Set<Project> workingProjects;
}
