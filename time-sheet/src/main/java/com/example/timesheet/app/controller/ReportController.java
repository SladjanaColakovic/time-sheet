package com.example.timesheet.app.controller;

import com.example.timesheet.CustomMapper;
import com.example.timesheet.app.dto.ReportDTO;
import com.example.timesheet.app.dto.ReportSearchDTO;
import com.example.timesheet.core.model.Report;
import com.example.timesheet.core.model.ReportSearch;
import com.example.timesheet.core.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
        Report report = reportService.reportSearch(reportSearch);
        ReportDTO response = mapper.reportToReportDTO(report);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
