package com.example.timesheet.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class TimeSheet {
    private List<DailyTimeSheet> dailyTimeSheets;
    private double totalHours;

    public TimeSheet(List<DailyTimeSheet> dailyTimeSheets, double totalHours){
        this.dailyTimeSheets = dailyTimeSheets;
        this.totalHours = totalHours;
    }
}
