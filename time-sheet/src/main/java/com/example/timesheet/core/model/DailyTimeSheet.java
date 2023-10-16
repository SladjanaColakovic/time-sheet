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
    private double totalHoursPerDay;
    private Flag flag;

    public DailyTimeSheet(LocalDate date, double totalHoursPerDay){
        this.date = date;
        this.totalHoursPerDay = totalHoursPerDay;
    }

    public DailyTimeSheet(LocalDate date, double totalHoursPerDay, Flag flag){
        this.date = date;
        this.totalHoursPerDay = totalHoursPerDay;
        this.flag = flag;
    }
}
