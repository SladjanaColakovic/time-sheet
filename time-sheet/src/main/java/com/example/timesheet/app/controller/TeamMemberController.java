package com.example.timesheet.app.controller;


import com.example.timesheet.CustomMapper;
import com.example.timesheet.app.dto.*;
import com.example.timesheet.core.model.ChangePassword;
import com.example.timesheet.core.model.TeamMember;
import com.example.timesheet.core.service.ITeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/teamMember")
public class TeamMemberController {

    @Autowired
    private ITeamMemberService teamMemberService;

    @Autowired
    private CustomMapper mapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewTeamMemberDTO newTeamMember){
        TeamMember teamMember = teamMemberService.create(mapper.newTeamMemberDTOToTeamMember(newTeamMember));
        TeamMemberDTO response = mapper.teamMemberToTeamMemberDTO(teamMember);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        TeamMember teamMember = teamMemberService.getById(id);
        TeamMemberDTO response = mapper.teamMemberToTeamMemberDTO(teamMember);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<TeamMember> teamMembers = teamMemberService.getAll();
        List<TeamMemberDTO> response = teamMembers.stream()
                .map(mapper::teamMemberToTeamMemberDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(new TeamMembers(response), HttpStatus.OK
        );
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") Long id){
        teamMemberService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody TeamMemberUpdateDTO editing){
        TeamMember teamMember = teamMemberService.update(mapper.teamMemberUpdateDTOToTeamMember(editing));
        TeamMemberDTO response = mapper.teamMemberToTeamMemberDTO(teamMember);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/changePassword")
    @PreAuthorize("hasAnyRole('WORKER', 'ADMIN')")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO){
        ChangePassword changePassword = mapper.chnagePasswordDTOToChangePassword(changePasswordDTO);
        teamMemberService.changePassword(changePassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
