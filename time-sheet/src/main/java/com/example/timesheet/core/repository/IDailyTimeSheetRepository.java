package com.example.timesheet.core.repository;

import com.example.timesheet.core.model.DailyTimeSheet;
import com.example.timesheet.core.model.TimeSheetRange;

import java.util.List;

public interface IDailyTimeSheetRepository {
    List<DailyTimeSheet> getDailyTimeSheets(TimeSheetRange timeSheetRange);
}
