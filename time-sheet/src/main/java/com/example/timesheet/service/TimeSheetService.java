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

    @Autowired
    private IDailyTimeSheetRepository dailyTimeSheetRepository;
    private final Double DAILY_WORK_NORM = 7.5;

    @Override
    public TimeSheet getTimeSheet(TimeSheetRange timeSheetRange) {
        Map<LocalDate, DailyTimeSheet> timeSheet = createInitialTimeSheet(timeSheetRange.getFrom(), timeSheetRange.getTo());
        List<DailyTimeSheet> dailyTimeSheets = dailyTimeSheetRepository.getDailyTimeSheets(timeSheetRange);
        List<DailyTimeSheet> flaggedDailyTimeSheets = flagDailyTimeSheet(dailyTimeSheets);

        Map<LocalDate, DailyTimeSheet> existingTimeSheet = flaggedDailyTimeSheets
                .stream()
                .collect(Collectors.toMap(DailyTimeSheet::getDate, Function.identity()));

        timeSheet.putAll(existingTimeSheet);
        return new TimeSheet(new ArrayList<>(timeSheet.values()), calculateTotalHours(dailyTimeSheets));
    }

    private Map<LocalDate, DailyTimeSheet> createInitialTimeSheet(LocalDate from, LocalDate to){
        Map<LocalDate, DailyTimeSheet> initialTimeSheet = new HashMap<>();
        for(LocalDate date = from; date.isBefore(to); date = date.plusDays(1)){
            initialTimeSheet.put(date, new DailyTimeSheet(date, 0.0, Flag.NOT_FILLED));
        }
        return initialTimeSheet;
    }

    private List<DailyTimeSheet> flagDailyTimeSheet(List<DailyTimeSheet> dailyTimeSheets){
        dailyTimeSheets.forEach(element -> {
            element.setTotalHoursPerDay(element.getHoursPerDay() + element.getOvertimeHoursPerDay());
            if (element.getTotalHoursPerDay() >= DAILY_WORK_NORM) element.setFlag(Flag.FULFILLED);
            else element.setFlag(Flag.UNFULFILLED);
        });
        return dailyTimeSheets;
    }

    private Double calculateTotalHours(List<DailyTimeSheet> dailyTimeSheets){
        return dailyTimeSheets.stream()
                .map(element -> element.getHoursPerDay() + element.getOvertimeHoursPerDay())
                .reduce(0.0, Double::sum);
    }

}
