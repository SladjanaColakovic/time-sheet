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

    public TeamMember(Long id, String name, String username, String password, String email, Float hoursPerWeek, TeamMemberStatus status, Role role){
        this.id= id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.hoursPerWeek = hoursPerWeek;
        this.status = status;
        this.role = role;
    }

    public TeamMember(String name, String username, String password, String email, Float hoursPerWeek, TeamMemberStatus status, Role role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.hoursPerWeek = hoursPerWeek;
        this.status = status;
        this.role = role;
    }
}
