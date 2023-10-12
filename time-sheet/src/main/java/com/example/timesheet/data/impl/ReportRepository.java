package com.example.timesheet.data.impl;

import com.example.timesheet.CustomMapper;
import com.example.timesheet.core.model.ReportSearch;
import com.example.timesheet.core.model.TimeSheetItem;
import com.example.timesheet.core.repository.IReportRepository;
import com.example.timesheet.data.entity.TimeSheetItemEntity;
import com.example.timesheet.data.repository.TimeSheetItemJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReportRepository implements IReportRepository {

    private final TimeSheetItemJpaRepository timeSheetItemJpaRepository;

    private final CustomMapper mapper;

    @Autowired
    public ReportRepository(TimeSheetItemJpaRepository timeSheetItemJpaRepository, CustomMapper mapper){
        this.timeSheetItemJpaRepository = timeSheetItemJpaRepository;
        this.mapper = mapper;
    }
    @Override
    public List<TimeSheetItem> reportSearch(ReportSearch reportSearch) {
        List<TimeSheetItemEntity> items = timeSheetItemJpaRepository.reportSearch(reportSearch);
        return items
                .stream()
                .map(mapper::timeSheetItemEntityToTimeSheetItem)
                .collect(Collectors.toList());
    }
}
