package com.example.timesheet.data.impl;

import com.example.timesheet.CustomMapper;
import com.example.timesheet.core.exception.*;
import com.example.timesheet.core.model.ChangePassword;
import com.example.timesheet.core.model.TeamMember;
import com.example.timesheet.core.repository.ITeamMemberRepository;
import com.example.timesheet.data.entity.TeamMemberEntity;
import com.example.timesheet.data.repository.TeamMemberJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class TeamMemberRepository implements ITeamMemberRepository {

    @Autowired
    private  TeamMemberJpaRepository teamMemberJpaRepository;
    @Autowired
    private CustomMapper mapper;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Override
    public TeamMember create(TeamMember teamMember) {
        TeamMemberEntity newEntity = mapper.teamMemberToTeamMemberEntity(teamMember);
        if(!isValid(teamMember.getPassword())) throw new InvalidPasswordFormatException();
        newEntity.setPassword(passwordEncoder.encode(teamMember.getPassword()));
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

    @Override
    public TeamMember getByUsername(String username) {
        TeamMemberEntity teamMember = teamMemberJpaRepository.findByUsername(username);
        return mapper.teamMemberEntityToTeamMember(teamMember);
    }

    @Override
    public void changePassword(ChangePassword changePassword) {
        TeamMemberEntity teamMember = teamMemberJpaRepository.findByUsername(changePassword.getUsername());
        if(teamMember == null) throw new UserNotFoundException();
        if(!passwordEncoder.matches(changePassword.getOldPassword(), teamMember.getPassword())) throw new InvalidOldPasswordException();
        if(!changePassword.getPassword().equals(changePassword.getConfirmPassword())) throw new PasswordsDoNotMatchException();
        if(!isValid(changePassword.getPassword())) throw new InvalidPasswordFormatException();
        teamMember.setPassword(passwordEncoder.encode(changePassword.getPassword()));
        teamMemberJpaRepository.save(teamMember);
    }

    private boolean isValid(String password){
        return Pattern.compile("^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{8,}$").matcher(password).matches();
    }
}
