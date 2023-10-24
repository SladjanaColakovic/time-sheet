package com.example.timesheet.core.service;


import com.example.timesheet.core.model.TeamMemberTimeSheetItems;
import com.example.timesheet.core.model.TimeSheetItem;

import java.time.LocalDate;
import java.util.List;

public interface ITimeSheetItemService {
    TimeSheetItem create(TimeSheetItem timeSheetItem);
    TimeSheetItem getById(Long id);
    List<TimeSheetItem> getAll();
    TeamMemberTimeSheetItems getTeamMemberItems(Long teamMemberId, LocalDate date);
    TimeSheetItem update(TimeSheetItem timeSheetItem);

}
