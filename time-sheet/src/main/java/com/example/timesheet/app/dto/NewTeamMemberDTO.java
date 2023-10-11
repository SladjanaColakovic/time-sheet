package com.example.timesheet.app.dto;

import com.example.timesheet.app.enumeration.Role;
import com.example.timesheet.app.enumeration.TeamMemberStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class NewTeamMemberDTO {
    private String name;
    private String username;
    private String password;
    private String email;
    private float hoursPerWeek;
    private TeamMemberStatus status;
    private Role role;
}
