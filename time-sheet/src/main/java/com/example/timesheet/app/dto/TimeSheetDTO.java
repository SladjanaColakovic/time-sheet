package com.example.timesheet.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class TimeSheetDTO {
    private List<DailyTimeSheetDTO> dailyTimeSheets;
    private double totalHours;
}
