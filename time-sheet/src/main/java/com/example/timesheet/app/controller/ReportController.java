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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping(value = "/api/report")
public class ReportController {

    @Autowired
    private IReportService reportService;

    @Autowired
    private CustomMapper mapper;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> reportSearch(@RequestParam(value = "clientId", required = false) Long clientId,
                                          @RequestParam(value = "projectId", required = false) Long projectId,
                                          @RequestParam(value = "categoryId", required = false) Long categoryId,
                                          @RequestParam(value = "teamMemberId", required = false) Long teamMemberId,
                                          @RequestParam(value = "startDate", required = false) LocalDate startDate,
                                          @RequestParam(value = "endDate", required = false) LocalDate endDate){
        ReportSearch reportSearch = mapper.reportSearchDTOToReportSearch(new ReportSearchDTO(clientId, teamMemberId, categoryId, projectId, startDate, endDate));
        Report report = reportService.reportSearch(reportSearch);
        ReportDTO response = mapper.reportToReportDTO(report);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
