package com.example.timesheet.core.service;

import com.example.timesheet.core.model.TimeSheet;
import com.example.timesheet.core.model.TimeSheetRange;

public interface ITimeSheetService {
    TimeSheet getTimeSheet(TimeSheetRange timeSheetRange);
}
