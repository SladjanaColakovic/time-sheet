package com.example.timesheet.service;

import com.example.timesheet.core.model.ChangePassword;
import com.example.timesheet.core.model.TeamMember;
import com.example.timesheet.core.repository.ITeamMemberRepository;
import com.example.timesheet.core.service.ITeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamMemberService implements ITeamMemberService {

    @Autowired
    private ITeamMemberRepository teamMemberRepository;

    @Override
    public TeamMember create(TeamMember teamMember) {
        return teamMemberRepository.create(teamMember);
    }

    @Override
    public TeamMember getById(Long id) {
        return teamMemberRepository.getById(id);
    }

    @Override
    public void delete(Long id) {
        teamMemberRepository.delete(id);
    }

    @Override
    public List<TeamMember> getAll() {
        return teamMemberRepository.getAll();
    }

    @Override
    public TeamMember update(TeamMember teamMember) {
        return teamMemberRepository.update(teamMember);
    }

    @Override
    public TeamMember getByUsername(String username) {
        return teamMemberRepository.getByUsername(username);
    }

    @Override
    public void changePassword(ChangePassword changePassword) {
        teamMemberRepository.changePassword(changePassword);
    }
}
