package com.example.timesheet.data.impl;

import com.example.timesheet.CustomMapper;
import com.example.timesheet.core.exception.ObjectNotFoundException;
import com.example.timesheet.core.model.TeamMember;
import com.example.timesheet.core.repository.ITeamMemberRepository;
import com.example.timesheet.data.entity.TeamMemberEntity;
import com.example.timesheet.data.repository.TeamMemberJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamMemberRepository implements ITeamMemberRepository {

    private final TeamMemberJpaRepository teamMemberJpaRepository;
    private final CustomMapper mapper;

    @Autowired
    public TeamMemberRepository(TeamMemberJpaRepository teamMemberJpaRepository, CustomMapper mapper){
        this.teamMemberJpaRepository = teamMemberJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public TeamMember create(TeamMember teamMember) {
        TeamMemberEntity newEntity = mapper.teamMemberToTeamMemberEntity(teamMember);
        TeamMemberEntity saved = teamMemberJpaRepository.save(newEntity);
        return mapper.teamMemberEntityToTeamMember(saved);
    }

    @Override
    public TeamMember getById(Long id) {
        TeamMemberEntity teamMember = teamMemberJpaRepository.findById(id).orElse(null);
        if(teamMember == null) throw new ObjectNotFoundException();
        return mapper.teamMemberEntityToTeamMember(teamMember);
    }

    @Override
    public void delete(Long id) {
        TeamMemberEntity teamMember = teamMemberJpaRepository.findById(id).orElse(null);
        if(teamMember == null) throw new ObjectNotFoundException();
        teamMemberJpaRepository.delete(teamMember);
    }

    @Override
    public List<TeamMember> getAll() {
        List<TeamMemberEntity> teamMembers = teamMemberJpaRepository.findAll();
        return teamMembers
                .stream()
                .map(mapper::teamMemberEntityToTeamMember)
                .collect(Collectors.toList());
    }

    @Override
    public TeamMember update(TeamMember teamMember) {
        TeamMemberEntity editing = teamMemberJpaRepository.findById(teamMember.getId()).orElse(null);
        if(editing == null) throw new ObjectNotFoundException();
        mapper.teamMemberToTeamMemberEntityUpdate(teamMember, editing);
        TeamMemberEntity saved = teamMemberJpaRepository.save(editing);
        return mapper.teamMemberEntityToTeamMember(saved);
    }
}
