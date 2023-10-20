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
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TimeSheetService implements ITimeSheetService {

    @Autowired
    private IDailyTimeSheetRepository dailyTimeSheetRepository;
    private static final double DAILY_WORK_NORM = 7.5;

    @Override
    public TimeSheet getTimeSheet(TimeSheetRange timeSheetRange) {
        List<DailyTimeSheet> response = new ArrayList<>();
        Map<LocalDate, DailyTimeSheet> dailyTimeSheets = getExistingDailyTimeSheets(timeSheetRange);

        double totalHours = 0.0;
        for(LocalDate date = timeSheetRange.getFrom(); date.isBefore(timeSheetRange.getTo()) || date.isEqual(timeSheetRange.getTo()); date = date.plusDays(1)) {
            DailyTimeSheet dailyTimeSheet = createDailyTimeSheet(dailyTimeSheets, date);
            response.add(dailyTimeSheet);
            totalHours += dailyTimeSheet.getTotalHoursPerDay();
        }
        return new TimeSheet(response, totalHours);
    }

    private Map<LocalDate, DailyTimeSheet> getExistingDailyTimeSheets(TimeSheetRange timeSheetRange){
        List<DailyTimeSheet> dailyTimeSheets = dailyTimeSheetRepository.getDailyTimeSheets(timeSheetRange);
        return dailyTimeSheets.stream()
                .collect(Collectors.toMap(DailyTimeSheet::getDate, Function.identity()));
    }

    private DailyTimeSheet flagDailyTimeSheet(DailyTimeSheet dailyTimeSheet){
        if (dailyTimeSheet.getTotalHoursPerDay() >= DAILY_WORK_NORM) dailyTimeSheet.setFlag(Flag.FULFILLED);
        else dailyTimeSheet.setFlag(Flag.UNFULFILLED);
        return  dailyTimeSheet;
    }

    private DailyTimeSheet createDailyTimeSheet(Map<LocalDate, DailyTimeSheet> dailyTimeSheets, LocalDate date){
        return dailyTimeSheets.containsKey(date)
                ? flagDailyTimeSheet(dailyTimeSheets.get(date))
                : new DailyTimeSheet(date, 0.0, Flag.NOT_FILLED);
    }
}
