package com.example.timesheet.core.service;

import com.example.timesheet.core.model.Report;
import com.example.timesheet.core.model.ReportSearch;
import com.example.timesheet.core.model.TimeSheetItem;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public interface IReportService {
    Report reportSearch(ReportSearch reportSearch);
    ByteArrayOutputStream pdfReport(ReportSearch reportSearch);
}
