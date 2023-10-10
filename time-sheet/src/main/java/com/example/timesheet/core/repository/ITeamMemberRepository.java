package com.example.timesheet.core.repository;


import com.example.timesheet.core.model.TeamMember;

import java.util.List;

public interface ITeamMemberRepository {
    TeamMember create(TeamMember teamMember);
    TeamMember getById(Long id);
    void delete(Long id);
    List<TeamMember> getAll();
    TeamMember update(TeamMember teamMember);
}
