package com.example.timesheet.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class TimeSheetRangeDTO {
    private LocalDate from;
    private LocalDate to;
    private Long teamMemberId;
    //private Double regularHours;


    public TimeSheetRangeDTO(LocalDate from, LocalDate to, Long teamMemberId) {
        this.from = from;
        this.to = to;
        this.teamMemberId = teamMemberId;
    }
}
