package com.example.timesheet.data.impl;

import com.example.timesheet.core.exception.ObjectNotFoundException;
import com.example.timesheet.core.model.TeamMember;
import com.example.timesheet.core.repository.ITeamMemberRepository;
import com.example.timesheet.data.entity.TeamMemberEntity;
import com.example.timesheet.data.repository.TeamMemberJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamMemberJpa implements ITeamMemberRepository {

    private final TeamMemberJpaRepository teamMemberJpaRepository;
    private final ModelMapper mapper;

    @Autowired
    public TeamMemberJpa(TeamMemberJpaRepository teamMemberJpaRepository, ModelMapper mapper){
        this.teamMemberJpaRepository = teamMemberJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public TeamMember create(TeamMember teamMember) {
        TeamMemberEntity newEntity = mapper.map(teamMember, TeamMemberEntity.class);
        TeamMemberEntity saved = teamMemberJpaRepository.save(newEntity);
        return mapper.map(saved, TeamMember.class);
    }

    @Override
    public TeamMember getById(Long id) {
        TeamMemberEntity teamMember = teamMemberJpaRepository.findById(id).orElse(null);
        if(teamMember == null) throw new ObjectNotFoundException();
        return mapper.map(teamMember, TeamMember.class);
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
                .map(element -> mapper.map(element, TeamMember.class))
                .collect(Collectors.toList());
    }

    @Override
    public TeamMember update(TeamMember teamMember) {
        TeamMemberEntity editing = mapper.map(teamMember, TeamMemberEntity.class);
        TeamMemberEntity saved = teamMemberJpaRepository.save(editing);
        return mapper.map(saved, TeamMember.class);
    }
}
