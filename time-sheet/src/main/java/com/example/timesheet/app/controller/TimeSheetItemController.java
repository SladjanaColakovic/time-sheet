package com.example.timesheet.app.controller;

import com.example.timesheet.CustomMapper;
import com.example.timesheet.app.dto.*;
import com.example.timesheet.core.model.Project;
import com.example.timesheet.core.model.TeamMemberTimeSheetItems;
import com.example.timesheet.core.model.TimeSheetItem;
import com.example.timesheet.core.service.ITimeSheetItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/timeSheetItem")
public class TimeSheetItemController {

    @Autowired
    private ITimeSheetItemService timeSheetItemService;

    @Autowired
    private CustomMapper mapper;

    @PostMapping
    @PreAuthorize("hasRole('WORKER')")
    public ResponseEntity<?> create(@RequestBody NewTimeSheetItemDTO newTimeSheetItem){
        TimeSheetItem timeSheetItem = timeSheetItemService.create(mapper.newTimeSheetItemDTOToTimeSheetItem(newTimeSheetItem));
        TimeSheetItemDTO response = mapper.timeSheetItemToTimeSheetItemDTO(timeSheetItem);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('WORKER')")
    public ResponseEntity<?> getById(@PathVariable Long id){
        TimeSheetItem timeSheetItem = timeSheetItemService.getById(id);
        TimeSheetItemDTO response = mapper.timeSheetItemToTimeSheetItemDTO(timeSheetItem);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasRole('WORKER')")
    public ResponseEntity<?> getAll(){
        List<TimeSheetItem> items = timeSheetItemService.getAll();
        List<TimeSheetItemDTO> response = items.stream()
                .map(mapper::timeSheetItemToTimeSheetItemDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(new TimeSheetItems(response), HttpStatus.OK);
    }

    @GetMapping("/teamMember")
    @PreAuthorize("hasRole('WORKER')")
    public ResponseEntity<?> getTeamMemberItems(@RequestParam("teamMemberId") Long teamMemberId,
                                                @RequestParam("date") LocalDate date){
        TeamMemberTimeSheetItems teamMemberTimeSheetItems = timeSheetItemService.getTeamMemberItems(teamMemberId, date);
        TeamMemberTimeSheetItemsDTO response = mapper.teamMemberTimeSheetItemToTeamMemberTimeSheetItemsDTO(teamMemberTimeSheetItems);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PutMapping
    @PreAuthorize("hasRole('WORKER')")
    public ResponseEntity<?> update(@RequestBody TimeSheetItemUpdateDTO editing){
        TimeSheetItem timeSheetItem = timeSheetItemService.update(mapper.timeSheetItemUpdateDTOToTimeSheetItem(editing));
        TimeSheetItemDTO response = mapper.timeSheetItemToTimeSheetItemDTO(timeSheetItem);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
