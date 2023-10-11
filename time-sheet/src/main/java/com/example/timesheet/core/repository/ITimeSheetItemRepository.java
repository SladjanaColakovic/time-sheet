package com.example.timesheet.core.repository;


import com.example.timesheet.core.model.TimeSheetItem;

import java.util.List;

public interface ITimeSheetItemRepository {
    TimeSheetItem create(TimeSheetItem timeSheetItem);
    TimeSheetItem getById(Long id);
    List<TimeSheetItem> getAll();

}
