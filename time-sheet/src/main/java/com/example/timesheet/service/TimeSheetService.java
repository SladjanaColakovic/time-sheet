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
        List<DailyTimeSheet> timeSheet = new ArrayList<>();
        List<DailyTimeSheet> dailyTimeSheets = dailyTimeSheetRepository.getDailyTimeSheets(timeSheetRange);
        Map<LocalDate, DailyTimeSheet> existingTimeSheet = dailyTimeSheets
                .stream()
                .collect(Collectors.toMap(DailyTimeSheet::getDate, Function.identity()));
        for(LocalDate date = timeSheetRange.getFrom(); date.isBefore(timeSheetRange.getTo()); date = date.plusDays(1)){
            if(!existingTimeSheet.containsKey(date)){
                timeSheet.add(new DailyTimeSheet(date, 0.0, Flag.NOT_FILLED));
                continue;
            }
            DailyTimeSheet dailyTimeSheet = existingTimeSheet.get(date);
            if (dailyTimeSheet.getTotalHoursPerDay() >= DAILY_WORK_NORM) dailyTimeSheet.setFlag(Flag.FULFILLED);
            else dailyTimeSheet.setFlag(Flag.UNFULFILLED);
            timeSheet.add(dailyTimeSheet);

        }
        return new TimeSheet(timeSheet, calculateTotalHours(dailyTimeSheets));

    }

    private Double calculateTotalHours(List<DailyTimeSheet> dailyTimeSheets){
        return dailyTimeSheets.stream()
                .map(DailyTimeSheet::getTotalHoursPerDay)
                .reduce(0.0, Double::sum);
    }

}
