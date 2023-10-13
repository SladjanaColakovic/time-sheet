package com.example.timesheet.data.impl;

import com.example.timesheet.core.model.DailyTimeSheet;
import com.example.timesheet.core.model.TimeSheetRange;
import com.example.timesheet.core.repository.IDailyTimeSheetRepository;
import com.example.timesheet.data.repository.TimeSheetItemJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import java.util.List;
import java.util.Map;

@Component
public class DailyTimeSheetRepository implements IDailyTimeSheetRepository {

    private final TimeSheetItemJpaRepository timeSheetItemJpaRepository;

    @Autowired
    public DailyTimeSheetRepository(TimeSheetItemJpaRepository timeSheetItemJpaRepository){
        this.timeSheetItemJpaRepository = timeSheetItemJpaRepository;
    }
    @Override
    public List<DailyTimeSheet> getDailyTimeSheets(TimeSheetRange timeSheetRange) {
        return timeSheetItemJpaRepository.getDailyTimeSheets(timeSheetRange);
    }
}
