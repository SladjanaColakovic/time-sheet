package com.example.timesheet.app.controller;


import com.example.timesheet.CustomMapper;
import com.example.timesheet.app.dto.NewTeamMemberDTO;
import com.example.timesheet.app.dto.TeamMemberUpdateDTO;
import com.example.timesheet.core.model.TeamMember;
import com.example.timesheet.core.service.ITeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/teamMember")
public class TeamMemberController {
    private final ITeamMemberService teamMemberService;
    private final CustomMapper mapper;
    @Autowired
    public TeamMemberController(ITeamMemberService teamMemberService, CustomMapper mapper){
        this.teamMemberService = teamMemberService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewTeamMemberDTO newTeamMember){
        TeamMember teamMember = teamMemberService.create(mapper.newTeamMemberDTOToTeamMember(newTeamMember));
        return new ResponseEntity<>(mapper.teamMemberToTeamMemberDTO(teamMember), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        TeamMember teamMember = teamMemberService.getById(id);
        return new ResponseEntity<>(mapper.teamMemberToTeamMemberDTO(teamMember), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<TeamMember> teamMembers = teamMemberService.getAll();
        return new ResponseEntity<>(teamMembers.stream()
                .map(mapper::teamMemberToTeamMemberDTO)
                .collect(Collectors.toList()), HttpStatus.OK
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
        return new ResponseEntity<>(mapper.teamMemberToTeamMemberDTO(teamMember), HttpStatus.OK);
    }
}
