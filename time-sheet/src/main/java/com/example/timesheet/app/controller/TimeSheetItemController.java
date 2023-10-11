package com.example.timesheet.app.controller;

import com.example.timesheet.CustomMapper;
import com.example.timesheet.app.dto.NewTimeSheetItemDTO;
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

    private final ITimeSheetItemService timeSheetItemService;

    private final CustomMapper mapper;

    @Autowired
    public TimeSheetItemController(ITimeSheetItemService timeSheetItemService, CustomMapper mapper){
        this.timeSheetItemService = timeSheetItemService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewTimeSheetItemDTO newTimeSheetItem){
        TimeSheetItem timeSheetItem = timeSheetItemService.create(mapper.newTimeSheetItemDTOToTimeSheetItem(newTimeSheetItem));
        return new ResponseEntity<>(mapper.timeSheetItemToTimeSheetItemDTO(timeSheetItem), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        TimeSheetItem timeSheetItem = timeSheetItemService.getById(id);
        return new ResponseEntity<>(mapper.timeSheetItemToTimeSheetItemDTO(timeSheetItem), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<TimeSheetItem> items = timeSheetItemService.getAll();
        return new ResponseEntity<>(items.stream()
                .map(mapper::timeSheetItemToTimeSheetItemDTO)
                .collect(Collectors.toList()), HttpStatus.OK
        );
    }

}
