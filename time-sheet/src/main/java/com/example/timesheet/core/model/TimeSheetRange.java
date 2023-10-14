package com.example.timesheet.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class TimeSheetRange {
    private LocalDate from;
    private LocalDate to;
    private Long teamMemberId;
    //private Double regularHours;
}
