package com.example.timesheet.app.controller;

import com.example.timesheet.CustomMapper;
import com.example.timesheet.app.dto.ReportSearchDTO;
import com.example.timesheet.core.model.ReportSearch;
import com.example.timesheet.core.model.TimeSheetItem;
import com.example.timesheet.core.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/report")
public class ReportController {

    private final IReportService reportService;
    private final CustomMapper mapper;

    @Autowired
    public ReportController(IReportService reportService, CustomMapper mapper){
        this.reportService = reportService;
        this.mapper = mapper;
    }
    @GetMapping
    public ResponseEntity<?> reportSearch(@RequestBody ReportSearchDTO reportSearchDTO){
        ReportSearch reportSearch = mapper.reportSearchDTOToReportSearch(reportSearchDTO);
        List<TimeSheetItem> items = reportService.reportSearch(reportSearch);
        return new ResponseEntity<>(items.stream()
                .map(mapper::timeSheetItemToTimeSheetItemDTO)
                .collect(Collectors.toList()), HttpStatus.OK
        );
    }
}
