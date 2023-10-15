package com.example.timesheet.app.controller;

import com.example.timesheet.CustomMapper;
import com.example.timesheet.app.dto.NewTimeSheetItemDTO;
import com.example.timesheet.app.dto.TimeSheetItemDTO;
import com.example.timesheet.app.dto.TimeSheetItems;
import com.example.timesheet.core.model.TimeSheetItem;
import com.example.timesheet.core.service.ITimeSheetItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> create(@RequestBody NewTimeSheetItemDTO newTimeSheetItem){
        TimeSheetItem timeSheetItem = timeSheetItemService.create(mapper.newTimeSheetItemDTOToTimeSheetItem(newTimeSheetItem));
        TimeSheetItemDTO response = mapper.timeSheetItemToTimeSheetItemDTO(timeSheetItem);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        TimeSheetItem timeSheetItem = timeSheetItemService.getById(id);
        TimeSheetItemDTO response = mapper.timeSheetItemToTimeSheetItemDTO(timeSheetItem);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<TimeSheetItem> items = timeSheetItemService.getAll();
        List<TimeSheetItemDTO> response = items.stream()
                .map(mapper::timeSheetItemToTimeSheetItemDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(new TimeSheetItems(response), HttpStatus.OK
        );
    }

}
