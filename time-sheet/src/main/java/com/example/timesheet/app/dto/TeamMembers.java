package com.example.timesheet.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class TeamMembers {
    private List<TeamMemberDTO> teamMembers;

    public TeamMembers(List<TeamMemberDTO> teamMembers){
        this.teamMembers = teamMembers;
    }
}
