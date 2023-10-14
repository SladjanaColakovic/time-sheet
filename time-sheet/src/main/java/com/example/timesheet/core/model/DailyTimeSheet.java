package com.example.timesheet.core.model;

import com.example.timesheet.core.enumeration.Flag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class DailyTimeSheet {
    private LocalDate date;
    private double hoursPerDay;
    private double overtimeHoursPerDay;
    private double totalHoursPerDay;
    private Flag flag;

    public DailyTimeSheet(LocalDate date, double hoursPerDay, double overtimeHoursPerDay){
        this.date = date;
        this.hoursPerDay = hoursPerDay;
        this.overtimeHoursPerDay = overtimeHoursPerDay;
    }

    public DailyTimeSheet(LocalDate date, double totalHoursPerDay, Flag flag){
        this.date = date;
        this.totalHoursPerDay = hoursPerDay;
        this.flag = flag;
    }
}
