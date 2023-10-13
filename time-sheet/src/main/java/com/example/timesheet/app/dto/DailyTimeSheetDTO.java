package com.example.timesheet.app.dto;

import com.example.timesheet.app.enumeration.Flag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class DailyTimeSheetDTO {
    private LocalDate date;
    private Double hoursPerDay;
    private Double overtimeHoursPerDay;
    private Flag flag;
}
