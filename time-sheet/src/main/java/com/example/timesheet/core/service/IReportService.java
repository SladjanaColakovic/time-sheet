package com.example.timesheet.core.service;

import com.example.timesheet.core.model.ReportSearch;
import com.example.timesheet.core.model.TimeSheetItem;

import java.util.List;

public interface IReportService {
    List<TimeSheetItem> reportSearch(ReportSearch reportSearch);
}
