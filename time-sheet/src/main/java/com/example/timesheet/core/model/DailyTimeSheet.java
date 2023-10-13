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
    private Double hoursPerDay;
    private Double overtimeHoursPerDay;
    private Flag flag;

    public DailyTimeSheet(LocalDate date, Double hoursPerDay, Double overtimeHoursPerDay){
        this.date = date;
        this.hoursPerDay = hoursPerDay;
        this.overtimeHoursPerDay = overtimeHoursPerDay;
    }

    public DailyTimeSheet(LocalDate date, Double hoursPerDay, Double overtimeHoursPerDay, Flag flag){
        this.date = date;
        this.hoursPerDay = hoursPerDay;
        this.overtimeHoursPerDay = overtimeHoursPerDay;
        this.flag = flag;
    }
}
