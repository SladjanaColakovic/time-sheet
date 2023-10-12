package com.example.timesheet.app.dto;


import com.example.timesheet.app.enumeration.Role;
import com.example.timesheet.app.enumeration.TeamMemberStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
public class TeamMemberDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private Float hoursPerWeek;
    private TeamMemberStatus status;
    private Role role;
    private Set<ProjectDTO> leadingProjects;
    private Set<ProjectDTO> workingProjects;
}
