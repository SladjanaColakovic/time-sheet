package com.example.timesheet.service;

import com.example.timesheet.core.enumeration.Flag;
import com.example.timesheet.core.model.DailyTimeSheet;
import com.example.timesheet.core.model.TimeSheet;
import com.example.timesheet.core.model.TimeSheetRange;
import com.example.timesheet.core.repository.IDailyTimeSheetRepository;
import com.example.timesheet.core.repository.ITimeSheetItemRepository;
import com.example.timesheet.core.service.ITimeSheetService;
import com.example.timesheet.data.entity.TimeSheetItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<DailyTimeSheet> dailyTimeSheets = dailyTimeSheetRepository.getDailyTimeSheets(timeSheetRange);
        dailyTimeSheets.forEach(element -> {
            if (element.getHoursPerDay() + element.getOvertimeHoursPerDay() >= DAILY_WORK_NORM) element.setFlag(Flag.GREEN);
            else if (element.getHoursPerDay() + element.getOvertimeHoursPerDay() > 0) element.setFlag(Flag.RED);
            else element.setFlag(Flag.WHITE);
        });
        TimeSheet timeSheet = new TimeSheet();
        timeSheet.setDailyTimeSheets(dailyTimeSheets);
        Double totalReport = dailyTimeSheets.stream()
                .map(element -> element.getHoursPerDay() + element.getOvertimeHoursPerDay())
                .reduce(0.0, Double::sum);
        timeSheet.setTotalHours(totalReport);
        return timeSheet;
    }

}
