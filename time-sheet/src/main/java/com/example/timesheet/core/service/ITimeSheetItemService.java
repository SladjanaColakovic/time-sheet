package com.example.timesheet.core.service;


import com.example.timesheet.core.model.TimeSheetItem;

import java.util.List;

public interface ITimeSheetItemService {
    TimeSheetItem create(TimeSheetItem timeSheetItem);
    TimeSheetItem getById(Long id);
    List<TimeSheetItem> getAll();

}
