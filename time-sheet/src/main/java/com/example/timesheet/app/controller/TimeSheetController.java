package com.example.timesheet.app.controller;

import com.example.timesheet.CustomMapper;
import com.example.timesheet.app.dto.TimeSheetDTO;
import com.example.timesheet.app.dto.TimeSheetRangeDTO;
import com.example.timesheet.core.model.TimeSheet;
import com.example.timesheet.core.model.TimeSheetRange;
import com.example.timesheet.core.service.ITimeSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/timeSheet")
public class TimeSheetController {

    @Autowired
    private ITimeSheetService timeSheetService;

    @Autowired
    private CustomMapper mapper;

    @GetMapping()
    public ResponseEntity<?> getDailyTimeSheets(@RequestBody TimeSheetRangeDTO timeSheetRangeDTO){
        TimeSheetRange timeSheetRange = mapper.timeSheetRangeDTOToTimeSheetRange(timeSheetRangeDTO);
        TimeSheet timeSheet = timeSheetService.getTimeSheet(timeSheetRange);
        TimeSheetDTO response = mapper.timeSheetToTimeSheetDTO(timeSheet);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
