package com.example.timesheet.core.repository;

import com.example.timesheet.core.model.ReportSearch;
import com.example.timesheet.core.model.TimeSheetItem;

import java.util.List;

public interface IReportRepository {
    List<TimeSheetItem> reportSearch(ReportSearch reportSearch);
}
