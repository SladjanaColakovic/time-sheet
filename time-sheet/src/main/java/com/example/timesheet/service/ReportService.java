package com.example.timesheet.service;

import com.example.timesheet.CustomMapper;
import com.example.timesheet.core.model.Report;
import com.example.timesheet.core.model.ReportSearch;
import com.example.timesheet.core.model.TimeSheetItem;
import com.example.timesheet.core.repository.ITimeSheetItemRepository;
import com.example.timesheet.core.service.IReportService;
import com.example.timesheet.data.entity.TimeSheetItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService implements IReportService {

    @Autowired
    private ITimeSheetItemRepository timeSheetItemRepository;

    @Autowired
    private CustomMapper mapper;

    @Override
    public Report reportSearch(ReportSearch reportSearch) {
        List<TimeSheetItem> items = timeSheetItemRepository.reportSearch(reportSearch);
        Report report = new Report();
        report.setItems(items);
        Float totalReport = items.stream()
                .map(TimeSheetItem::getTime)
                .reduce(0f, Float::sum);
        report.setTotalReport(totalReport);
        return report;
    }
}
