package com.example.timesheet.service;

import com.example.timesheet.core.model.Report;
import com.example.timesheet.core.model.ReportSearch;
import com.example.timesheet.core.model.TimeSheetItem;
import com.example.timesheet.core.repository.IReportRepository;
import com.example.timesheet.core.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService implements IReportService {
    private final IReportRepository reportRepository;

    @Autowired
    public ReportService(IReportRepository reportRepository){
        this.reportRepository = reportRepository;
    }

    @Override
    public Report reportSearch(ReportSearch reportSearch) {
        return reportRepository.reportSearch(reportSearch);
    }
}
