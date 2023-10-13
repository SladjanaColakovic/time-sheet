package com.example.timesheet.core.repository;

import com.example.timesheet.core.model.DailyTimeSheet;
import com.example.timesheet.core.model.TimeSheetRange;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IDailyTimeSheetRepository {
    List<DailyTimeSheet> getDailyTimeSheets(TimeSheetRange timeSheetRange);
}
