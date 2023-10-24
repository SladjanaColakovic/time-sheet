package com.example.timesheet.core.repository;

import com.example.timesheet.core.model.ReportSearch;
import com.example.timesheet.core.model.TimeSheetItem;

import java.time.LocalDate;
import java.util.List;

public interface ITimeSheetItemRepository {
    TimeSheetItem create(TimeSheetItem timeSheetItem);
    TimeSheetItem getById(Long id);
    List<TimeSheetItem> getAll();
    List<TimeSheetItem> reportSearch(ReportSearch reportSearch);
    List<TimeSheetItem> getTeamMemberItems(Long teamMemberId, LocalDate date);
    TimeSheetItem update(TimeSheetItem timeSheetItem);

}
