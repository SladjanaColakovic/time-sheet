package com.example.timesheet.service;

import com.example.timesheet.core.enumeration.Flag;
import com.example.timesheet.core.model.DailyTimeSheet;
import com.example.timesheet.core.model.TimeSheet;
import com.example.timesheet.core.model.TimeSheetRange;
import com.example.timesheet.core.repository.IDailyTimeSheetRepository;
import com.example.timesheet.core.service.ITimeSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TimeSheetService implements ITimeSheetService {

    private final IDailyTimeSheetRepository dailyTimeSheetRepository;
    private final Double DAILY_WORK_NORM = 7.5;

    @Autowired
    public TimeSheetService(IDailyTimeSheetRepository dailyTimeSheetRepository){
        this.dailyTimeSheetRepository = dailyTimeSheetRepository;
    }

    @Override
    public TimeSheet getTimeSheet(TimeSheetRange timeSheetRange) {
        Map<LocalDate, DailyTimeSheet> initialTimeSheet = createInitialTimeSheet(timeSheetRange.getFrom(), timeSheetRange.getTo());
        List<DailyTimeSheet> dailyTimeSheets = dailyTimeSheetRepository.getDailyTimeSheets(timeSheetRange);
        List<DailyTimeSheet> flaggedDailyTimeSheets = flagDailyTimeSheets(dailyTimeSheets);

        Map<LocalDate, DailyTimeSheet> dailyTimeSheetsMap = flaggedDailyTimeSheets.stream()
                .collect(Collectors.toMap(DailyTimeSheet::getDate, Function.identity()));
        initialTimeSheet.putAll(dailyTimeSheetsMap);

        TimeSheet timeSheetResponse = new TimeSheet();
        Double totalReport = calculateTotalReport(dailyTimeSheets);
        timeSheetResponse.setTotalHours(totalReport);
        ArrayList<DailyTimeSheet> valueList = new ArrayList<>(initialTimeSheet.values());
        timeSheetResponse.setDailyTimeSheets(valueList);
        return timeSheetResponse;
    }

    private Map<LocalDate, DailyTimeSheet> createInitialTimeSheet(LocalDate from, LocalDate to){
        Map<LocalDate, DailyTimeSheet> initialTimeSheet = new HashMap<>();
        for(LocalDate date = from; date.isBefore(to); date = date.plusDays(1)){
            initialTimeSheet.put(date, new DailyTimeSheet(date, 0.0, 0.0, Flag.WHITE));
        }
        return initialTimeSheet;
    }

    private List<DailyTimeSheet> flagDailyTimeSheets(List<DailyTimeSheet> dailyTimeSheets){
        dailyTimeSheets.forEach(element -> {
            if (element.getHoursPerDay() + element.getOvertimeHoursPerDay() >= DAILY_WORK_NORM) element.setFlag(Flag.GREEN);
            else element.setFlag(Flag.RED);
        });
        return dailyTimeSheets;
    }

    private Double calculateTotalReport(List<DailyTimeSheet> dailyTimeSheets){
        return dailyTimeSheets.stream()
                .map(element -> element.getHoursPerDay() + element.getOvertimeHoursPerDay())
                .reduce(0.0, Double::sum);
    }

}
